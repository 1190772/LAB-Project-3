package lapr.project.ui.console;

import java.util.ArrayList;
import java.util.List;

public class ShipChiefElectricalEngineerUI implements Runnable{

    public  ShipChiefElectricalEngineerUI () {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Total energy to be supplied to the containers in a trip", new TripEnergyUI()));
        int option;

        do {
            option = Utils.showAndSelectIndex(options, "\n\nShip Chief Electrical Engineer Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }

}
