package lapr.project.model;

public class SeaDistance {

    private final int id_port1;
    private final int id_port2;
    private final int distance;

    public SeaDistance(int id_port1, int id_port2, int distance) {
        this.id_port1 = id_port1;
        this.id_port2 = id_port2;
        this.distance = distance;
    }

    public int getId_port1() { return id_port1; }

    public int getId_port2() { return id_port2; }

    public int getDistance() { return distance; }
}
