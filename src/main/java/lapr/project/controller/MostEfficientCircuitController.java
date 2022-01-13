package lapr.project.controller;

import lapr.project.data.ContainerOperationStoreDb;
import lapr.project.model.ContainerOperation;
import lapr.project.model.FreightNetwork;
import lapr.project.model.FreightNetworkVertex;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller responsible for coordinating US403.
 *
 * @author David Magalhães 1201237
 */
public class MostEfficientCircuitController {

    /**
     * Builds an instance of the Controller.
     */
    public MostEfficientCircuitController() {
    }

    /**
     * Sends FreightNetwork a location to begin the circuit.
     *
     * @param sourceLocation the starting location.
     * @return Most efficient circuit.
     */
    public LinkedList<FreightNetworkVertex> getMostEfficientCircuit(String sourceLocation) {
        FreightNetwork freightNetwork = App.getInstance().getCompany().getFreightNetwork();
        return freightNetwork.getMostEfficientCircuit(sourceLocation);
    }

}
