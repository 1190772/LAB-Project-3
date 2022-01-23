package lapr.project.controller;

import lapr.project.data.DatabaseFunctions;

public class LightTripsController {

    public String getLightTrips() {
        return DatabaseFunctions.getLightTrips();
    }
}
