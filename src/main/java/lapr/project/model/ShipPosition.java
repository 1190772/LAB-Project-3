package lapr.project.model;

import java.time.LocalDateTime;

public class ShipPosition implements Comparable<ShipPosition> {

    private final LocalDateTime baseDateTime;
    private final double latitude;
    private final double longitude;
    private final double sog;
    private final double cog;
    private final double heading;
    private final char transceiverClass;
    private final int cargo;

    public ShipPosition(LocalDateTime baseDateTime, double latitude, double longitude, double sog, double cog, double heading, char transceiverClass, int cargo) {

        this.baseDateTime = baseDateTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sog = sog;
        this.cog = cog;
        this.heading = heading;
        this.transceiverClass = transceiverClass;
        this.cargo = cargo;
    }

    public LocalDateTime getBaseDateTime() {
        return baseDateTime;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getSOG() {
        return sog;
    }

    public double getHeading() {
        return heading;
    }

    public double getCOG() {
        return cog;
    }

    public int getCargo() {
        return cargo;
    }

    public char getTransceiverClass() {
        return transceiverClass;
    }

    @Override
    public String toString()
        {
        return "Ship Position{" +
                "Base date and time = " + baseDateTime +
                ", Latitude = " + latitude +
                ", Longitude = " + longitude +
                ", SOG = " + sog +
                ", COG = " + cog +
                ", Heading = " + heading +
                ", Transceiver Class = " + transceiverClass +
                ", Cargo = " + heading +
                '}';
        }

    @Override
    public int compareTo(ShipPosition o) {
        return baseDateTime.compareTo(o.getBaseDateTime());
    }

}

