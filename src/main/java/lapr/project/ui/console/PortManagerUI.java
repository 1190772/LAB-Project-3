package lapr.project.ui.console;

import java.util.ArrayList;
import java.util.List;

/**
 * UI of Port Manager Role
 *
 * @author David Magalhães 1201237
 */
public class PortManagerUI implements Runnable{
    /**
     * Constructor.
     */
    public PortManagerUI() {
    }

    /**
     * Runs the UI.
     */
    public void run() {

    List<MenuItem> options = new ArrayList<>();
    options.add(new MenuItem("Import Ports", new ImportPortsUI()));
    options.add(new MenuItem("Map of port resources", new PortOccupationUI()));
    int option;

    do {
        option = Utils.showAndSelectIndex(options, "\n\nPort Manager Menu:");

        if ((option >= 0) && (option < options.size())) {
        options.get(option).run();
        }
    } while (option != -1);
    }
}