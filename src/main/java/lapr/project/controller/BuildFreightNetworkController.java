package lapr.project.controller;

import lapr.project.model.Border;
import lapr.project.model.Country;
import lapr.project.model.Port;
import lapr.project.model.SeaDistance;
import lapr.project.utils.graph.MatrixGraph;

import java.util.ArrayList;

import static lapr.project.model.shared.Utils.distanceBetweenTwoCoordinates;

public class BuildFreightNetworkController {

    public MatrixGraph<String, Integer> BuildFreightNetwork(ArrayList<Country> countries, ArrayList<Port> ports, ArrayList<Border> borders, ArrayList<SeaDistance> seaDistances, int n) {
        ArrayList<String> vs = new ArrayList<>();
        Integer[][] m;
        int index1;
        int index2;
        int distance;

        for (Country country : countries) {
            vs.add(country.getCapital());
        }

        for (Port port : ports) {
            vs.add(port.getName());
        }

        m = new Integer[vs.size()][vs.size()];

        for (Border border : borders) {
            Country country1 = border.getCountry1();
            Country country2 = border.getCountry2();
            index1 = countries.indexOf(country1);
            index2 = countries.indexOf(country2);
            distance = (int) distanceBetweenTwoCoordinates(country1.getLongitude(), country1.getLatitude(), country2.getLongitude(), country2.getLatitude());
            m[index1][index2] = distance;
            m[index2][index1] = distance;
        }

        for (int i = 0; i < ports.size()-1; i++) {
            for (int j = i+1; j < ports.size(); j++) {
                Port port1 = ports.get(i);
                Port port2 = ports.get(j);
                if (ports.get(i).getCountry().equals(ports.get(j).getCountry())) {
                    index1 = countries.size() + ports.indexOf(port1);
                    index2 = countries.size() + ports.indexOf(port2);
                    distance = (int) distanceBetweenTwoCoordinates(port1.getLongitude(), port1.getLatitude(), port2.getLongitude(), port2.getLatitude());
                    m[index1][index2] = distance;
                    m[index2][index1] = distance;
                }
            }
        }


        return new MatrixGraph<>(false, vs, m);
    }
}
