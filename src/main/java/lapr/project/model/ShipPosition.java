package lapr.project.model;

import java.time.LocalDateTime;

public class ShipPosition implements Comparable<ShipPosition> {
    private LocalDateTime baseDateTime;
    private double latitude;
    private double longitude;
    private double sog;
    private double cog;
    private int heading;
    private char transceiverClass;

    public ShipPosition(LocalDateTime baseDateTime, double latitude, double longitude, double sog, double cog, int heading, char transceiverClass) {
        this.baseDateTime = baseDateTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sog = sog;
        this.cog = cog;
        this.heading = heading;
        this.transceiverClass = transceiverClass;
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

    @Override
    public int compareTo(ShipPosition o) {
        return baseDateTime.compareTo(o.getBaseDateTime());
    }

    @Override
    public String toString()
        {
        return "ShipPosition{" +
                "baseDateTime=" + baseDateTime +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", sog=" + sog +
                ", cog=" + cog +
                ", heading=" + heading +
                ", transceiverClass=" + transceiverClass +
                '}';
        }
}
