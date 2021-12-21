package lapr.project.controller;

import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static lapr.project.model.shared.Utils.distanceBetweenTwoCoordinates;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BuildFreightNetworkControllerTest {

    @Test
    void buildFreightNetwork() {
        BuildFreightNetworkController controller = new BuildFreightNetworkController();
        ArrayList<Country> countries = new ArrayList<>();
        Country country1 = new Country("BZ","BLZ","Belize","Belmopan","America",397.6,17.25,-88.766667);
        Country country2 = new Country("MX","MEX","Mexico","Mexico City","America",128.9,19.43333333,-99.133333);
        countries.add(country1);
        countries.add(country2);
        ArrayList<Port> ports = new ArrayList<>();
        Port port1 = new Port(12345, "port1", "America", "BZ", 17.05, -88.3456373);
        Port port2 = new Port(23456, "port2", "America", "BZ", 14.36, -94.5874937);
        Port port3 = new Port(34567, "port3", "America", "MX", 17.65, -93.5572935);
        Port port4 = new Port(45678, "port4", "America", "MX", 19.21, -99.4094854);
        ports.add(port1);
        ports.add(port2);
        ports.add(port3);
        ports.add(port4);
        ArrayList<Border> borders = new ArrayList<>();
        borders.add(new Border(country1, country2));
        ArrayList<SeaDistance> seaDistances = new ArrayList<>();
        SeaDistance seaDistance1 = new SeaDistance(12345, 34567, 30001);
        SeaDistance seaDistance2 = new SeaDistance(23456, 34567, 30002);
        SeaDistance seaDistance3 = new SeaDistance(34567, 23456, 30003);
        SeaDistance seaDistance4 = new SeaDistance(45678, 23456, 30004);
        seaDistances.add(seaDistance1);
        seaDistances.add(seaDistance2);
        seaDistances.add(seaDistance3);
        seaDistances.add(seaDistance4);
        ArrayList<String> vs = new ArrayList<>();
        vs.add(country1.getCapital());
        vs.add(country2.getCapital());
        vs.add(port1.getName());
        vs.add(port2.getName());
        vs.add(port3.getName());
        vs.add(port4.getName());
        Integer[][] m = new Integer[vs.size()][vs.size()];
        m[0][1] = (int) distanceBetweenTwoCoordinates(country1.getLongitude(), country1.getLatitude(), country2.getLongitude(), country2.getLatitude());
        m[1][0] = m[0][1];
        m[2][3] = (int) distanceBetweenTwoCoordinates(port1.getLongitude(), port1.getLatitude(), port2.getLongitude(), port3.getLatitude());
        m[3][2] = m[2][3];
        m[4][5] = (int) distanceBetweenTwoCoordinates(port3.getLongitude(), port3.getLatitude(), port4.getLongitude(), port4.getLatitude());
        m[5][4] = m [4][5];
        m[0][2] = (int) distanceBetweenTwoCoordinates(country1.getLongitude(), country1.getLatitude(), port1.getLongitude(), port1.getLatitude());
        m[2][0] = m [0][1];
        m[1][5] = (int) distanceBetweenTwoCoordinates(country1.getLongitude(), country1.getLatitude(), port1.getLongitude(), port1.getLatitude());
        m[5][1] = m [1][5];
        m[2][4] = 3001;
        m[4][2] = m [2][4];
        m[3][4] = 3002;
        m[4][3] = m [3][4];
        m[4][3] = 3003;
        m[3][4] = m [4][3];
        m[5][3] = 3004;
        m[3][5] = m [5][3];

        FreightNetwork actual = controller.BuildFreightNetwork(countries, ports, borders, seaDistances, 1);
        FreightNetwork expected = new FreightNetwork(vs, m);
        assertEquals(expected, actual);
    }
}