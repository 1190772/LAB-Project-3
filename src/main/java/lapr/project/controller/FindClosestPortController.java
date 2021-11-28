package lapr.project.controller;

import lapr.project.model.*;
import lapr.project.utils.TwoDTree;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FindClosestPortController {

    public Port findClosestPortController(String callsign, LocalDateTime baseDatetime) {
        double latitude = -92, longitude = 0;
        ArrayList<ShipPosition> list = (ArrayList<ShipPosition>) new SearchShipController().findShip(callsign).getPosition().inOrder();
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
