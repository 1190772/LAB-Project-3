package lapr.project.controller;

import lapr.project.data.CargoManifestStoreDb;

import java.util.List;

public class ContainerPositionsController {

    private final CargoManifestStoreDb cargoManifestStoreDb;

    public ContainerPositionsController() {
        cargoManifestStoreDb = new CargoManifestStoreDb();
    }

    public List<String> getContainerPositions(int cargoManifestID) {
        return cargoManifestStoreDb.getContainerPositions(cargoManifestID);
    }

    public List<String> getContainerInfo(int cargoManifestID) {
        return cargoManifestStoreDb.getContainerInfo(cargoManifestID);
    }
}
