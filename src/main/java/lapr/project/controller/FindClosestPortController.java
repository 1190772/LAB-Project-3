package lapr.project.controller;

import lapr.project.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FindClosestPortController {

    public Port findClosestPortController(String callsign, LocalDateTime baseDatetime) {
        double latitude = -92;
        double longitude = 0;
        Ship ship = new SearchShipController().findShip(callsign);
        if (ship==null)
            return null;
        ArrayList<ShipPosition> list = (ArrayList<ShipPosition>) ship.getPosition().inOrder();
        for (int i = 0; i < list.size() && latitude == -92; i++)
            if (list.get(i).getBaseDateTime().toString().equals(baseDatetime.toString())) {
                latitude = list.get(i).getLatitude();
                longitude = list.get(i).getLongitude();
            }
        if (latitude != -92)
            return App.getInstance().getCompany().getPorts().findNearestNeighbour(latitude, longitude);
        return null;
    }

}
