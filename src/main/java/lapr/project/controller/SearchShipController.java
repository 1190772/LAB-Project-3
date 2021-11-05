package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipBST;

import javax.naming.OperationNotSupportedException;

/**
 * Controller of US102.
 *
 * @author David Magalhães 1201237
 */
public class SearchShipController {

    /**
     * The instance of the domain class associated to this controller.
     */
    private final ShipBST shipBST;

    /**
     * Builds an instance of the Controller.
     */
    public SearchShipController() {
        shipBST = App.getInstance().getCompany().getShips();
    }

    /**
     * Sends searchShip the code to be used to find the ship
     *
     * @param code the code to search by
     *
     * @return a ship if found or null otherwise.
     */
    //public Ship findShip(String code) throws OperationNotSupportedException {
        //return shipBST.findShip(code);
    //}
}
