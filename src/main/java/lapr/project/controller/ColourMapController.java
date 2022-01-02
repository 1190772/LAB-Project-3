package lapr.project.controller;

import lapr.project.utils.graph.Graph;

import java.util.*;

public class ColourMapController {
    private int calculateNumberOfColours(Graph grafo) {
        if (grafo == null) {
            return 0;
        }
        int max = -1;
        for (Object vertex : grafo.vertices()) {
            int num = grafo.adjVertices(vertex).size();
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
    private<V, E> Map<V, Integer> colourMap (Graph<V, E> grafo){
        int numberOfColours = calculateNumberOfColours(grafo);
        Map<V, Integer> map = new HashMap<>();
        for (V vertex : grafo.vertices()) {
            List<V> l = new ArrayList<>(grafo.adjVertices(vertex));
            int colour = atributeColour(l,map,numberOfColours);
            map.put(vertex,colour);
        }
        return map;
    }

    private <V> int atributeColour(List<V> l, Map<V, Integer> map, int numberOfColours) {
        List<Integer> listOfColours = new LinkedList<>();
        for (int i = 0; i < numberOfColours; i++) {
            listOfColours.add(i);
        }
        for (V vertex : l ) {
            if(map.containsKey(vertex)){
                listOfColours.remove(map.get(vertex));
            }
        }
        return listOfColours.get(0);
    }
}


