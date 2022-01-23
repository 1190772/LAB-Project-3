package lapr.project.ui.console;

import java.util.ArrayList;
import java.util.List;

/**
 * UI of Ship Captain Role
 *
 * @author David Magalhães 1201237
 */
public class ShipCaptainUI implements Runnable{

    /**
     * Constructor.
     */
    public  ShipCaptainUI() {
    }

    /**
     * Runs the UI.
     */
    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Check Container Operations", new ContainerOperationsUI()));
        options.add(new MenuItem("Determine unladen center of mass", new UnladenCenterOfMassUI()));
        options.add(new MenuItem("Trip Generators", new TripGeneratorsUI()));

        int option;

            do {
                option = Utils.showAndSelectIndex(options, "\n\nShip Captain Menu:");

                if ((option >= 0) && (option < options.size())) {
                    options.get(option).run();
            }
        } while (option != -1);
    }

}
