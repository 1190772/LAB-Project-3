package lapr.project.model;

import lapr.project.ui.auth.AuthFacade;

public class Company {
    private final String designation;


    private final AuthFacade authFacade;
    private final ShipBST shipBST;

    public Company(String designation) {
        this.designation = designation;
        shipBST = new ShipBST();
        this.authFacade = new AuthFacade();
    }

    public String getDesignation() {
        return designation;
    }

    public ShipBST getShips() {
        return shipBST;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }
}
