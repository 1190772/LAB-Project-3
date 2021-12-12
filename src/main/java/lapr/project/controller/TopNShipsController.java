package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipBST;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Controller responsible for getting the top N ships with most kilometers travelled and their mean SOG.
 *
 * @author José Silva 1190772
 */
public class TopNShipsController {

    /**
     * The ship binary search tree.
     */
    private final ShipBST shipBST;

    /**
     * Builds an instance of the Controller.
     */
    public TopNShipsController() { shipBST = App.getInstance().getCompany().getShips(); }

    public void refreshShips() throws SQLException {
        App.getInstance().getCompany().refreshShips();
    }

    /**
     * Gets the top N ships with most kilometers travelled and their mean SOG grouped by VesselType in a period.
     *
     * @param n
     * @param start
     * @param end
     * @return
     */
    public ArrayList<Ship>[] topNShips(int n, LocalDateTime start, LocalDateTime end) {
        return shipBST.topNShips(n, start, end);
    }
}
