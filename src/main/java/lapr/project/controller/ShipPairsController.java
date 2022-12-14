package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipBST;

import java.util.List;

/**
 * Controller responsible for finding pairs of ships.
 *
 * @author David Magalhães 1201237
 */
public class ShipPairsController {

    /**
     * The ship binary search tree.
     */
    private final ShipBST shipBST;

    /**
     * Builds an instance of the Controller.
     */
    public ShipPairsController() {
shipBST = App.getInstance().getCompany().getShips();
}

    public void refreshShips() {
        App.getInstance().getCompany().refreshShips();
    }

    public List<Ship[]> getShipPairs() {
        return shipBST.getShipPairs();
    }
}
