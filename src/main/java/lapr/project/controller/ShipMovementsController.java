package lapr.project.controller;


import lapr.project.model.Ship;
import lapr.project.model.ShipMovements;
import lapr.project.model.ShipPosition;
import lapr.project.model.ShipPositionBST;

import java.util.ArrayList;

public class ShipMovementsController {
    public ShipMovements getAttributes(String shipCode) {
        Ship ship = new SearchShipController().findShip(shipCode);
        ShipPositionBST bst = ship.getPosition();
        ArrayList<ShipPosition> list = (ArrayList<ShipPosition>) bst.inOrder();
        return new ShipMovements(shipCode, ship.getName(), list.get(0), list.get(list.size()-1), bst.totalMovementTime(), bst.totalNumberMovements(),
                bst.maxSOG(), bst.meanSOG(), bst.maxCOG(), bst.meanCOG(), bst.travelledDistance(), bst.deltaDistance());
    }
}
