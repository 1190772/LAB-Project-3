package lapr.project.model;

import lapr.project.utils.graph.MatrixGraph;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedList;
import java.util.List;

public class FreightNetwork extends MatrixGraph<Object, Long> {

    public FreightNetwork(List<Object> vertices, Long[][] m) { super(false, vertices, m); }

    public FreightNetworkVertex findVertexByName(String name) {
        boolean found = false;
        int i = 0;
        FreightNetworkVertex startVertex = null;

        while (!found && i < numVerts) {
            if ( ((FreightNetworkVertex) vertices.get(i)).getName().equals(name)) {
                found = true;
                startVertex = (FreightNetworkVertex) vertices.get(i);
            }
            i++;
        }
        return startVertex;
    }

    public LinkedList<String> getMostEfficientCircuit(String sourceLocation) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

}
