package lapr.project.ui.console;

import lapr.project.controller.TripEnergyController;

public class TripEnergyUI implements Runnable{

    private final TripEnergyController controller;

    public TripEnergyUI() {
        controller = new TripEnergyController();
    }

    @Override
    public void run() {
        int tripID;

        tripID = Utils.readIntegerFromConsole("Insert trip ID:");
        System.out.println(controller.getTripEnergy(tripID));
    }
}
