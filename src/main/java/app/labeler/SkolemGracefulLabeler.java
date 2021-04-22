package app.labeler;

import app.graph.Edge;
import app.graph.Graph;
import app.graph.Vertex;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;

public class SkolemGracefulLabeler {

    SkolemGracefulLabeler(){
    }

    public Solver labelGraph(Graph graph){
        Model model = new Model("SkolemGracefulLabeler");

        ArrayList<Vertex> vertices = graph.getVertices();
        ArrayList<Edge> edges = graph.getEdges();
        int numberOfEdges = edges.size();

        IntVar[] varVertices = new IntVar[vertices.size()];
        IntVar[] varEdges = new IntVar[edges.size()];

        vertices.forEach(vertex -> {
            vertex.setVarId(model.intVar(vertex.getId(),0, numberOfEdges));
        });

        edges.forEach(edge -> {
            edge.setVarId(model.intVar(edge.getId(),1, numberOfEdges));
            setEdgeRules(edge, model);
        });

        for (int i=0; i<vertices.size(); i++) {
            varVertices[i] = vertices.get(i).getVarId();
        }

        for (int i=0; i<edges.size(); i++) {
            varEdges[i] = edges.get(i).getVarId();
        }


        model.allDifferent(varVertices).post();
        model.allDifferent(varEdges).post();

        return model.getSolver();
    }
    private void setEdgeRules(Edge edge, Model model){

        model.or(model.arithm(edge.getV1().getVarId(),"-", edge.getV2().getVarId(), "=", edge.getVarId()),
                model.arithm(edge.getV2().getVarId(),"-", edge.getV1().getVarId(), "=", edge.getVarId()))
                .post();
    }
}
