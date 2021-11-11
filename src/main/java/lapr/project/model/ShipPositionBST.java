package lapr.project.model;

import lapr.project.utils.AVL;

import java.util.ArrayList;
import java.util.List;

public class ShipPositionBST extends AVL<ShipPosition> {
    private double distanceBetweenTwoCoordinates(double lon1, double lat1, double lon2, double lat2){
        return Math.sqrt(Math.pow(lon1-lon2, 2)+Math.pow(lat1-lat2, 2));
    }

    private double distanceBetweenTwoCoordinates(ShipPosition pos1, ShipPosition pos2){
        double lon1 = pos1.getLongitude(),lat1=pos1.getLatitude();
        double lon2=pos2.getLongitude(), lat2= pos2.getLatitude();
        return Math.sqrt(Math.pow(lon1-lon2, 2)+Math.pow(lat1-lat2, 2));
    }

    public double deltaDistance(){
        ArrayList<ShipPosition> list = (ArrayList<ShipPosition>) inOrder();
        return distanceBetweenTwoCoordinates(list.get(0), list.get(list.size()-1));
    }

    public double travelledDistance(){
        ArrayList<ShipPosition> list = (ArrayList<ShipPosition>) inOrder();
        if (list.isEmpty())
            return -1;
        if (list.size()==1)
            return 0;
        return distanceTravelled(list, 0);
    }

    private double distanceTravelled(ArrayList<ShipPosition> list, int position) {
        if (position== list.size()-1)
            return 0;
        return distanceBetweenTwoCoordinates(list.get(position), list.get(position+1))+distanceTravelled(list, position+1);
    }

    public int totalNumberMovements() {
        return size()-1;
    }
}
