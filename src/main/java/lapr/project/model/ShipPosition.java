package lapr.project.model;

public class ShipPosition {
    String baseDateTime;
    int latitude;
    int longitude;
    int sog;
    int cog;
    int heading;
    //int positionCode;
    char transceiverClass;

    public ShipPosition(String baseDateTime, int latitude, int longitude, int sog, int cog, int heading, char transceiverClass) {
        this.baseDateTime = baseDateTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sog = sog;
        this.cog = cog;
        this.heading = heading;
        this.transceiverClass = transceiverClass;
    }
}
