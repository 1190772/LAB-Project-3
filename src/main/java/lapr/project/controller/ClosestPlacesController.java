package lapr.project.controller;


import lapr.project.model.Country;
import lapr.project.model.FreightNetwork;
import lapr.project.model.FreightNetworkVertex;
import lapr.project.model.Port;
import lapr.project.utils.graph.Algorithms;
import lapr.project.utils.graph.Edge;
import lapr.project.utils.graph.MatrixGraph;
import oracle.ucp.util.Pair;

import java.util.*;

public class ClosestPlacesController {
    public Map<String, List<Pair<FreightNetworkVertex, Long>>> closestPlaces(FreightNetwork freightNetwork, int n) {
        MatrixGraph<FreightNetworkVertex, Long> shortPathMatrix = Algorithms.minDistGraph(freightNetwork, Long::compareTo, Long::sum);
        Map<String, List<Pair<FreightNetworkVertex, Long>>> result = new HashMap<>();
        int numVertices = shortPathMatrix.vertices().size();

        for (FreightNetworkVertex vertex : shortPathMatrix.vertices())
            if (shortPathMatrix.outDegree(vertex) == numVertices - 1) {
                long sum = 0;
                for (Edge<FreightNetworkVertex, Long> edge : shortPathMatrix.outgoingEdges(vertex))
                    sum += edge.getWeight();
                sum /= (numVertices - 1);

                if (vertex.getClass() == Country.class){
                    result.computeIfAbsent(((Country) vertex).getContinent(), k -> new ArrayList<>());
                    result.get(((Country) vertex).getContinent()).add(new Pair<>(vertex, sum));
                }
                else
                    result.get(((Port) vertex).getCountry().getContinent()).add(new Pair<>(vertex, sum));

            }

        for (List<Pair<FreightNetworkVertex, Long>> list : result.values()){
            list.sort((o1, o2) -> {
                if (o1.get2nd().equals(o2.get2nd()))
                    return 0;
                return (o1.get2nd() > o2.get2nd()) ? 1 : -1;
            });

            while (list.size()>n)
                list.remove(list.size()-1);
        }

        return result;
    }
}
