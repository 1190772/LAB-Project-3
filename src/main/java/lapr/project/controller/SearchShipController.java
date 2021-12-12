package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipBST;
import lapr.project.model.ShipPosition;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller responsible for coordinating ship searches.
 *
 * @author David Magalhães 1201237
 */
public class SearchShipController {

    /**
     * The ship binary search tree.
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

    public void refreshShips() throws SQLException {
        App.getInstance().getCompany().refreshShips();
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

    /**
     * Sends Ship an interval of dates to search positions.
     *
     * @param startDate the start date.
     * @param endDate the end date.
     *
     * @return a list of positions.
     */
    public List<ShipPosition> getPositions(LocalDateTime startDate, LocalDateTime endDate) {
        return ship.getPositions(startDate, endDate);
    }
}
