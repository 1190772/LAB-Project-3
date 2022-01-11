package lapr.project.ui.console;

import lapr.project.controller.MostEfficientCircuitController;
import lapr.project.model.FreightNetworkVertex;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedList;

/**
 * UI of US403.
 *
 * @author David Magalhães 1201237
 */
public class MostEfficientCircuitUI implements Runnable{

    /**
     * The Controller associated to this UI.
     */
    private final MostEfficientCircuitController controller;

    /**
     * Builds an instance of the UI.
     */
    public MostEfficientCircuitUI() {
    controller = new MostEfficientCircuitController();
    }

    /**
     * Runs the US.
     */
    @Override
    public void run() {
        String sourceLocation;
        LinkedList<FreightNetworkVertex> circuit = new LinkedList<>();

        sourceLocation = Utils.readLineFromConsole("Source Location: ");
        try {
            circuit = controller.getMostEfficientCircuit(sourceLocation);
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }

    for (FreightNetworkVertex place : circuit)
            System.out.println(place.getName());
    }
}
