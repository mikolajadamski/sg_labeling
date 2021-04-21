package graph;

import org.chocosolver.solver.variables.IntVar;

public class Edge {
    private Vertex v1;
    private Vertex v2;
    private IntVar modelId;
    private String id;

    public Edge (String id, Vertex v1, Vertex v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String getId() {
        return id;
    }

    public IntVar getModelId() {
        return modelId;
    }

    public void setModelId(IntVar modelId) {
        this.modelId = modelId;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }
}
