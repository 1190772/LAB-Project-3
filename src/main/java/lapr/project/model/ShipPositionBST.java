package lapr.project.model;

import lapr.project.utils.AVL;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;


public class ShipPositionBST extends AVL<ShipPosition> {

    private ArrayList<ShipPosition> list;

    public double distanceBetweenTwoCoordinates(ShipPosition pos1, ShipPosition pos2) {
        return distanceBetweenTwoCoordinates(pos1.getLongitude(), pos1.getLatitude(), pos2.getLongitude(), pos2.getLatitude());
    }

    private double distanceBetweenTwoCoordinates(double lon1, double lat1, double lon2, double lat2) {
        double R = 6371 * Math.pow(10, 3);
        lon1 *= Math.PI / 180;
        lat1 *= Math.PI / 180;
        lon2 *= Math.PI / 180;
        lat2 *= Math.PI / 180;
        double a = Math.pow(Math.sin((lat2 - lat1) / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((lon2 - lon1) / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public double deltaDistance() {
        list = (ArrayList<ShipPosition>) inOrder();
        return Math.round(distanceBetweenTwoCoordinates(list.get(0), list.get(list.size() - 1)));
    }

    public double travelledDistance() {
        list = (ArrayList<ShipPosition>) inOrder();
        if (list.isEmpty())
            return -1;
        if (list.size() == 1)
            return 0;
        return Math.round(travelledDistance(list, 0));
    }

    private double travelledDistance(ArrayList<ShipPosition> list, int position) {
        if (position == list.size() - 1)
            return 0;
        return distanceBetweenTwoCoordinates(list.get(position), list.get(position + 1)) + travelledDistance(list, position + 1);
    }

    public int totalNumberMovements() {
        return size() - 1;
    }

    public LocalTime totalMovementTime() {
        list = (ArrayList<ShipPosition>) inOrder();
        long min = Duration.between(list.get(0).getBaseDateTime(), list.get(list.size() - 1).getBaseDateTime()).toMinutes();
        return LocalTime.of((int) min / 60, (int) min % 60);
    }

    public double maxSOG() {
        list = (ArrayList<ShipPosition>) inOrder();
        double max = Double.MIN_VALUE;
        for (ShipPosition sp : list)
            if (sp.getSOG() > max)
                max = sp.getSOG();
        return (double) Math.round(max*100)/100;
    }

    public double meanSOG() {
        list = (ArrayList<ShipPosition>) inOrder();
        double mean = 0;
        for (ShipPosition sp : list)
            mean += sp.getSOG();
        mean /= size();
        return (double) Math.round(mean*100)/100;
    }

    public double maxCOG() {
        list = (ArrayList<ShipPosition>) inOrder();
        double max = Double.MIN_VALUE;
        for (ShipPosition sp : list)
            if (sp.getCOG() > max)
                max = sp.getCOG();
        return (double) Math.round(max*100)/100;
    }

    public double meanCOG() {
        list = (ArrayList<ShipPosition>) inOrder();
        double mean = 0;
        for (ShipPosition sp : list)
            mean += sp.getCOG();
        mean /= size();
        return (double) Math.round(mean*100)/100;
    }

//    public void topNShips(){
//        double distance;
//        double auxDistance=0;
//        int position = 0;
//        list = (ArrayList<ShipPosition>) inOrder();
//        for(ShipPosition sp: list){
//            distance = travelledDistance(list,position);
//            position++;
//            if(distance>auxDistance){
//
//            }
//        }
//    }

}
