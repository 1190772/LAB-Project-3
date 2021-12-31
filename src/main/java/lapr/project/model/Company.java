package lapr.project.model;

import lapr.project.model.store.BorderStore;
import lapr.project.model.store.CountryStore;
import lapr.project.model.store.SeaDistanceStore;
import lapr.project.ui.auth.AuthFacade;

public class Company {
    private final String designation;

    private final AuthFacade authFacade;
    private final ShipBST shipBST;
    private final Port2DTree port2DTree;
    private FreightNetwork freightNetwork;
    private final BorderStore borderStore;
    private final CountryStore countryStore;
    private final SeaDistanceStore seaDistanceStore;

    public Company(String designation) {
        this.designation = designation;
        shipBST = new ShipBST();
        port2DTree = new Port2DTree();
        this.authFacade = new AuthFacade();
        freightNetwork = null;
        borderStore = new BorderStore();
        countryStore = new CountryStore();
        seaDistanceStore = new SeaDistanceStore();
    }

    public String getDesignation() {
        return designation;
    }

    public ShipBST getShips() { return shipBST; }

    public void refreshShips() { shipBST.loadShipsFromDatabase(); }

    public Port2DTree getPorts() {
    return port2DTree;
    }

    public void refreshPorts() {
        port2DTree.loadPortsFromDatabase();
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public FreightNetwork getFreightNetwork() { return freightNetwork; }

    public void setFreightNetwork(FreightNetwork freightNetwork) { this.freightNetwork = freightNetwork; }

    public BorderStore getBorderStore() { return borderStore; }

    public CountryStore getCountryStore() { return countryStore; }

    public SeaDistanceStore getSeadistanceStore() { return seaDistanceStore; }
}
