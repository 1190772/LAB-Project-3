package lapr.project.ui.console;

import lapr.project.controller.ImportBordersController;

public class ImportBordersUI implements Runnable {

    private final ImportBordersController controller;

    public ImportBordersUI() {
    controller = new ImportBordersController();
    }

    @Override
    public void run() {
        controller.importBorders("Data-Sprint3-4/borders.csv");
        controller.saveBordersToDb();
    }
}
