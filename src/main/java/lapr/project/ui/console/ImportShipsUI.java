package lapr.project.ui.console;

import lapr.project.controller.ImportShipsController;


/**
 * UI of US101
 *
 * @author José Silva 1190772
 */
public class ImportShipsUI implements Runnable {

    /**
     * The Controller associated to this UI.
     */
    private final ImportShipsController controller;

    /**
     * Builds an instance of the UI.
     */
    public ImportShipsUI() {
        controller = new ImportShipsController();
    }

    /**
     * Runs the US.
     */
    public void run() {
        controller.importShips("bships.csv");
        controller.saveShipsToDb();
        System.out.println("Data has been saved.");
    }
}