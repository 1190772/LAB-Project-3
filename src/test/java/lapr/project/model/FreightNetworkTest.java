package lapr.project.model;

import lapr.project.controller.BuildFreightNetworkController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import oracle.ucp.util.Pair;

import static org.junit.jupiter.api.Assertions.*;

class FreightNetworkTest {

    private final ArrayList<Country> countries;
    private final Country country1;
    private final Country country2;
    private final ArrayList<Port> ports;
    private final Port port1;
    private final Port port2;
    private final Port port3;
    private final Port port4;
    private final ArrayList<Border> borders;
    private final Border border1;
    private final ArrayList<SeaDistance> seaDistances;
    private final SeaDistance seaDistance1;
    private final SeaDistance seaDistance2;
    private final SeaDistance seaDistance3;
    private final SeaDistance seaDistance4;
    private final SeaDistance seaDistance5;
    private final SeaDistance seaDistance6;
    private final FreightNetwork freightNetwork;

    public FreightNetworkTest() {
        countries = new ArrayList<>();
        country1 = new Country("BZ","BLZ","Belize","Belmopan","America",397.6,17.25,-88.766667);
        country2 = new Country("MX","MEX","Mexico","Mexico City","America",128.9,19.43333333,-99.133333);
        countries.add(country1);
        countries.add(country2);
        ports = new ArrayList<>();
        port1 = new Port("12345", "port1", country1, 17.05, -88.3456373, 0);
        port2 = new Port("23456", "port2", country1, 18.36, -89.5874937, 0);
        port3 = new Port("34567", "port3", country2, 18.65, -98.5572935, 0);
        port4 = new Port("45678", "port4", country2, 19.21, -99.4094854, 0);
        ports.add(port1);
        ports.add(port2);
        ports.add(port3);
        ports.add(port4);
        borders = new ArrayList<>();
        border1 = new Border(country1, country2);
        borders.add(border1);
        seaDistances = new ArrayList<>();
        seaDistance1 = new SeaDistance("12345", "34567", 27500);
        seaDistance2 = new SeaDistance("23456", "34567", 20000);
        seaDistance3 = new SeaDistance("45678", "23456", 25000);
        seaDistance4 = new SeaDistance("12345", "45678", 30000);
        seaDistance5 = new SeaDistance("12345", "23456", 10000);
        seaDistance6 = new SeaDistance("34567", "45678", 10000);
        seaDistances.add(seaDistance1);
        seaDistances.add(seaDistance2);
        seaDistances.add(seaDistance3);
        seaDistances.add(seaDistance4);
        seaDistances.add(seaDistance5);
        seaDistances.add(seaDistance6);
        freightNetwork =  new BuildFreightNetworkController().buildFreightNetwork(countries, ports, borders, seaDistances, 1);
    }

    @Test
    void findVertexByName() {
        BuildFreightNetworkController controller = new BuildFreightNetworkController();
        FreightNetwork freightNetwork = controller.buildFreightNetwork(countries, ports, borders, seaDistances, 0);
        Assertions.assertEquals(country1, freightNetwork.findVertexByName(country1.getName()));
        Assertions.assertEquals(country2, freightNetwork.findVertexByName(country2.getName()));
        Assertions.assertNull(freightNetwork.findVertexByName("Invalid Name"));
    }

    @Test
    void getMostEfficientCircuit() {
        LinkedList<FreightNetworkVertex> expectedCircuit = new LinkedList<>();
        int expectedDistance = 1248657;
        Pair<LinkedList<FreightNetworkVertex>, Integer> actual;
        expectedCircuit.add(country1);
        expectedCircuit.add(port1);
        expectedCircuit.add(port2);
        expectedCircuit.add(port3);
        expectedCircuit.add(port4);
        expectedCircuit.add(country2);
        expectedCircuit.add(country1);
        actual = freightNetwork.getMostEfficientCircuit("Belize");
        Assertions.assertEquals(expectedCircuit, actual.get1st());
        Assertions.assertEquals(expectedDistance, actual.get2nd());
    }

    @Test
    void testToString() {
        String expected = "Vertices:\n" +
                "Country = Belmopan\n" +
                "Country = Mexico City\n" +
                "Port = port1\n" +
                "Port = port2\n" +
                "Port = port3\n" +
                "Port = port4\n" +
                "\n" +
                "Matrix:\n" +
                "    |  0  |  1  |  2  |  3  |  4  |  5 \n" +
                " 0\t|     |  X  |  X  |     |     |     \n" +
                " 1\t|  X  |     |     |     |     |  X  \n" +
                " 2\t|  X  |     |     |  X  |  X  |     \n" +
                " 3\t|     |     |  X  |     |  X  |  X  \n" +
                " 4\t|     |     |  X  |  X  |     |  X  \n" +
                " 5\t|     |  X  |     |  X  |  X  |     \n" +
                "\n" +
                "\n";
        Assertions.assertEquals(expected, freightNetwork.toString());
    }
}