package lapr.project.model;

public class SeaDistance {

    private final String idPort1;
    private final String idPort2;
    private final int distance;

    public SeaDistance(String idPort1, String idPort2, int distance) {
        this.idPort1 = idPort1;
        this.idPort2 = idPort2;
        this.distance = distance;
    }

    public String getIdPort1() { return idPort1; }

    public String getIdPort2() { return idPort2; }

    public int getDistance() { return distance; }
}
