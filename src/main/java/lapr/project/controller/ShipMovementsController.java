package lapr.project.controller;


import lapr.project.model.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Samuel Pereira 1201274
 */
public class ShipMovementsController {
    /**
     * Returns all the details of the movements for a given ship
     *
     * @param shipCode MMSI, IMO, or CallSign to find the ship
     * @return all the details of the movements of a ship
     */
    public ShipMovementsAllDetails getAttributes(String shipCode) {
        Ship ship = new SearchShipController().findShip(shipCode);
        ShipPositionBST bst = ship.getPosition();
        ArrayList<ShipPosition> list = (ArrayList<ShipPosition>) bst.inOrder();
        return new ShipMovementsAllDetails(shipCode, ship.getName(), list.get(0), list.get(list.size() - 1), bst.totalMovementTime(), bst.totalNumberMovements(),
                bst.maxSOG(), bst.meanSOG(), bst.maxCOG(), bst.meanCOG(), bst.travelledDistance(), bst.deltaDistance());
    }

    /**
     * Returns a list with some details of the movements for each and all ships
     *
     * @param sortCode sort code
     * @return a list with some details of the movements for each and all ships
     */
    public ArrayList<ShipMovements> listAllShip(String sortCode) {
        ArrayList<ShipMovements> list = new ArrayList<>();
        for (Ship s : App.getInstance().getCompany().getShips().inOrder()) {
            ShipPositionBST bst = s.getPosition();
            list.add(new ShipMovements(s.getMMSI(), bst.totalNumberMovements(), bst.travelledDistance(), bst.deltaDistance()));
        }
        switch (sortCode) {
            case "travelledDistanceAsc":
                list = travelledDistanceAsc(list);
                break;
            case "travelledDistanceDesc":
                list = travelledDistanceDesc(list);
                break;
            case "deltaDistanceAsc":
                list = deltaDistanceAsc(list);
                break;
            case "deltaDistanceDesc":
                list = deltaDistanceDesc(list);
                break;
        }
        return list;
    }

    /**
     * Sorts a list os ShipMovements by ascending Travelled Distance
     *
     * @param list unsorted list
     * @return sorted list
     */
    private ArrayList<ShipMovements> travelledDistanceAsc(ArrayList<ShipMovements> list) {
        for (ShipMovements s : list)
            s.setCompareByTravelledDistance(true);
        Collections.sort(list);
        return list;
    }

    /**
     * Sorts a list os ShipMovements by descending Travelled Distance
     *
     * @param list unsorted list
     * @return sorted list
     */
    private ArrayList<ShipMovements> travelledDistanceDesc(ArrayList<ShipMovements> list) {
        return invertArrayList(travelledDistanceAsc(list));
    }

    /**
     * Sorts a list os ShipMovements by ascending Delta Distance
     *
     * @param list unsorted list
     * @return sorted list
     */
    private ArrayList<ShipMovements> deltaDistanceAsc(ArrayList<ShipMovements> list) {
        for (ShipMovements s : list)
            s.setCompareByTravelledDistance(false);
        Collections.sort(list);
        return list;
    }

    /**
     * Sorts a list os ShipMovements by descending Delta Distance
     *
     * @param list unsorted list
     * @return sorted list
     */
    private ArrayList<ShipMovements> deltaDistanceDesc(ArrayList<ShipMovements> list) {
        return invertArrayList(deltaDistanceAsc(list));
    }

    /**
     * Returns a list in its inverted order
     *
     * @param list list
     * @return list in its inverted order
     */
    private ArrayList<ShipMovements> invertArrayList(ArrayList<ShipMovements> list) {
        ShipMovements first = list.get(0);
        for (int i = 0; i<(list.size())/2; i++)
            Collections.swap(list, i, list.size()-1-i);
        return list;
    }
}
