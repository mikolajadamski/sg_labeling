package graph;

import org.chocosolver.solver.variables.IntVar;

import java.util.Objects;

public class Edge {
    private Vertex v1;
    private Vertex v2;
    private IntVar varId;
    private String id;

    public Edge (String id, Vertex v1, Vertex v2) {
        this.id = id;
        this.v1 = v1;
        this.v2 = v2;
    }

    public String getId() {
        return id;
    }

    public IntVar getVarId() {
        return varId;
    }

    public void setVarId(IntVar varId) {
        this.varId = varId;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

}
