package lapr.project.ui.console;

import java.util.ArrayList;
import java.util.List;

public class PortStaffUI implements Runnable{

    public  PortStaffUI() {
    }

    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Create file with container positions", new ContainerPositionsUI()));
        int option;

        do {
            option = Utils.showAndSelectIndex(options, "\n\nPort Staff Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }

}
