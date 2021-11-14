package lapr.project.ui.console;

import lapr.project.controller.ImportShipsController;

public class ImportShipsUI implements Runnable {

    private final ImportShipsController controller;

    public ImportShipsUI() {
        controller = new ImportShipsController();
    }

    public void run() {
        controller.importShips("bships.csv");
        System.out.println("Data has been saved.");
    }
}