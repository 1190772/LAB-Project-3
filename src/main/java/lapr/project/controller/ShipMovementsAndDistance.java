package lapr.project.controller;


import lapr.project.model.Ship;
import lapr.project.model.ShipPositionBST;

import java.lang.reflect.Array;
import java.util.*;

public class ShipMovementsAndDistance {

    private Ship ship;
    ShipPositionBST shipPositionBST = new ShipPositionBST();
    Map<String, ArrayList<Double>> shipMap;
    ArrayList<Ship> shipList;

    public ShipMovementsAndDistance(){
        shipMap = new HashMap<>();
        shipList = (ArrayList<Ship>) App.getInstance().getCompany().getShips().inOrder();
        for (Ship ship: shipList)
              {
                  ShipPositionBST shipPositionBST1 = ship.getPosition();
            shipMap.put(ship.getMMSI(),new ArrayList<>());
            shipMap.get(ship.getMMSI()).add((double)shipPositionBST1.totalNumberMovements());
            shipMap.get(ship.getMMSI()).add(shipPositionBST1.travelledDistance());
            shipMap.get(ship.getMMSI()).add(shipPositionBST1.deltaDistance());

        }
    }

    public Map<String, ArrayList<Double>> getShipMap() {
        Map<String,ArrayList<Double>> shipMap1;
        for (String mmsi:shipMap.keySet()
             ) {
            shipMap.get(mmsi).get(0)
        }
        return shipMap;
    }
}
