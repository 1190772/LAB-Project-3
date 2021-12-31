package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.model.store.BorderStore;
import lapr.project.model.store.CountryStore;
import lapr.project.model.store.SeaDistanceStore;

import java.util.ArrayList;
import java.util.List;

import static lapr.project.model.shared.Utils.distanceBetweenTwoCoordinates;

public class BuildFreightNetworkController {

    private final CountryStore countryStore;
    private final Port2DTree port2DTree;
    private final BorderStore borderStore;
    private final SeaDistanceStore seaDistanceStore;

    public BuildFreightNetworkController() {
        Company company = App.getInstance().getCompany();
        countryStore = company.getCountryStore();
        port2DTree = company.getPorts();
        borderStore = company.getBorderStore();
        seaDistanceStore = company.getSeadistanceStore();
    }

    public void buildFreightNetwork() {
            countryStore.refresh();
            port2DTree.loadPortsFromDatabase();
            borderStore.refresh();
            seaDistanceStore.refresh();

            App.getInstance().getCompany().setFreightNetwork(buildFreightNetwork(
                            countryStore.getCountries(),
                            port2DTree.getAllPorts(),
                            borderStore.getBorders(),
                            seaDistanceStore.getSeadists(),
                        1));
    }

    public FreightNetwork buildFreightNetwork(List<Country> countries, List<Port> ports, List<Border> borders, List<SeaDistance> seaDistances, int n) {
        ArrayList<Object> vs = new ArrayList<>();
        Long[][] m;
        int index1;
        int index2;
        long distance;
        long closestDistance;
        Port closestPort;
        ArrayList<Port> closestPorts = new ArrayList<>();
        ArrayList<Long> closestDistances = new ArrayList<>();
        int index;

        vs.addAll(countries);
        vs.addAll(ports);

        m = new Long[vs.size()][vs.size()];

        // The capital of a country has a direct connection with the capitals of the countries with which it borders
        for (Border border : borders) {
            Country country1 = border.getCountry1();
            Country country2 = border.getCountry2();
            index1 = countries.indexOf(country1);
            index2 = countries.indexOf(country2);
            distance = (long) distanceBetweenTwoCoordinates(country1.getLongitude(), country1.getLatitude(), country2.getLongitude(), country2.getLatitude());
            m[index1][index2] = distance;
            m[index2][index1] = distance;
        }

        // The ports of a country connect with all the ports of the same country
        for (int i = 0; i < ports.size()-1; i++) {
            for (int j = i+1; j < ports.size(); j++) {
                Port port1 = ports.get(i);
                Port port2 = ports.get(j);
                if (port1.getCountry().equals(port2.getCountry())) {
                    index1 = countries.size() + i;
                    index2 = countries.size() + j;
                    int g = 0;
                    distance = -1;
                    while (g < seaDistances.size() && distance == -1) {
                        if (seaDistances.get(g).getIdPort1() == port1.getID() && seaDistances.get(g).getIdPort2() == port2.getID() || seaDistances.get(g).getIdPort1() == port2.getID() && seaDistances.get(g).getIdPort2() == port1.getID())
                            distance = seaDistances.get(g).getDistance();
                        g++;
                    }
                    if (distance != -1) {
                        m[index1][index2] = distance;
                        m[index2][index1] = distance;
                    }
                }
            }
        }

        // the port closest to the capital of the country connects with it
        for (Country country : countries) {
            closestDistance = Integer.MAX_VALUE;
            closestPort = null;
            for (Port port : ports) {
                if (port.getCountry().equals(country)) {
                    distance = (long) distanceBetweenTwoCoordinates(port.getLongitude(), port.getLatitude(), country.getLongitude(), country.getLatitude());
                    if (distanceBetweenTwoCoordinates(port.getLongitude(), port.getLatitude(), country.getLongitude(), country.getLatitude()) < closestDistance) {
                        closestPort = port;
                        closestDistance = distance;
                    }
                }
            }
            index1 = countries.size()+ports.indexOf(closestPort);
            index2 = countries.indexOf(country);
            m[index1][index2] = closestDistance;
            m[index2][index2] = closestDistance;
        }

        //each port of a country connects with the n closest ports of any other country
        if (n > 0) {
            for (Port port1 : ports) {
                for (Country country : countries) {
                    if (!port1.getCountry().equals(country)) {
                        for (Port port2 : ports) {
                            if (port2.getCountry().equals(country) && port2.getID() != port1.getID()) {
                                int i = 0;
                                distance = -1;
                                while (i < seaDistances.size() && distance == -1) {
                                    if (seaDistances.get(i).getIdPort1() == port1.getID() && seaDistances.get(i).getIdPort2() == port2.getID() || seaDistances.get(i).getIdPort1() == port2.getID() && seaDistances.get(i).getIdPort2() == port1.getID())
                                        distance = seaDistances.get(i).getDistance();
                                    i++;
                                }
                                if (distance != -1) {
                                    index = closestPorts.size();
                                    while (index >= 1 && distance < closestDistances.get(index - 1)) {
                                        index--;
                                    }
                                    closestDistances.add(index, distance);
                                    closestPorts.add(index, port2);
                                    if (closestPorts.size() > n) {
                                        closestPorts.remove(closestPorts.size() - 1);
                                        closestDistances.remove(closestDistances.size() - 1);
                                    }
                                }
                            }
                        }
                        for (int i = 0; i < closestPorts.size(); i++) {
                            index1 = countries.size() + ports.indexOf(port1);
                            index2 = countries.size() + ports.indexOf(closestPorts.get(i));
                            distance = closestDistances.get(i);
                            m[index1][index2] = distance;
                            m[index2][index1] = distance;
                        }
                        closestPorts.clear();
                        closestDistances.clear();
                    }
                }
            }
        }
        return new FreightNetwork(vs, m);
    }
}
