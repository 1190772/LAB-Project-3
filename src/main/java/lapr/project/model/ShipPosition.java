package lapr.project.model;

import java.time.LocalDateTime;

public class ShipPosition implements Comparable<ShipPosition> {
    LocalDateTime baseDateTime;
    double latitude;
    double longitude;
    int sog;
    int cog;
    int heading;
    //int positionCode;
    char transceiverClass;

    public ShipPosition(LocalDateTime baseDateTime, double latitude, double longitude, int sog, int cog, int heading, char transceiverClass) {
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
}
