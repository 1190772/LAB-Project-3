package lapr.project.model;

import lapr.project.ui.auth.AuthFacade;

import java.sql.SQLException;

public class Company {
    private final String designation;

    private final AuthFacade authFacade;
    private final ShipBST shipBST;
    private final Port2DTree port2DTree;
    private FreightNetwork freightNetwork;

    public Company(String designation) {
        this.designation = designation;
        shipBST = new ShipBST();
        port2DTree = new Port2DTree();
        this.authFacade = new AuthFacade();
        freightNetwork = null;
    }

    public String getDesignation() {
        return designation;
    }

    public ShipBST getShips() { return shipBST; }

    public void refreshShips() throws SQLException {
        shipBST.loadShipsFromDatabase();
    }

    public Port2DTree getPorts() {
    return port2DTree;
    }

    public void refreshPorts() {
        try {
            port2DTree.loadPortsFromDatabase();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public FreightNetwork getFreightNetwork() { return freightNetwork; }

    public void setFreightNetwork(FreightNetwork freightNetwork) { this.freightNetwork = freightNetwork; }
}
