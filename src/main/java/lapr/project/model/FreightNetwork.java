package lapr.project.model;

import lapr.project.utils.graph.MatrixGraph;

import java.util.List;

public class FreightNetwork extends MatrixGraph<Object, Long> {

    public FreightNetwork(List<Object> vertices, Long[][] m) {
        super(false, vertices, m);
    }
}
