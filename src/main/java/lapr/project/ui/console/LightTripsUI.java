package lapr.project.ui.console;

import lapr.project.controller.LightTripsController;

public class LightTripsUI implements Runnable {

    private final LightTripsController controller;

    public LightTripsUI() {
        controller = new LightTripsController();
    }

    @Override
    public void run() {
        System.out.println(controller.getLightTrips());
    }
}
