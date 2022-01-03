package lapr.project.model.fsiap;

public enum WallMaterial {
    //Outside Walls
    CORTEN_STEEL (25),
    STAINLESS_STEEL (16.2),

    //Middle Layers
    EXPANDED_POLYSTYRENE (0.046),
    POLYURETHANE_FOAM (0.03),

    //Inside Walls
    BAMBOO (0.55),
    PLYWOOD (0.13);


    private final double k;

    WallMaterial(double k) {
        this.k = k;
    }

    public double getK() {
        return k;
    }
}
