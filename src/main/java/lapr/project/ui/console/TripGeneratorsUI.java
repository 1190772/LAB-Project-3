package lapr.project.ui.console;

import lapr.project.controller.TripGeneratorsController;

public class TripGeneratorsUI implements Runnable {

    TripGeneratorsController controller;

    public TripGeneratorsUI() {
        controller = new TripGeneratorsController();
    }

    @Override
    public void run() {
        double energy;
        int seconds;

        energy = Utils.readDoubleFromConsole("Trip energy:");
        seconds = Utils.readIntegerFromConsole("Trip duration in seconds:");

        System.out.println("\nAmount of 75kW generators necessary for the trip: " + controller.getTripGenerators(energy, seconds));
    }
}
