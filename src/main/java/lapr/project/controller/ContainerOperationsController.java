package lapr.project.controller;

import lapr.project.data.ContainerOperationStoreDb;
import lapr.project.model.ContainerOperation;

import java.util.ArrayList;

/**
 * Controller responsible for coordinating US304.
 *
 * @author David Magalhães 1201237
 */
public class ContainerOperationsController {

    /**
     * Instance of the Database class responsible for retrieving the information.
     */
    private final ContainerOperationStoreDb containerOperationStoreDb;

    /**
     * Builds an instance of the Controller.
     */
    public ContainerOperationsController() {
        containerOperationStoreDb = new ContainerOperationStoreDb();
    }

    /**
     * Sends ContainerOperationStoreDb the Cargo Manifest ID to be used to find Container Operations.
     *
     * @param cargoManifestID the Cargo Manifest ID to search by.
     * @return List of the found Container Operations.
     */
    public ArrayList<ContainerOperation> getContainerOperationsByCargoManifestID(int cargoManifestID) {
        return containerOperationStoreDb.getContainerOperationsByCargoManifestID(cargoManifestID);
    }
}
