package lapr.project.controller;

import lapr.project.data.PortOccupationDb;

public class PortOccupationController {

    private final PortOccupationDb portOccupationDb;

    public PortOccupationController() {
        portOccupationDb = new PortOccupationDb();
    }

    public String getPortOccupation(String portID, String month) {
        return portOccupationDb.getPortOccupation(portID, month);
    }
}
