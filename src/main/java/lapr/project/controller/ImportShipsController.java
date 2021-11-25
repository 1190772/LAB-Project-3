package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipBST;
import lapr.project.model.ShipPosition;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Controller responsible for importing ships.
 *
 * @author José Silva 1190772
 */
public class ImportShipsController {

    /**
     * The ship binary search tree.
     */
    private final ShipBST bst;

    /**
     * Builds an instance of the Controller.
     */
    public ImportShipsController() {
        bst = App.getInstance().getCompany().getShips();
    }

    /**
     * Imports the ships from a text file to a binary search tree, after validating some parameteres.
     *
     * @param fileName
     */
    public void importShips(String fileName) {
        String[] parameters;
        Ship ship;
        try (Scanner in = new Scanner((new FileReader(fileName)))) {
            in.nextLine();
            while (in.hasNextLine()) {
                parameters = in.nextLine().split(",");
                String imo = parameters[8];
                ship = bst.findShip(imo);
                if (ship == null) {
                    String mmsi = parameters[0];
                    String name = parameters[7];
                    String callSign = parameters[9];
                    int vesselType = Integer.parseInt(parameters[10]);
                    int length = Integer.parseInt(parameters[11]);
                    int width = Integer.parseInt(parameters[12]);
                    float draft = Float.parseFloat(parameters[13]);
                    ship = new Ship(mmsi, name, imo, callSign, vesselType, length, width, draft);
                    bst.insert(ship);
                }

                String dateTime = parameters[1];
                int year = Integer.parseInt(dateTime.substring(6, 10));
                int month = Integer.parseInt(dateTime.substring(3, 5));
                int day = Integer.parseInt(dateTime.substring(0, 2));
                int hour = Integer.parseInt(dateTime.substring(11, 13));
                int minute = Integer.parseInt(dateTime.substring(14, 16));
                double latitude = Double.parseDouble(parameters[2]);
                double longitude = Double.parseDouble(parameters[3]);
                double sog = Double.parseDouble(parameters[4]);
                double cog = Double.parseDouble(parameters[5]);
                double heading = Double.parseDouble(parameters[6]);
                int cargo;
                if (!parameters[14].equals("NA"))
                    cargo = Integer.parseInt(parameters[14]);
                else
                    cargo = 0;
                char transceiverClass = parameters[15].charAt(0);
                LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);
                ShipPosition shipPosition = new ShipPosition(localDateTime, latitude, longitude, sog, cog, heading, transceiverClass, cargo);
                if (validateHeading(shipPosition.getHeading()) && validateSOG(shipPosition.getSOG()) && validateCOG(shipPosition.getCOG()) && validateLatitude(shipPosition.getLatitude()) && validateLongitude(shipPosition.getLongitude())) {
                    ship.addPosition(shipPosition);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks the validity of heading.
     *
     * @param heading
     * @return
     */
    private boolean validateHeading(double heading) {
        boolean flag = false;
        if (heading >= 0 && heading < 360 || heading == 511) {
            flag = true;
        }
        return flag;
    }

    /**
     * Checks the validity of sog.
     *
     * @param sog
     * @return
     */
    private boolean validateSOG(double sog) {
        boolean flag = false;
        if (sog >= 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * Checks the validity of cog.
     *
     * @param cog
     * @return
     */
    private boolean validateCOG(double cog) {
        boolean flag = false;
        if (cog >= 0 && cog < 360) {
            flag = true;
        }
        return flag;
    }

    /**
     * Checks the validity of latitude.
     *
     * @param latitude
     * @return
     */
    private boolean validateLatitude(double latitude) {
        boolean flag = false;
        if (latitude > -90 && latitude < 90 || latitude == 91) {
            flag = true;
        }
        return flag;
    }

    /**
     * Checks the validity of longitude.
     *
     * @param longitude
     * @return
     */
    private boolean validateLongitude(double longitude) {
        boolean flag = false;
        if (longitude > -180 && longitude < 180 || longitude == 181) {
            flag = true;
        }
        return flag;
    }
}




