package lapr.project.controller;

import lapr.project.data.PortStoreDb;
import lapr.project.model.*;
import lapr.project.model.store.BorderStore;
import lapr.project.model.store.CountryStore;
import lapr.project.model.store.SeaDistanceStore;

import java.sql.SQLException;
import java.util.ArrayList;

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

    public void BuildFreightNetwork() {
        try {
            countryStore.refresh();
            port2DTree.loadPortsFromDatabase();
            borderStore.refresh();
            seaDistanceStore.refresh();

            App.getInstance().getCompany().setFreightNetwork(BuildFreightNetwork(
                            countryStore.countries,
                            (ArrayList<Port>) port2DTree.getAllPorts(),
                            borderStore.borders,
                            seaDistanceStore.seadists,
                        1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FreightNetwork BuildFreightNetwork(ArrayList<Country> countries, ArrayList<Port> ports, ArrayList<Border> borders, ArrayList<SeaDistance> seaDistances, int n) {
        ArrayList<String> vs = new ArrayList<>();
        Integer[][] m;
        int index1;
        int index2;
        int distance;
        int closestDistance = Integer.MAX_VALUE;
        Port closestPort = null;
        ArrayList<Port> closestPorts = new ArrayList<>();
        int index;

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
                if (port1.getCountry().equals(port2.getCountry())) {
                    index1 = countries.size() + i;
                    index2 = countries.size() + j;
                    distance = (int) distanceBetweenTwoCoordinates(port1.getLongitude(), port1.getLatitude(), port2.getLongitude(), port2.getLatitude());
                    m[index1][index2] = distance;
                    m[index2][index1] = distance;
                }
            }
        }

        for (Country country : countries) {
            for (Port port : ports) {
                if (port.getCountry().equals(country.getAlpha2_code())) {
                    distance = (int) distanceBetweenTwoCoordinates(port.getLongitude(), port.getLatitude(), country.getLongitude(), country.getLatitude());
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

        for (Port port1 : ports) {
            for (Country country : countries) {
                if (!port1.getCountry().equals(country.getAlpha2_code())) {
                    for (Port port2 : ports) {
                        if (port2.getCountry().equals(country.getAlpha2_code())) {
                            int i = 0;
                            distance = -1;
                            while (i < seaDistances.size() && distance == -1) {
                                if (seaDistances.get(i).getId_port1() == port1.getID() && seaDistances.get(i).getId_port2() == port2.getID())
                                    distance = seaDistances.get(i).getDistance();
                                i++;
                            }
                            if (distance != -1) {
                                index = closestPorts.size();
                                while (index >= 1 && distance < distanceBetweenTwoCoordinates(port1.getLongitude(), port1.getLatitude(), closestPorts.get(index - 1).getLongitude(), closestPorts.get(index - 1).getLatitude())) {
                                    index--;
                                }
                                closestPorts.add(index, port2);
                                if (closestPorts.size() > n) {
                                    closestPorts.remove(closestPorts.size()-1);
                                }
                            }
                        }
                    }
                }
            }
            for (Port port2 : closestPorts) {
                index1 = countries.size() + ports.indexOf(port1);
                index2 = countries.size() + ports.indexOf(port2);
                int i = 0;
                distance = -1;
                while (i < seaDistances.size() && distance == -1) {
                if (seaDistances.get(i).getId_port1() == port1.getID() && seaDistances.get(i).getId_port2() == port2.getID())
                    distance = seaDistances.get(i).getDistance();
                i++;
                }
                if (distance != -1) {
                    m[index1][index2] = distance;
                    m[index2][index1] = distance;
                }
            }
        }
        return new FreightNetwork(vs, m);
    }
}
