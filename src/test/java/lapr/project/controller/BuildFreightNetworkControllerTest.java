package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static lapr.project.model.shared.Utils.distanceBetweenTwoCoordinates;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BuildFreightNetworkControllerTest {

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

    public BuildFreightNetworkControllerTest() {
        countries = new ArrayList<>();
        country1 = new Country("BZ","BLZ","Belize","Belmopan","America",397.6,17.25,-88.766667);
        country2 = new Country("MX","MEX","Mexico","Mexico City","America",128.9,19.43333333,-99.133333);
        countries.add(country1);
        countries.add(country2);
        ports = new ArrayList<>();
        port1 = new Port(12345, "port1", country1, 17.05, -88.3456373, 0);
        port2 = new Port(23456, "port2", country1, 18.36, -89.5874937, 0);
        port3 = new Port(34567, "port3", country2, 18.65, -98.5572935, 0);
        port4 = new Port(45678, "port4", country2, 19.21, -99.4094854, 0);
        ports.add(port1);
        ports.add(port2);
        ports.add(port3);
        ports.add(port4);
        borders = new ArrayList<>();
        border1 = new Border(country1, country2);
        borders.add(border1);
        seaDistances = new ArrayList<>();
        seaDistance1 = new SeaDistance(12345, 34567, 27500);
        seaDistance2 = new SeaDistance(23456, 34567, 20000);
        seaDistance3 = new SeaDistance(45678, 23456, 25000);
        seaDistance4 = new SeaDistance(12345, 45678, 30000);
        seaDistance5 = new SeaDistance(12345, 23456, 10000);
        seaDistance6 = new SeaDistance(34567, 45678, 10000);
        seaDistances.add(seaDistance1);
        seaDistances.add(seaDistance2);
        seaDistances.add(seaDistance3);
        seaDistances.add(seaDistance4);
        seaDistances.add(seaDistance5);
        seaDistances.add(seaDistance6);
    }

    @Test
    void buildFreightNetwork0() {
        BuildFreightNetworkController controller = new BuildFreightNetworkController();
        ArrayList<Object> vs = new ArrayList<>();
        vs.add(country1);
        vs.add(country2);
        vs.add(port1);
        vs.add(port2);
        vs.add(port3);
        vs.add(port4);
        Long[][] m = new Long[vs.size()][vs.size()];
        m[0][1] = (long) distanceBetweenTwoCoordinates(country1.getLongitude(), country1.getLatitude(), country2.getLongitude(), country2.getLatitude());
        m[1][0] = m[0][1];
        m[2][3] = (long) seaDistance5.getDistance();
        m[3][2] = m[2][3];
        m[4][5] = (long) seaDistance6.getDistance();
        m[5][4] = m [4][5];
        m[0][2] = (long) distanceBetweenTwoCoordinates(country1.getLongitude(), country1.getLatitude(), port1.getLongitude(), port1.getLatitude());
        m[2][0] = m [0][2];
        m[1][5] = (long) distanceBetweenTwoCoordinates(country2.getLongitude(), country2.getLatitude(), port4.getLongitude(), port4.getLatitude());
        m[5][1] = m [1][5];

        FreightNetwork actual = controller.buildFreightNetwork(countries, ports, borders, seaDistances, 0);
        FreightNetwork expected = new FreightNetwork(vs, m);
        assertEquals(expected, actual);
    }

    @Test
    void buildFreightNetwork1() {
        BuildFreightNetworkController controller = new BuildFreightNetworkController();
        ArrayList<Object> vs = new ArrayList<>();
        vs.add(country1);
        vs.add(country2);
        vs.add(port1);
        vs.add(port2);
        vs.add(port3);
        vs.add(port4);
        Long[][] m = new Long[vs.size()][vs.size()];
        m[0][1] = (long) distanceBetweenTwoCoordinates(country1.getLongitude(), country1.getLatitude(), country2.getLongitude(), country2.getLatitude());
        m[1][0] = m[0][1];
        m[2][3] = (long) seaDistance5.getDistance();
        m[3][2] = m[2][3];
        m[4][5] = (long) seaDistance6.getDistance();
        m[5][4] = m [4][5];
        m[0][2] = (long) distanceBetweenTwoCoordinates(country1.getLongitude(), country1.getLatitude(), port1.getLongitude(), port1.getLatitude());
        m[2][0] = m [0][2];
        m[1][5] = (long) distanceBetweenTwoCoordinates(country2.getLongitude(), country2.getLatitude(), port4.getLongitude(), port4.getLatitude());
        m[5][1] = m [1][5];
        m[2][4] = (long) seaDistance1.getDistance();
        m[4][2] = m [2][4];
        m[3][4] = (long) seaDistance2.getDistance();
        m[4][3] = m [3][4];
        m[3][5] = (long) seaDistance3.getDistance();
        m[5][3] = m [3][5];

        FreightNetwork actual = controller.buildFreightNetwork(countries, ports, borders, seaDistances, 1);
        FreightNetwork expected = new FreightNetwork(vs, m);
        assertEquals(expected, actual);
    }

    @Test
    void buildFreightNetwork2() {
        BuildFreightNetworkController controller = new BuildFreightNetworkController();
        ArrayList<Object> vs = new ArrayList<>();
        vs.add(country1);
        vs.add(country2);
        vs.add(port1);
        vs.add(port2);
        vs.add(port3);
        vs.add(port4);
        Long[][] m = new Long[vs.size()][vs.size()];
        m[0][1] = (long) distanceBetweenTwoCoordinates(country1.getLongitude(), country1.getLatitude(), country2.getLongitude(), country2.getLatitude());
        m[1][0] = m[0][1];
        m[2][3] = (long) seaDistance5.getDistance();
        m[3][2] = m[2][3];
        m[4][5] = (long) seaDistance6.getDistance();
        m[5][4] = m [4][5];
        m[0][2] = (long) distanceBetweenTwoCoordinates(country1.getLongitude(), country1.getLatitude(), port1.getLongitude(), port1.getLatitude());
        m[2][0] = m [0][2];
        m[1][5] = (long) distanceBetweenTwoCoordinates(country2.getLongitude(), country2.getLatitude(), port4.getLongitude(), port4.getLatitude());
        m[5][1] = m [1][5];
        m[2][4] = (long) seaDistance1.getDistance();
        m[4][2] = m [2][4];
        m[3][4] = (long) seaDistance2.getDistance();
        m[4][3] = m [3][4];
        m[5][3] = (long) seaDistance3.getDistance();
        m[3][5] = m [5][3];
        m[2][5] = (long) seaDistance4.getDistance();
        m[5][2] = m [2][5];

        FreightNetwork actual = controller.buildFreightNetwork(countries, ports, borders, seaDistances, 2);
        FreightNetwork expected = new FreightNetwork(vs, m);
        assertEquals(expected, actual);
    }
}