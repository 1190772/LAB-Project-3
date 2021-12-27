package lapr.project.ui.console;

import lapr.project.controller.ImportSeaDistancesController;

public class ImportSeaDistancesUI implements Runnable{

    private final ImportSeaDistancesController controller;

    public ImportSeaDistancesUI() {
    controller = new ImportSeaDistancesController();
    }

    @Override
    public void run() {
        controller.importSeaDistances("Data-Sprint3-4/seadists.csv");
        controller.saveSeaDistancesToDb();
    }
}
