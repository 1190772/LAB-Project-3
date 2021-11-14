package lapr.project.model;

import lapr.project.utils.AVL;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ShipBST extends AVL<Ship> {


    /**
     * Decides how to search for a ship using a provided code.
     *
     * @param code the code to search by.
     * @return a ship if found or null otherwise.
     */
    public Ship findShip(String code) {
        String type = determineCodeType(code);
        Ship res;

        if (type.equals("IMO")) {
            res = findShipByIMO(code);
        } else if (type.equals("MMSI")) {
            res = findShipByMMSI(root, code);
        } else {
            res = findShipByCallSign(root, code);
        }

        return res;
    }

    /**
     * Determines the type of a ship's code.
     *
     * @param code the code to determine the type of.
     * @return the type of the code.
     */
    private String determineCodeType(String code) {
        String type;

        if (code.startsWith("IMO"))
            type = "IMO";
        else if (code.length() == 9)
            type = "MMSI";
        else
            type = "CallSign";

        return type;
    }

    /**
     * Searches for a ship using its IMO code.
     *
     * @param code the code to search by.
     * @return a ship if found or null otherwise.
     */
    private Ship findShipByIMO(String code) {
        Node<Ship> node = root;
        Ship res = null;
        boolean find = false;

        while (node != null && !find) {
            if (node.getElement().getIMO().equals(code))
                find = true;
            else if (node.getElement().getIMO().compareTo(code) > 0)
                node = node.getLeft();
            else if (node.getElement().getIMO().compareTo(code) < 0)
                node = node.getRight();
        }

        if (find)
            res = node.getElement();

        return res;
    }

    /**
     * Searches for a ship using its MMSI code.
     *
     * @param node the current node.
     * @param code the code to search by.
     * @return a ship if found or null otherwise.
     */
    private Ship findShipByMMSI(Node<Ship> node, String code) {
        Ship res = null;

        if (node != null) {
            if (node.getElement().getMMSI().equals(code))
                res = node.getElement();
            if (res == null)
                res = findShipByMMSI(node.getLeft(), code);
            if (res == null)
                res = findShipByMMSI(node.getRight(), code);
        }
        return res;
    }

    /**
     * Searches for a ship using its Call Sign code.
     *
     * @param node the current node.
     * @param code the code to search by.
     * @return a ship if found or null otherwise.
     */
    private Ship findShipByCallSign(Node<Ship> node, String code) {
        Ship res = null;

        if (node != null) {
            if (node.getElement().getCallSign().equals(code))
                res = node.getElement();
            if (res == null)
                res = findShipByCallSign(node.getLeft(), code);
            if (res == null)
                res = findShipByCallSign(node.getRight(), code);
        }
        return res;
    }

    public ArrayList<Ship>[] topNShips(int n, LocalDateTime start, LocalDateTime end) {

        ArrayList<Ship>[] topNLists = new ArrayList[100];
        Iterator<Ship> list = inOrder().iterator();
        double distance;
        Ship ship;
        int i ;

        while (list.hasNext()) {
            ship = list.next();
            distance = ship.getPosition().travelledDistance(start,end);
            if (topNLists[ship.getVesselType()] == null)
                topNLists[ship.getVesselType()] = new ArrayList<>();
            if(topNLists[ship.getVesselType()].isEmpty()){
                topNLists[ship.getVesselType()].add(ship);;
            }
            else if(topNLists[ship.getVesselType()].size() < n){
                i = topNLists[ship.getVesselType()].size();
                while (i > 0 && distance > topNLists[ship.getVesselType()].get(i-1).getPosition().travelledDistance(start,end)) {
                    i--;
                }
                if (i < topNLists[ship.getVesselType()].size())
                    topNLists[ship.getVesselType()].add(i, ship);
            }else if(distance > topNLists[ship.getVesselType()].get(topNLists[ship.getVesselType()].size()-1).getPosition().travelledDistance(start, end)){
                topNLists[ship.getVesselType()].remove(topNLists[ship.getVesselType()].size()-1);
                    i = topNLists[ship.getVesselType()].size();
                    while (i > 0 && distance > topNLists[ship.getVesselType()].get(i-1).getPosition().travelledDistance(start,end)) {
                        i--;
                    }
                    if (i < topNLists[ship.getVesselType()].size())
                        topNLists[ship.getVesselType()].add(i, ship);
            }
        }
        return topNLists;
    }
}
