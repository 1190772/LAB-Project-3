package lapr.project.model;

import lapr.project.utils.AVL;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author Samuel Pereira 1201274
 */
public class ShipPositionBST extends AVL<ShipPosition> {

    public ShipPositionBST(){
        list = new ArrayList<>();
    }

    /**
     * Inserts a ShipPosition element and refreshes the ShipPosition's list
     *
     * @param element ShipPosition
     */
    @Override
    public void insert(ShipPosition element) {
        super.insert(element);
        list = (ArrayList<ShipPosition>) inOrder();
    }

    /**
     * Removes a ShipPosition element and refreshes the ShipPosition's list
     *
     * @param element ShipPosition
     */
    @Override
    public void remove(ShipPosition element) {
        super.remove(element);
        list = (ArrayList<ShipPosition>) inOrder();
    }

    /**
     * ShipPosition's list
     */
    private ArrayList<ShipPosition> list;

    /**
     * Calculates the distance between two ShipPosition
     *
     * @param pos1 first position
     * @param pos2 second position
     * @return distance in meters
     */
    public double distanceBetweenTwoCoordinates(ShipPosition pos1, ShipPosition pos2) {
        return distanceBetweenTwoCoordinates(pos1.getLongitude(), pos1.getLatitude(), pos2.getLongitude(), pos2.getLatitude());
    }

    /**
     * Calculates the distance between two coordinates
     *
     * @param lon1 first longitude
     * @param lat1 first latitude
     * @param lon2 second longitude
     * @param lat2 second latitude
     * @return distance in meters
     */
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

    /**
     * Calculates the Delta Distance
     *
     * @return Delta Distance in meters
     */
    public double deltaDistance() {
        
        return Math.round(distanceBetweenTwoCoordinates(list.get(0), list.get(list.size() - 1)));
    }

    /**
     * Calculates the Travelled Distance
     *
     * @return Travelled Distance in meters
     */
    public double travelledDistance() {
        
        if (list.isEmpty())
            return -1;
        if (list.size() == 1)
            return 0;
        return Math.round(travelledDistance(list, 0));
    }

    public double travelledDistance(LocalDateTime start, LocalDateTime end) {

        ArrayList<ShipPosition> shipPositions = getPositions(start,end);

        if (shipPositions.isEmpty())
            return -1;
        if (shipPositions.size() == 1)
            return 0;
        return Math.round(travelledDistance(shipPositions, 0));
    }

    /**
     * Calculates the Travelled Distance
     *
     * @param list List of the positions
     * @param position position indicator
     * @return Travelled Distance in meters
     */
    private double travelledDistance(ArrayList<ShipPosition> list, int position) {
        if (position == list.size() - 1)
            return 0;
        return distanceBetweenTwoCoordinates(list.get(position), list.get(position + 1)) + travelledDistance(list, position + 1);
    }

    /**
     * Returns the total number of movements
     *
     * @return total number of movements
     */
    public int totalNumberMovements() {
        return size() - 1;
    }

    /**
     * Returns the total movement time
     *
     * @return total movement time
     */
    public LocalTime totalMovementTime() {
        
        long min = Duration.between(list.get(0).getBaseDateTime(), list.get(list.size() - 1).getBaseDateTime()).toMinutes();
        return LocalTime.of((int) min / 60, (int) min % 60);
    }

    public double maxSOG() {
        
        double max = -Double.MAX_VALUE;
        for (ShipPosition sp : list)
            if (sp.getSOG() > max)
                max = sp.getSOG();
        return (double) Math.round(max*100)/100;
    }

    public double meanSOG() {
        
        double mean = 0;
        for (ShipPosition sp : list)
            mean += sp.getSOG();
        mean /= size();
        return (double) Math.round(mean*100)/100;
    }

    public double meanSOG(LocalDateTime start, LocalDateTime end) {

        ArrayList<ShipPosition> shipPositions = getPositions(start,end);
        double mean = 0;
        for (ShipPosition sp : shipPositions)
            mean += sp.getSOG();
        mean /= size();
        return (double) Math.round(mean*100)/100;
    }

    public double maxCOG() {
        
        double max = -Double.MAX_VALUE;
        for (ShipPosition sp : list)
            if (sp.getCOG() > max)
                max = sp.getCOG();
        return (double) Math.round(max*100)/100;
    }

    public double meanCOG() {
        
        double mean = 0;
        for (ShipPosition sp : list)
            mean += sp.getCOG();
        mean /= size();
        return (double) Math.round(mean*100)/100;
    }

    /**
     * Returns positions of ship given a date interval.
     *
     * @param startDate start of interval.
     * @param endDate end of interval.
     *
     * @return negative, 0, or positive, depending on whose IMO code comes first.
     */
    public ArrayList<ShipPosition> getPositions(LocalDateTime startDate, LocalDateTime endDate) {
        ArrayList<ShipPosition> res = new ArrayList<>();
        int i = 0;

        while (i < list.size() && list.get(i).getBaseDateTime().compareTo(startDate) < 0)
            i++;

        while (i < list.size() && list.get(i).getBaseDateTime().compareTo(endDate) <= 0) {
            res.add(list.get(i));
            i++;

        }

        return res;
    }



}
