package lapr.project.controller;

import lapr.project.model.Border;
import lapr.project.model.Country;
import lapr.project.model.Port;
import lapr.project.model.SeaDistance;
import lapr.project.utils.graph.MatrixGraph;
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
        Port port1 = new Port(12345, "name1", "America", "BZ", 18.05, -89.3456373);
        Port port2 = new Port(23456, "name2", "America", "BZ", 16.36, -87.5874937);
        Port port3 = new Port(34567, "name3", "America", "MX", 20.65, -98.5572935);
        Port port4 = new Port(45678, "name4", "America", "MX", 18.21, -100.4094854);
        ports.add(port1);
        ports.add(port2);
        ports.add(port3);
        ports.add(port4);
        ArrayList<Border> borders = new ArrayList<>();
        borders.add(new Border(country1, country2));
        ArrayList<SeaDistance> seaDistances = new ArrayList<>();
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

        MatrixGraph<String, Integer> actual = controller.BuildFreightNetwork(countries, ports, borders, seaDistances, 1);
        MatrixGraph<String, Integer> expected = new MatrixGraph<>(false, vs, m);
        assertEquals(expected, actual);
    }
}