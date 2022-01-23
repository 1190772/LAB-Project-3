package lapr.project.model.shared;

import static lapr.project.controller.PositionContainersController.Coordinates;
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

    public static double thermalResistance(double thickness, double k, double area) {
        return thickness / (k * area);
    }

    public static double thermalFlux(double thermalVariation, double resistance) {
        return (double) Math.round((thermalVariation / resistance) * 100) / 100;
    }

    public static double energy(double thermalFlux, double time) {
        return thermalFlux * time;
    }

    public static Coordinates calculateCenterMass(Coordinates centerMass, Coordinates newCoordinates, double oldMass, double addedMass) {
        Coordinates result = new Coordinates(0,0,0);
        result.setX((centerMass.getX()*oldMass+ newCoordinates.getX()*addedMass)/(oldMass+addedMass));
        result.setY((centerMass.getY()*oldMass+ newCoordinates.getY()*addedMass)/(oldMass+addedMass));
        result.setZ((centerMass.getZ()*oldMass+ newCoordinates.getZ()*addedMass)/(oldMass+addedMass));
        return result;
    }
}
