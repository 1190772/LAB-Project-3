package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipBST;

/**
 * Controller of US102.
 *
 * @author David Magalhães 1201237
 */
public class SearchShipController {

    /**
     * The current ship binary search tree.
     */
    private final ShipBST shipBST;

    /**
     * Builds an instance of the Controller.
     */
    public SearchShipController() {
        shipBST = App.getInstance().getCompany().getShips();
    }

    /**
     * Sends ShipBST the code to be used to find the ship.
     *
     * @param code the code to search by.
     * @return a ship if found or null otherwise.
     */
    public Ship findShip(String code) {
        return shipBST.findShip(code);
    }
}
