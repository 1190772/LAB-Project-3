package lapr.project.controller;

import oracle.ucp.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TripEnergyControllerTest {
    @Test
    void tripEnergyTest() {
        double sideArea = 6.10*2.44;
        double topArea = 6.10*2.44;
        double doorArea = 2.44*2.44;
        Map<Double, List<Pair<Integer, Integer>>> containers = new HashMap<>();
        List<Double> temperatureVariations = new ArrayList<>(Arrays.asList(20.0, 22.0));
        List<Integer> duration = new ArrayList<>(Arrays.asList(3600, 3600));
        containers.put(2*sideArea+2*topArea+2*doorArea, new ArrayList<>(Arrays.asList(new Pair<>(-5, 2))));
        containers.put(0.0, new ArrayList<>(Arrays.asList(new Pair<>(7, 3))));

        Assertions.assertEquals(2.9330928E7, new TripEnergyController().getTripEnergy(containers, temperatureVariations, duration));
    }
}
