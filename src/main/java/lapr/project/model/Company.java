package lapr.project.model;

public class Company {
    private final String designation;

    private final ShipBST shipBST;

    public Company(String designation) {
        this.designation = designation;
        shipBST = new ShipBST();
    }

    public String getDesignation() {
return designation;
}

    public ShipBST getShips() { return shipBST; }
}
