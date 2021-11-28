package lapr.project.ui.console;

import lapr.project.controller.ImportPortsController;


/**
 * UI of US201.
 *
 * @author David Magalhães 1201237
 */

public class ImportPortsUI implements Runnable{

    /**
     * The Controller associated to this UI.
     */
    private final ImportPortsController controller;

    /**
     * Builds an instance of the UI.
     */
    public ImportPortsUI() {
        controller = new ImportPortsController();
    }

    /**
     * Runs the US.
     */
    @Override
    public void run() {
        controller.importPorts("sports.csv");
    }
}
