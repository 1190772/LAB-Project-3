package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipBST;
import lapr.project.model.ShipPosition;
import lapr.project.utils.BST;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class ImportShipsController {

    BufferedReader in;
    String word;
    String fileName = "sships.csv";

    ShipBST bst;

    private String mmsi;
    private String name;
    private String imo;
    private String callSign;
    private int vesselType;
    private int length;
    private int width;
    private float draft;
    private String cargo;

    private String dateTime;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private double latitude;
    private double longitude;
    private double sog;
    private double cog;
    private double heading;
    private char transceiverClass;


    public ImportShipsController() throws UnsupportedOperationException {
        bst = App.getInstance().getCompany().getShips();
    }

    //TODO


    public void importShips() {
        String[] parameters;
        try {
            in = new BufferedReader((new FileReader(fileName)));
            parameters = in.readLine().split(",");
            mmsi = parameters[0];
            dateTime = parameters[1];
            year = Integer.parseInt(dateTime.substring(6, 10));
            month = Integer.parseInt(dateTime.substring(3, 5));
            day = Integer.parseInt(dateTime.substring(0, 2));
            hour = Integer.parseInt(dateTime.substring(11, 13));
            minute = Integer.parseInt(dateTime.substring(14, 16));
            latitude = Double.parseDouble(parameters[2]);
            longitude = Double.parseDouble(parameters[3]);
            sog = Double.parseDouble(parameters[4]);
            cog = Double.parseDouble(parameters[5]);
            heading = Double.parseDouble(parameters[6]);
            name = parameters[7];
            imo = parameters[8];
            callSign = parameters[9];
            vesselType = Integer.parseInt(parameters[10]);
            length = Integer.parseInt(parameters[11]);
            width = Integer.parseInt(parameters[12]);
            draft = Float.parseFloat(parameters[13]);
            cargo = parameters[14];
            transceiverClass = parameters[15].charAt(0);

            LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);

            Ship ship = new Ship(mmsi, name, imo, callSign, vesselType, length, width, draft, cargo);
            ShipPosition shipPosition = new ShipPosition(year, month, day, hour, minute, latitude, longitude, sog, cog, heading, transceiverClass);
            while ((word = in.readLine()) != null) {
                bst.insert(ship);
            }
            System.out.println(ship);
            System.out.println(shipPosition);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    public void importShipsByMMSI() {
//        ship_BST_mmsi = new BST<>();
//        try {
//            in = new BufferedReader(new FileReader(fileName));
//            String[] aux_array;
//            in.readLine();
//            while ((word = in.readLine()) != null) {
//                aux_array = word.split(",");
//                ship_BST_mmsi.insert(Integer.parseInt(aux_array[0]));
//            }
//            System.out.println(ship_BST_mmsi);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void importShipsByIMO() {
//        ship_BST_imo = new BST<>();
//        try {
//            in = new BufferedReader(new FileReader(fileName));
//            String[] aux_array;
//            in.readLine();
//            while ((word = in.readLine()) != null) {
//                aux_array = word.split(",");
//                ship_BST_imo.insert((aux_array[8]));
//            }
//            System.out.println(ship_BST_imo);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void importShipsByCallSign() {
//        ship_BST_callSign = new BST<>();
//        try {
//            in = new BufferedReader(new FileReader(fileName));
//            String[] aux_array;
//            in.readLine();
//            while ((word = in.readLine()) != null) {
//                aux_array = word.split(",");
//                ship_BST_callSign.insert((aux_array[9]));
//            }
//            System.out.println(ship_BST_callSign);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
