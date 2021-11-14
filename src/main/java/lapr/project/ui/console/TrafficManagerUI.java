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
     * @throws UnsupportedOperationException
     */
    public TrafficManagerUI() throws UnsupportedOperationException {

    }

    /**
     * Runs the UI.
     */
    public void run() {

        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Import Ships From File", new ImportShipsUI()));
        options.add(new MenuItem("Search a ship", new SearchShipUI()));
        options.add(new MenuItem("TopN Ships", new TopNShipsUI()));
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
