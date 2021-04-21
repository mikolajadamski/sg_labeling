package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Graph {
    private Hashtable<String, Vertex> vertices;
    private Hashtable<String, Edge> edges;

    public Graph() {
        vertices = new Hashtable<>();
        edges = new Hashtable<>();
    }


    public void addVertex(Vertex vertex){
        vertices.put(vertex.getId(),vertex);
    }

    public void addEdge(Edge edge){
        edges.put(edge.getId(), edge);
    }

    public Vertex getVertex(String id){
        return vertices.get(id);
    }

    public Edge getEdge(String id){
        return edges.get(id);
    }

    public ArrayList<Vertex> getVertices(){
        return new ArrayList<Vertex>(vertices.values());
    }

    public ArrayList<Edge> getEdges(){
        return new ArrayList<Edge>(edges.values());
    }

}
