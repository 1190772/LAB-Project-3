package lapr.project.model;

public class Port {
    private final int id;
    private final String name;
    private final String country;
    private final double latitude;
    private final double longitude;
    private final int capacity;

    public Port(int id, String name, String country, double latitude, double longitude, int capacity) {
        this.id=id;
        this.name=name;
        this.country=country;
        this.latitude=latitude;
        this.longitude=longitude;
        this.capacity = capacity;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return "Port = " + name;
    }
}


