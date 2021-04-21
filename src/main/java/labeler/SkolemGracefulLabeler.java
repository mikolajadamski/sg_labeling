package labeler;

import graph.Edge;
import graph.Vertex;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;

public class SkolemGracefulLabeler {


    public static void main(String[] args){
        Model model = new Model("SkolemGracefulLabeler");
        int n = 3;
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        Edge e1 = new Edge("e1", v1, v2);
        Edge e2 = new Edge("e2", v2, v3);
        Edge e3 = new Edge("e3", v3, v4);

        v1.setVarId(model.intVar(v1.getId(),0,1000000));
        v2.setVarId(model.intVar(v2.getId(),0,1000000));
        v3.setVarId(model.intVar(v3.getId(),0,1000000));
        v4.setVarId(model.intVar(v4.getId(),0,1000000));

        e1.setModelId(model.intVar("e1",1,n));
        e2.setModelId(model.intVar("e2",1,n));
        e3.setModelId(model.intVar("e3",1,n));

        setEdgeRules(e1, model);
        setEdgeRules(e2, model);
        setEdgeRules(e3, model);

        model.allDifferent(v1.getVarId(),v2.getVarId(),v3.getVarId(),v4.getVarId()).post();

        model.allDifferent(e1.getModelId(), e2.getModelId(), e3.getModelId()).post();

        Solver solver = model.getSolver();
        if(solver.solve()){
            System.out.println("Solution: ");
            System.out.println(v1.getVarId() +","+ v2.getVarId() +","+ v3.getVarId() + "," + v4.getVarId());
            System.out.println(e1.getModelId()+"," + e2.getModelId() +","+ e3.getModelId());
        }
        else{
            System.out.println("No solution");
        }


    }
    private static void setEdgeRules(Edge edge, Model model){

        model.or(model.arithm(edge.getV1().getVarId(),"-", edge.getV2().getVarId(), "=", edge.getModelId()),
                model.arithm(edge.getV2().getVarId(),"-", edge.getV1().getVarId(), "=", edge.getModelId()))
                .post();
    }
}
