package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipBST;
import lapr.project.model.ShipPosition;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
     * Holder of the selected ship.
     */
    private Ship ship;

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
        ship = shipBST.findShip(code);
        return ship;
    }

    public ArrayList<ShipPosition> getPositions(LocalDateTime startDate, LocalDateTime endDate) {
        return ship.getPositions(startDate, endDate);
    }
}
