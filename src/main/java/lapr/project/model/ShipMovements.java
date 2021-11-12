package lapr.project.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ShipMovements {
    private final String shipCode;
    private final String shipName;
    private final LocalDateTime startBaseDateTime;
    private final LocalDateTime endBaseDateTime;
    private final LocalTime totalMovementTime;
    private final int totalNumberMovements;
    private final double maxSOG;
    private final double meanSOG;
    private final double maxCOG;
    private final double meanCOG;
    private final double departureLatitude;
    private final double departureLongitude;
    private final double arrivalLatitude;
    private final double arrivalLongitude;
    private final double travelledDistance;
    private final double deltaDistance;


    public ShipMovements(String shipCode, String shipName, ShipPosition position1, ShipPosition position2, LocalTime totalMovementTime, int totalNumberMovements,
                         double maxSOG, double meanSOG, double maxCOG, double meanCOG, double travelledDistance, double deltaDistance) {
        this.shipCode=shipCode;
        this.shipName=shipName;
        startBaseDateTime=position1.getBaseDateTime();
        endBaseDateTime=position2.getBaseDateTime();
        this.totalMovementTime=totalMovementTime;
        this.totalNumberMovements=totalNumberMovements;
        this.maxSOG=maxSOG;
        this.meanSOG=meanSOG;
        this.maxCOG=maxCOG;
        this.meanCOG=meanCOG;
        departureLatitude= position1.getLatitude();
        departureLongitude=position1.getLongitude();
        arrivalLatitude= position2.getLatitude();
        arrivalLongitude= position2.getLongitude();
        this.travelledDistance=travelledDistance;
        this.deltaDistance=deltaDistance;
    }


    public String getShipCode() {
        return shipCode;
    }

    public String getShipName() {
        return shipName;
    }

    public LocalDateTime getStartBaseDateTime() {
        return startBaseDateTime;
    }

    public LocalDateTime getEndBaseDateTime() {
        return endBaseDateTime;
    }

    public LocalTime getTotalMovementTime() {
        return totalMovementTime;
    }

    public int getTotalNumberMovements() {
        return totalNumberMovements;
    }

    public double getMaxSOG() {
        return maxSOG;
    }

    public double getMeanSOG() {
        return meanSOG;
    }

    public double getMaxCOG() {
        return maxCOG;
    }

    public double getMeanCOG() {
        return meanCOG;
    }

    public double getDepartureLatitude() {
        return departureLatitude;
    }

    public double getDepartureLongitude() {
        return departureLongitude;
    }

    public double getArrivalLatitude() {
        return arrivalLatitude;
    }

    public double getArrivalLongitude() {
        return arrivalLongitude;
    }

    public double getTravelledDistance() {
        return travelledDistance;
    }

    public double getDeltaDistance() {
        return deltaDistance;
    }


    @Override
    public String toString() {
        return "ShipMovements{" +
                "\n\tShip Code='" + shipCode + '\'' +
                ", \n\tShip Name='" + shipName + '\'' +
                ", \n\tStart Base Date Time=" + startBaseDateTime +
                ", \n\tEnd Base Date Time=" + endBaseDateTime +
                ", \n\tTotal Movement Time=" + totalMovementTime +
                ", \n\tTotal Number Movements=" + totalNumberMovements +
                ", \n\tMax SOG=" + maxSOG +
                ", \n\tMean SOG=" + meanSOG +
                ", \n\tMax COG=" + maxCOG +
                ", \n\tMean COG=" + meanCOG +
                ", \n\tDeparture Latitude=" + departureLatitude +
                ", \n\tDeparture Longitude=" + departureLongitude +
                ", \n\tArrival Latitude=" + arrivalLatitude +
                ", \n\tArrival Longitude=" + arrivalLongitude +
                ", \n\tTravelled Distance=" + travelledDistance +
                ", \n\tDelta Distance=" + deltaDistance +
                '}';
    }
}
