package lapr.project.ui.console;

import lapr.project.controller.ContainerOperationsController;
import lapr.project.model.ContainerOperation;

import java.util.List;

/**
 * UI of US304.
 *
 * @author David Magalhães 1201237
 */
public class ContainerOperationsUI implements Runnable{

    /**
     * The Controller associated to this UI.
     */
    private final ContainerOperationsController controller;

    /**
     * Builds an instance of the UI.
     */
    public ContainerOperationsUI() {
        controller = new ContainerOperationsController();
    }

    /**
     * Runs the US.
     */
    @Override
    public void run() {
        int cargoManifestID;
        List<ContainerOperation> containerOperations;

        cargoManifestID = Utils.readIntegerFromConsole("Cargo Manifest ID: ");
        containerOperations = controller.getContainerOperationsByCargoManifestID(cargoManifestID);

        if (!containerOperations.isEmpty())
            for ( ContainerOperation containerOperation : containerOperations )
                System.out.println(containerOperation);
        else
            System.out.println("No container operations found for the specified Cargo Manifest.");
    }
}
