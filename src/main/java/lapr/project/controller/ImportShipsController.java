package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipBST;
import lapr.project.model.ShipPosition;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ImportShipsController {

    String fileName = "sships.csv";

    ShipBST bst;

    public ImportShipsController() throws UnsupportedOperationException {
        bst = App.getInstance().getCompany().getShips();
    }

    public ImportShipsController(ShipBST bst) {
        this.bst=bst;
    }

    public void importShips() {
        String[] parameters;
        Ship ship;
        try {
            Scanner in = new Scanner((new FileReader(fileName)));
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
                ship.addPosition(shipPosition);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
