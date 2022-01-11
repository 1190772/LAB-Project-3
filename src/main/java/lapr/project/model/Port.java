package lapr.project.model;

public class Port implements FreightNetworkVertex {
    private final String  id;
    private final String name;
    private final Country country;
    private final double latitude;
    private final double longitude;
    private final int capacity;

    public Port(String id, String name, Country country, double latitude, double longitude, int capacity) {
        this.id=id;
        this.name=name;
        this.country=country;
        this.latitude=latitude;
        this.longitude=longitude;
        this.capacity = capacity;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
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
        return "Port{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", capacity=" + capacity +
                '}';
    }

    @Override
    public String getVertexName() {
        return "Port = " + name;
    }
}


