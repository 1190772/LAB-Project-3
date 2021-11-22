package lapr.project.model;

public class ShipMovements implements Comparable<ShipMovements> {
    private final String shipCode;
    private final int totalNumberMovements;
    private final double travelledDistance;
    private final double deltaDistance;


    public ShipMovements(String shipCode, int totalNumberMovements, double travelledDistance, double deltaDistance) {
        this.shipCode = shipCode;
        this.totalNumberMovements = totalNumberMovements;
        this.travelledDistance = travelledDistance;
        this.deltaDistance = deltaDistance;
    }

    public String getShipCode() {
        return shipCode;
    }

    public int getTotalNumberMovements() {
        return totalNumberMovements;
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
                ", \n\tTotal Number Movements=" + totalNumberMovements +
                ", \n\tTravelled Distance=" + travelledDistance +
                ", \n\tDelta Distance=" + deltaDistance +
                '}';
    }


    @Override
    public int compareTo(ShipMovements o) {
        if (this == o)
            return 0;
        ShipMovements other = (ShipMovements) o;

        if (travelledDistance == other.travelledDistance)
            if (deltaDistance == other.deltaDistance)
                return 0;
            else
                return (deltaDistance > other.deltaDistance) ? 1 : -1;
        else
            return (travelledDistance > other.travelledDistance) ? 1 : -1;
    }
}

