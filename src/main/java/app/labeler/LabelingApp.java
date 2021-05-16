package app.labeler;

import app.graph.Edge;
import app.graph.Graph;
import app.graph.Vertex;
import org.chocosolver.solver.Solver;

import java.util.ArrayList;

public class LabelingApp {

    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addVertex(new Vertex("v0"));
        graph.addVertex(new Vertex("v1"));
        graph.addVertex(new Vertex("v2"));
        graph.addVertex(new Vertex("v3"));

        graph.addEdge(new Edge("e1", graph.getVertex("v0"), graph.getVertex("v1")));
        graph.addEdge(new Edge("e2", graph.getVertex("v1"), graph.getVertex("v2")));
        graph.addEdge(new Edge("e3", graph.getVertex("v2"), graph.getVertex("v3")));

        SkolemGracefulLabeler labeler = new SkolemGracefulLabeler();

        Solver solver = labeler.labelGraph(graph);

        if (solver.solve()) {
            System.out.println("Solution: ");
            ArrayList<Vertex> vertices = graph.getVertices();
            ArrayList<Edge> edges = graph.getEdges();
            for (Vertex vertex : vertices) {
                System.out.printf("Vertex %s\n", vertex.getVarId());
            }
            for (Edge edge : edges) {
                System.out.printf("Edge %s\n", edge.getVarId());
            }
        } else {
            System.out.println("No solution");
        }
    }
}
