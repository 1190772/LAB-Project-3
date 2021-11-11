package lapr.project.controller;


import lapr.project.model.Ship;
import lapr.project.model.ShipPositionBST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShipMovementsAndDistance {

    private Ship ship;
    ShipPositionBST shipPositionBST = new ShipPositionBST();
    Map<String, ArrayList<String>> shipMap;
    ArrayList<Ship> shipList;

    public ShipMovementsAndDistance(){
        shipMap = new HashMap<>();
        shipList = (ArrayList<Ship>) App.getInstance().getCompany().getShips().inOrder();
    }




}
