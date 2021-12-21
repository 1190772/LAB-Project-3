package lapr.project.model;

import lapr.project.utils.graph.MatrixGraph;

import java.util.List;

public class FreightNetwork extends MatrixGraph<String, Integer> {

    public FreightNetwork(List<String> strings, Integer[][] m) {
        super(false, strings, m);
    }
}
