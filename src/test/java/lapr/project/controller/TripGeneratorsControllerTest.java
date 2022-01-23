package lapr.project.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TripGeneratorsControllerTest {

    @Test
    void getTripGenerators() {
        TripGeneratorsController controller = new TripGeneratorsController();
        double energy = 2.46E12;
        int seconds = 604800;
        long expected = 55;
        long actual = controller.getTripGenerators(energy, seconds);
    }
}