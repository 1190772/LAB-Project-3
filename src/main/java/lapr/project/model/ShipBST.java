package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.data.ShipStoreDb;
import lapr.project.model.shared.Utils;
import lapr.project.utils.AVL;
import lapr.project.utils.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;


public class ShipBST extends AVL<Ship> {

    private final ShipStoreDb shipStoreDb;

    public ShipBST() {
        super();
        shipStoreDb = new ShipStoreDb();
    }

    public void loadShipsFromDatabase() throws SQLException {
        DatabaseConnection connection = App.getInstance().getSql().getDatabaseConnection();
        ResultSet ships = shipStoreDb.getAllShips(connection);
        while (ships.next()) {
            Ship ship = new Ship(ships.getString("mmsi_code"),
                    ships.getString("name_ship"),
                    ships.getString("imo_code"),
                    ships.getInt("number_generators"),
                    ships.getInt("power_out_generator"),
                    ships.getString("call_sign"),
                    ships.getInt("vessel_type"),
                    ships.getInt("length_ship"),
                    ships.getInt("width_ship"),
                    ships.getInt("capacity_ship"),
                    ships.getFloat("draft"));
            insert(ship);
            ResultSet positions = shipStoreDb.getShipPostions(connection, ships.getString("imo_code"));
            while (positions.next()) {
            ship.addPosition(new ShipPosition(positions.getTimestamp("base_date_time").toLocalDateTime(),
                    positions.getDouble("latitude"),
                    positions.getDouble("longitude"),
                    positions.getDouble("sog"),
                    positions.getDouble("cog"),
                    positions.getInt("heading"),
                    positions.getString("transceiver_class").charAt(0)));
                    //positions.getInt("cargo")));
            }
            positions.close();
        }
        ships.close();
    }

    public void saveShipsToDb() {
        DatabaseConnection connection = App.getInstance().getSql().getDatabaseConnection();
        saveShipsToDb(root, connection);
    }

    private void saveShipsToDb(Node<Ship> node, DatabaseConnection connection) {
        if (node == null)
            return;
        saveShipsToDb(node.getLeft(), connection);
        shipStoreDb.save(connection, node.getElement());
        saveShipsToDb(node.getRight(), connection);
    }

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
                topNLists[ship.getVesselType()].add(ship);
            }
            else if(topNLists[ship.getVesselType()].size() < n){
                i = topNLists[ship.getVesselType()].size();
                while (i > 0 && distance > topNLists[ship.getVesselType()].get(i-1).getPosition().travelledDistance(start,end)) {
                    i--;
                }
                if (i < topNLists[ship.getVesselType()].size())
                    topNLists[ship.getVesselType()].add(i, ship);
            }else if(distance > topNLists[ship.getVesselType()].get(topNLists[ship.getVesselType()].size()-1).getPosition().travelledDistance(start, end)){
                i = topNLists[ship.getVesselType()].size();
                while (i > 0 && distance > topNLists[ship.getVesselType()].get(i-1).getPosition().travelledDistance(start,end)) {
                    i--;
                }
                if (i < topNLists[ship.getVesselType()].size())
                    topNLists[ship.getVesselType()].add(i, ship);
                topNLists[ship.getVesselType()].remove(topNLists[ship.getVesselType()].size()-1);
            }
        }
        return topNLists;
    }

    public List<Ship[]> getShipPairs() {
        ArrayList<Ship[]> res = new ArrayList<>();
        TreeMap<Ship, Double> travelledDistances = new TreeMap<>();
        TreeMap<Ship, ShipPosition> startPositions = new TreeMap<>();
        TreeMap<Ship, ShipPosition> endPositions = new TreeMap<>();
        Ship[] ships;
        int index;

        getPositions(startPositions, endPositions, root);

        ships = startPositions.keySet().toArray(new Ship[0]);

        for (Ship ship : ships)
           travelledDistances.put(ship, ship.getPosition().travelledDistance());


        for (int i = 0; i < ships.length; i++) {
            for (int j = i+1; j < ships.length; j++) {
                if (Utils.distanceBetweenTwoCoordinates(startPositions.get(ships[i]), startPositions.get(ships[j])) <= 5000 &&
                    Utils.distanceBetweenTwoCoordinates(endPositions.get(ships[i]), endPositions.get(ships[j])) <= 5000 ) {

                    index = res.size();
                    while (index > 0 &&
                            Math.abs(travelledDistances.get(ships[i]) - travelledDistances.get(ships[j])) <
                            Math.abs(travelledDistances.get(res.get(index-1)[0]) - travelledDistances.get(res.get(index-1)[1])))
                        index--;

                    if (ships[i].getMMSI().compareTo(ships[j].getMMSI()) < 0)
                        res.add(index, new Ship[] {ships[i], ships[j]});
                    else
                        res.add(index, new Ship[] {ships[j], ships[i]});
                }
            }
        }
        return res;
    }

    private void getPositions(TreeMap<Ship, ShipPosition> startMap, TreeMap<Ship, ShipPosition> endMap, Node<Ship> node) {
        if (node != null) {
            getPositions(startMap, endMap, node.getLeft());
            Ship ship = node.getElement();
            if (ship.getPosition().travelledDistance() >= 10) {
                startMap.put(ship, ship.getPosition().getFirstPosition());
                endMap.put(ship, ship.getPosition().getLastPosition());
            }
            getPositions(startMap, endMap, node.getRight());
        }
    }
}
