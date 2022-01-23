package lapr.project.ui.console;

import lapr.project.controller.App;
import lapr.project.controller.CriticalPortsController;
import lapr.project.controller.ShortestPathController;

import java.util.Scanner;

public class CriticalPortsUI implements Runnable{
    private final CriticalPortsController controller;

    public CriticalPortsUI() {
        controller = new CriticalPortsController(new ShortestPathController());
    }

    @Override
    public void run() {
        System.out.println("Number of ports:");
        System.out.println(controller.getCriticalPorts(App.getInstance().getCompany().getFreightNetwork(), new Scanner(System.in).nextInt()));
    }
}
