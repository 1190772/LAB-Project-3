package lapr.project.controller;

import lapr.project.model.Country;
import lapr.project.model.FreightNetworkVertex;
import lapr.project.model.Port;
import lapr.project.utils.graph.Algorithms;
import lapr.project.utils.graph.Edge;
import lapr.project.utils.graph.Graph;
import lapr.project.utils.graph.MatrixGraph;

import java.util.LinkedList;
import java.util.List;

public class ShortestPathController {
    public Long shortestPathLand(List<FreightNetworkVertex> stops, List<FreightNetworkVertex> path) {
        verifyLists(stops, path);
        MatrixGraph<FreightNetworkVertex, Long> freightNetwork = App.getInstance().getCompany().getFreightNetwork().clone();
        for (Edge<FreightNetworkVertex, Long> edge : freightNetwork.edges())
            if (edge.getVOrig().getClass() == Port.class && edge.getVDest().getClass() == Port.class)
                freightNetwork.removeEdge(edge.getVOrig(), edge.getVDest());
        return shortestPathStops(freightNetwork, stops, path);
    }

    public Long shortestPathSea(List<FreightNetworkVertex> stops, List<FreightNetworkVertex> path) {
        verifyLists(stops, path);
        MatrixGraph<FreightNetworkVertex, Long> freightNetwork = App.getInstance().getCompany().getFreightNetwork().clone();
        for (FreightNetworkVertex o : freightNetwork.vertices())
            if (o.getClass() == Country.class)
                freightNetwork.removeVertex(o);
        return shortestPathStops(freightNetwork, stops, path);
    }

    public Long shortestPathLandSea(List<FreightNetworkVertex> stops, List<FreightNetworkVertex> path) {
        verifyLists(stops, path);
        return shortestPathStops(App.getInstance().getCompany().getFreightNetwork(), stops, path);
    }

    private Long shortestPathStops(Graph<FreightNetworkVertex, Long> freightNetwork, List<FreightNetworkVertex> stops, List<FreightNetworkVertex> path) {
        Long distance = 0L;
        for (int i = 0; i < stops.size() - 1; i++) {
            List<FreightNetworkVertex> temp = new LinkedList<>();
            Long lenghtPath = Algorithms.shortestPath(freightNetwork, stops.get(i), stops.get(i + 1), Long::compareTo, Long::sum, 0L, temp);
            if (lenghtPath ==null){
                path.removeAll(path);
                return 0L;
            }
            distance += lenghtPath;
            temp.remove(temp.size() - 1);
            path.addAll(temp);
        }
        path.add(stops.get(stops.size() - 1));
        return distance;
    }

    private void verifyLists(List<FreightNetworkVertex> stops, List<FreightNetworkVertex> path) {
        if (stops == null)
            throw new IllegalArgumentException("The list stops cannot be null");
        if (path == null)
            throw new IllegalArgumentException("The list path cannot be null");
        if (stops.size() < 2)
            throw new IllegalArgumentException("The list stops needs to have at least 2 places!");
        if (path.getClass() != LinkedList.class)
            throw new IllegalArgumentException("The list path needs to be a LinkedList!");
    }
}
