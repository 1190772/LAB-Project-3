package lapr.project.controller;

public class TripGeneratorsController {

    private final int GENERATOR_ENERGY = 75000;

    public long getTripGenerators(double energy, int seconds) {
        long res = 0;

        res = (long) Math.ceil((energy/seconds)/GENERATOR_ENERGY);

        return res;
    }
}
