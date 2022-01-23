package lapr.project.ui.console;

import java.util.ArrayList;
import java.util.List;

public class FleetManagerUI implements Runnable {

    @Override
    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("See light trips", new LightTripsUI()));
        int option;

        do {
            option = Utils.showAndSelectIndex(options, "\n\nPort Staff Menu:");
            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
