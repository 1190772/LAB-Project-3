package lapr.project.controller;

import lapr.project.data.CargoManifestStoreDb;

import java.util.ArrayList;

public class ContainerPositionsController {

    private final CargoManifestStoreDb cargoManifestStoreDb;

    public ContainerPositionsController() {
        cargoManifestStoreDb = new CargoManifestStoreDb();
    }

    public ArrayList<String> getContainerPositions(int cargoManifestID) {
        return cargoManifestStoreDb.getContainerPositions(cargoManifestID);
    }
}
