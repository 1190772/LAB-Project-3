package lapr.project.controller;

import lapr.project.model.FreightNetwork;
import lapr.project.model.FreightNetworkVertex;
import lapr.project.model.Port;
import lapr.project.utils.Pair;
import lapr.project.utils.graph.Graph;

import java.util.*;

public class CriticalPortsController implements Comparator<Pair<Integer, Port>> {
    private ShortestPathController ctrl;

    public CriticalPortsController(ShortestPathController ctrl){
        this.ctrl = ctrl;
    }

    public List<Port> getCriticalPorts(FreightNetwork graph, int n){
        Map<Port, Integer> vertices = new HashMap<>();
        //Percorre todos os vertices duas vezes e descobre todos os menores caminhos e conta o numero de vezes que cada porto aparece em cada caminho
        for (FreightNetworkVertex vertex : graph.vertices()) {
            for( FreightNetworkVertex dest: graph.vertices() ){
                if(vertex.equals(dest)){
                    continue;
                }
                List<FreightNetworkVertex> listaP = new LinkedList<>();
                listaP.add(vertex);
                listaP.add(dest);
                List<FreightNetworkVertex> path = new LinkedList<>();
                ctrl.shortestPathSea(listaP, path);
                countVertices(path, vertices);
            }
        }
        //Converte o mapa de portos numa lista, ordena a por centralidade e retorna os n primeiros
        List<Pair<Integer, Port>> list = new LinkedList<>();
        for ( Port p: vertices.keySet() ) {
            list.add(new Pair<>(vertices.get(p),p));
        }
        list.sort(this);
        List<Port> result = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            result.add(list.remove(0).second());
        }
        return result;
    }

    //Conta o numero de vezes que o porto aparece
    private void countVertices(List<FreightNetworkVertex> path, Map<Port, Integer> vertices) {
        for ( FreightNetworkVertex vertex : path) {
            if(vertex instanceof Port){
                if(!vertices.containsKey(((Port) vertex))){
                    vertices.put(((Port) vertex),0);
                }
                vertices.replace((Port) vertex,vertices.get(vertex)+1);
            }
        }
    }
    // complexidade do shortest path x o(n^2)
    //Compara dois pairs
    @Override
    public int compare(Pair<Integer, Port> integerPortPair, Pair<Integer, Port> t1) {
        return Integer.compare(integerPortPair.first(), t1.first());
    }
}
