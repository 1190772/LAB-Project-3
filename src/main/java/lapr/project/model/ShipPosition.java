package lapr.project.model;

import java.time.LocalDateTime;

public class ShipPosition implements Comparable<ShipPosition> {
    private final LocalDateTime baseDateTime;
    private final double latitude;
    private final double longitude;
    private final double sog;
    private final double cog;
    private final int heading;
    private final char transceiverClass;

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
                "Base date and time=" + baseDateTime +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", SOG=" + sog +
                ", COG=" + cog +
                ", Heading=" + heading +
                ", Transceiver Class=" + transceiverClass +
                '}';
        }
}
