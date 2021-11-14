package lapr.project.model;


import java.time.LocalDateTime;
import java.time.LocalTime;

public class ShipMovementsAllDetails extends ShipMovements {

    private final String shipName;
    private final LocalDateTime startBaseDateTime;
    private final LocalDateTime endBaseDateTime;
    private final LocalTime totalMovementTime;
    private final double maxSOG;
    private final double meanSOG;
    private final double maxCOG;
    private final double meanCOG;
    private final double departureLatitude;
    private final double departureLongitude;
    private final double arrivalLatitude;
    private final double arrivalLongitude;


    public ShipMovementsAllDetails(String shipCode, String shipName, ShipPosition position1, ShipPosition position2, LocalTime totalMovementTime, int totalNumberMovements,
                                   double maxSOG, double meanSOG, double maxCOG, double meanCOG, double travelledDistance, double deltaDistance) {
        super(shipCode, totalNumberMovements, travelledDistance, deltaDistance);
        this.shipName = shipName;
        startBaseDateTime = position1.getBaseDateTime();
        endBaseDateTime = position2.getBaseDateTime();
        this.totalMovementTime = totalMovementTime;
        this.maxSOG = maxSOG;
        this.meanSOG = meanSOG;
        this.maxCOG = maxCOG;
        this.meanCOG = meanCOG;
        departureLatitude = position1.getLatitude();
        departureLongitude = position1.getLongitude();
        arrivalLatitude = position2.getLatitude();
        arrivalLongitude = position2.getLongitude();

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

    @Override
    public String toString() {

        return "ShipMovements{" +
                "\n\tShip Code='" + super.getShipCode() + '\'' +
                ", \n\tShip Name='" + shipName + '\'' +
                ", \n\tStart Base Date Time=" + startBaseDateTime +
                ", \n\tEnd Base Date Time=" + endBaseDateTime +
                ", \n\tTotal Movement Time=" + totalMovementTime +
                ", \n\tTotal Number Movements=" + super.getTotalNumberMovements() +
                ", \n\tMax SOG=" + maxSOG +
                ", \n\tMean SOG=" + meanSOG +
                ", \n\tMax COG=" + maxCOG +
                ", \n\tMean COG=" + meanCOG +
                ", \n\tDeparture Latitude=" + departureLatitude +
                ", \n\tDeparture Longitude=" + departureLongitude +
                ", \n\tArrival Latitude=" + arrivalLatitude +
                ", \n\tArrival Longitude=" + arrivalLongitude +
                ", \n\tTravelled Distance=" + super.getTravelledDistance() +
                ", \n\tDelta Distance=" + super.getDeltaDistance() +
                '}';
    }
}


