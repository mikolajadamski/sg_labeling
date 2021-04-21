package graph;

import org.chocosolver.solver.variables.IntVar;

public class Vertex {

    private String id;
    private IntVar varId;

    public Vertex(String id) { this.id = id; }
    public String getId() { return id; }
    public IntVar getVarId() { return varId; }
    public void setVarId(IntVar varId) { this.varId = varId; }
}
