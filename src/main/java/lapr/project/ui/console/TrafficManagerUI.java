package lapr.project.ui.console;

import lapr.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TrafficManagerUI implements Runnable {

    public TrafficManagerUI() {

    }

    public void run() {

        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Import Ships From File", new ImportShipsUI()));
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
