package lapr.project.controller;

import lapr.project.model.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShortestPathControllerTest {
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
    private List<Object> path;
    private List<Object> stops;
    private final ArrayList<Object> vertices;


    public ShortestPathControllerTest() {
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
        freightNetwork =  new BuildFreightNetworkController().buildFreightNetwork(countries, ports, borders, seaDistances, 0);
        App.getInstance().getCompany().setFreightNetwork(freightNetwork);
        vertices = freightNetwork.vertices();

        path=new LinkedList<>();
    }

    @Test
    void invalidLists() {
        stops = new ArrayList<>();
        stops.add(vertices.get(0));
        stops.add(vertices.get(2));
        path = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new ShortestPathController().shortestPathLand(stops, path));
        stops = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new ShortestPathController().shortestPathLand(stops, path));
        path = null;
        assertThrows(IllegalArgumentException.class, () -> new ShortestPathController().shortestPathLand(stops, path));
        stops = null;
        assertThrows(IllegalArgumentException.class, () -> new ShortestPathController().shortestPathLand(stops, path));
    }

    @Test
    void shortestPathLandTest() {
        stops = new ArrayList<>();
        stops.add(vertices.get(0));
        stops.add(vertices.get(2));
        stops.add(vertices.get(5));
        assertEquals(1258614, new ShortestPathController().shortestPathLand(stops, path));
        assertEquals("[Country = Belmopan, Port = port1, Country = Belmopan, Country = Mexico City, Port = port4]", path.toString());
        stops.add(vertices.get(3));
        assertEquals(0, new ShortestPathController().shortestPathLand(stops, path));
        assertTrue(path.isEmpty());

    }

    @Test
    void shortestPathSeaTest() {
        stops = new ArrayList<>();
        stops.add(vertices.get(2));
        stops.add(vertices.get(3));
        stops.add(vertices.get(2));
        assertEquals(20000, new ShortestPathController().shortestPathSea(stops, path));
        assertEquals("[Port = port1, Port = port2, Port = port1]", path.toString());
        stops.add(vertices.get(4));
        assertEquals(0, new ShortestPathController().shortestPathSea(stops, path));
        assertTrue(path.isEmpty());
    }

    @Test
    void shortestPathLandSeaTest() {
        stops = new ArrayList<>();
        stops.add(vertices.get(0));
        stops.add(vertices.get(2));
        stops.add(vertices.get(3));
        stops.add(vertices.get(5));
        assertEquals(1278614, new ShortestPathController().shortestPathLandSea(stops, path));
        assertEquals("[Country = Belmopan, Port = port1, Port = port2, Port = port1, Country = Belmopan, Country = Mexico City, Port = port4]", path.toString());
    }
}
