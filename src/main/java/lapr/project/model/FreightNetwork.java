package lapr.project.model;

import lapr.project.utils.graph.Edge;
import lapr.project.utils.graph.MatrixGraph;

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

    public LinkedList<FreightNetworkVertex> getMostEfficientCircuit(String sourceLocation) {
        LinkedList<FreightNetworkVertex> res = new LinkedList<>();
        FreightNetworkVertex startVertex = findVertexByName(sourceLocation);
        boolean[] visited = new boolean[numVertices()];
        FreightNetworkVertex vOrig = startVertex;
        Edge<FreightNetworkVertex, Long> shortestEdge;
        Long shortestDistance;
        boolean readyToReturn = false;

        if (startVertex != null) {
            res.add(vOrig);
            visited[key(startVertex)] = true;

            do {
                shortestDistance = Long.MAX_VALUE;
                shortestEdge = null;
                for (Edge<FreightNetworkVertex, Long> edge : outgoingEdges(vOrig)) {
                    if (!visited[key(edge.getVDest())] && (edge.getWeight() < shortestDistance)) {
                        shortestEdge = edge;
                        shortestDistance = shortestEdge.getWeight();
                    }
                }

                if (shortestEdge != null) {
                    vOrig = shortestEdge.getVDest();
                    visited[key(vOrig)] = true;
                    res.add(vOrig);
                }
                else if (readyToReturn){
                    vOrig = res.getLast();
                    res.removeLast();
                }
                else {
                    visited[key(startVertex)] = false;
                    readyToReturn = true;
                }
            } while (vOrig != startVertex);
        }
        return res;
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
