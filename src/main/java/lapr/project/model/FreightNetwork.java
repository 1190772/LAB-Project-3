package lapr.project.model;

import lapr.project.utils.graph.MatrixGraph;

import javax.naming.OperationNotSupportedException;
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

    public LinkedList<FreightNetworkVertex> getMostEfficientCircuit(String sourceLocation) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
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
