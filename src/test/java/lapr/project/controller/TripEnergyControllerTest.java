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
        List<Double> temperatureVariations = new ArrayList<>(Arrays.asList(15.0, 25.0));
        List<Integer> duration = new ArrayList<>(Arrays.asList(4500, 4500));
        containers.put(topArea, new ArrayList<>(Arrays.asList(new Pair<>(7, 4), new Pair<>(-5, 6))));
        containers.put(0.0, new ArrayList<>(Arrays.asList(new Pair<>(-5, 6))));
        containers.put(doorArea, new ArrayList<>(Arrays.asList(new Pair<>(-5, 3), new Pair<>(7, 3))));
        containers.put(sideArea+doorArea, new ArrayList<>(Arrays.asList(new Pair<>(7,4), new Pair<>(7,6))));
        containers.put(sideArea+topArea, new ArrayList<>(Arrays.asList(new Pair<>(7,4))));
        containers.put(doorArea+topArea+sideArea, new ArrayList<>(Arrays.asList(new Pair<>(7,4))));

        Assertions.assertEquals(2.2050369E8, new TripEnergyController().getTripEnergy(containers, temperatureVariations, duration));
    }
}
