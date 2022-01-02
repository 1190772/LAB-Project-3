package lapr.project.ui.console;

import lapr.project.controller.PortOccupationController;

public class PortOccupationUI implements Runnable {

    private PortOccupationController controller;

    public PortOccupationUI() {
        controller = new PortOccupationController();
    }

    @Override
    public void run() {
        String portID;
        String month;

        portID = Utils.readLineFromConsole("Inser port id:");
        month = Utils.readLineFromConsole("Insert month (YYYY-MM):");

        System.out.println(controller.getPortOccupation(portID, month));
    }
}
