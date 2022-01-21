package lapr.project.controller;

import lapr.project.model.FreightNetwork;
import lapr.project.model.FreightNetworkVertex;
import oracle.ucp.util.Pair;

import java.util.LinkedList;

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
    public Pair<LinkedList<FreightNetworkVertex>, Integer> getMostEfficientCircuit(String sourceLocation) {
        FreightNetwork freightNetwork = App.getInstance().getCompany().getFreightNetwork();
        return freightNetwork.getMostEfficientCircuit(sourceLocation);
    }

}
