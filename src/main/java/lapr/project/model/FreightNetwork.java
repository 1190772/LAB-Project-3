package lapr.project.model;

import lapr.project.utils.graph.MatrixGraph;
import oracle.ucp.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class FreightNetwork extends MatrixGraph<FreightNetworkVertex, Long> {

    public FreightNetwork(List<FreightNetworkVertex> vertices, Long[][] m) { super(false, vertices, m); }

    public FreightNetworkVertex findVertexByName(String name) {
        boolean found = false;
        int i = 0;
        FreightNetworkVertex startVertex = null;

        while (!found && i < numVerts) {
            if ( vertices.get(i).getName().equalsIgnoreCase(name)) {
                found = true;
                startVertex = vertices.get(i);
            }
            i++;
        }
        return startVertex;
    }

    public Pair<LinkedList<FreightNetworkVertex>, Integer> getMostEfficientCircuit(String sourceLocation) {
        LinkedList<FreightNetworkVertex> circuit = new LinkedList<>();
        FreightNetworkVertex startVertex = findVertexByName(sourceLocation);
        boolean[] visited = new boolean[numVertices()];
        FreightNetworkVertex vOrig = startVertex;
        FreightNetworkVertex closestVertex;
        Long shortestDistance;
        int totalDistance = 0;
        boolean readyToReturn = false;

        if (startVertex != null) {
            circuit.add(vOrig);
            visited[key(startVertex)] = true;

            do {
                shortestDistance = Long.MAX_VALUE;
                closestVertex = null;
                for (FreightNetworkVertex adj : adjVertices(vOrig)) {
                    if (!visited[key(adj)] && (edge(vOrig, adj).getWeight() < shortestDistance)) {
                        closestVertex = adj;
                        shortestDistance = edge(vOrig, adj).getWeight();
                    }
                }

                if (closestVertex != null) {
                    vOrig = closestVertex;
                    visited[key(vOrig)] = true;
                    circuit.add(vOrig);
                    totalDistance += shortestDistance;
                }
                else if (readyToReturn){
                    circuit.removeLast();
                    totalDistance -= edge(circuit.getLast(), vOrig).getWeight();
                    vOrig = circuit.getLast();
                }
                else {
                    visited[key(startVertex)] = false;
                    readyToReturn = true;
                }
            } while (vOrig != startVertex);
        }
        return new Pair<>(circuit, totalDistance);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        sb.append("Vertices:\n");
        for (int i = 0; i < numVerts; i++)
            sb.append(vertices.get(i).getVertexName()).append("\n");

        sb.append("\nMatrix:\n");

        temp.append(super.toString());
        temp.delete(0, temp.indexOf("|")-4);
        temp.delete(temp.indexOf("Edges"), temp.length());
        sb.append(temp.toString());

        sb.append("\n");

        return sb.toString();
    }

}
