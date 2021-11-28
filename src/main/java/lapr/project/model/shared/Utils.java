package lapr.project.model.shared;

import lapr.project.model.ShipPosition;

public class Utils {

    /**
     * Calculates the distance between two ShipPosition
     *
     * @param pos1 first position
     * @param pos2 second position
     * @return distance in meters
     */
    public static double distanceBetweenTwoCoordinates(ShipPosition pos1, ShipPosition pos2) {
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
    public static double distanceBetweenTwoCoordinates(double lon1, double lat1, double lon2, double lat2) {
        double r = 6371 * Math.pow(10, 3);
        lon1 *= Math.PI / 180;
        lat1 *= Math.PI / 180;
        lon2 *= Math.PI / 180;
        lat2 *= Math.PI / 180;
        double a = Math.pow(Math.sin((lat2 - lat1) / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin((lon2 - lon1) / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return r * c;
    }

}
