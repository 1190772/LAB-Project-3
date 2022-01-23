package lapr.project.ui.console;

import lapr.project.controller.App;
import lapr.project.controller.CriticalPortsController;
import lapr.project.controller.ShortestPathController;
import lapr.project.model.Port;

import java.util.List;
import java.util.Scanner;

public class CriticalPortsUI implements Runnable {
    private final CriticalPortsController controller;

    public CriticalPortsUI() {
        controller = new CriticalPortsController(new ShortestPathController());
    }

    @Override
    public void run() {
        System.out.println("Insert the number of ports:");
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        System.out.println("Calculating...");
        List<Port> ports = controller.getCriticalPorts(App.getInstance().getCompany().getFreightNetwork(), n);
        System.out.println("Ports with greater centrality order:");
        for (int i = 0; i < ports.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, ports.get(i).getCountry().getName());
        }
        System.out.println("Press enter to continue...");
        sc.nextLine();
    }
}
