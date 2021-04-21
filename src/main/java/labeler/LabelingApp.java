package labeler;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import org.chocosolver.solver.Solver;

import java.util.*;

public class LabelingApp {

    public static void main(String[] args){
        Graph graph = new Graph();
        int n = 3;
        String[] E = {"v1", "v2", "v2", "v3", "v3", "v4"};

        for(int i=0; i<=n; i++)
            graph.addVertex(new Vertex("v"+String.valueOf(i+1)));
        for(int i = 0, j=0; i<n; i++, j+=2)
            graph.addEdge(new Edge("e" + String.valueOf(i+1), graph.getVertex(E[j]), graph.getVertex(E[j+1])));

        SkolemGracefulLabeler labeler = new SkolemGracefulLabeler();
        Solver solver = labeler.labelGraph(graph);

        if(solver.solve()){
            System.out.println("Solution: ");
            ArrayList<Vertex> vertices = graph.getVertices();
            ArrayList<Edge> edges = graph.getEdges();
            for (Vertex vertex: vertices)
                System.out.printf("Vertex %s\n",vertex.getVarId());
            for (Edge edge: edges)
                System.out.printf("Edge %s\n",edge.getVarId());
        }
        else System.out.println("No solution");
    }
}
