package lapr.project.model;

public class SeaDistance {

    private final int idPort1;
    private final int idPort2;
    private final int distance;

    public SeaDistance(int idPort1, int idPort2, int distance) {
        this.idPort1 = idPort1;
        this.idPort2 = idPort2;
        this.distance = distance;
    }

    public int getIdPort1() { return idPort1; }

    public int getIdPort2() { return idPort2; }

    public int getDistance() { return distance; }
}
