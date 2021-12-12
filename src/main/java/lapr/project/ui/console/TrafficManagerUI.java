package lapr.project.ui.console;

import java.util.ArrayList;
import java.util.List;

/**
 * UI of Traffic Manager Role
 *
 * @author José Silva 1190772
 */
public class TrafficManagerUI implements Runnable {

    /**
     * Constructor.
     */
    public TrafficManagerUI() {

    }

    /**
     * Runs the UI.
     */
    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Import Ships From File", new ImportShipsUI()));
        options.add(new MenuItem("Search a ship", new SearchShipUI()));
        options.add(new MenuItem("TopN Ships", new TopNShipsUI()));
        options.add(new MenuItem("Pairs of ships", new ShipPairsUI()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nTraffic Manager Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);

    }
}
