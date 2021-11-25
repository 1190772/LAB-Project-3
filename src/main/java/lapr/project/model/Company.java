package lapr.project.model;

import lapr.project.ui.auth.AuthFacade;
import lapr.project.utils.TwoDTree;

public class Company {
    private final String designation;


    private final AuthFacade authFacade;
    private final ShipBST shipBST;
    private final TwoDTree<Port> port2DTree;

    public Company(String designation) {
        this.designation = designation;
        shipBST = new ShipBST();
        port2DTree = new TwoDTree<>();
        this.authFacade = new AuthFacade();
    }

    public String getDesignation() {
        return designation;
    }

    public ShipBST getShips() {
        return shipBST;
    }

    public TwoDTree<Port> getPorts() {
    return port2DTree;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }
}
