package lapr.project.model.fsiap;

import java.util.List;

import static lapr.project.model.shared.Utils.*;

import oracle.ucp.util.Pair;

public class RefrigeratedContainer {
    private double length = 244;
    private double width = 244;
    private double height = 610;
    private final double thermalVariation;

    private List<Pair<WallMaterial, Double>> topWall;
    private List<Pair<WallMaterial, Double>> leftWall;
    private List<Pair<WallMaterial, Double>> rightWall;
    private List<Pair<WallMaterial, Double>> bottomWall;

    public RefrigeratedContainer(List<Pair<WallMaterial, Double>> topWall, List<Pair<WallMaterial, Double>> leftWall, List<Pair<WallMaterial, Double>> rightWall, List<Pair<WallMaterial, Double>> bottomWall, double thermalVariation) {
        setTopWall(topWall);
        setLeftWall(leftWall);
        setRightWall(rightWall);
        setBottomWall(bottomWall);
        this.thermalVariation = thermalVariation;
    }

    public RefrigeratedContainer(List<Pair<WallMaterial, Double>> wall, double thermalVariation) {
        setTopWall(wall);
        setLeftWall(wall);
        setRightWall(wall);
        setBottomWall(wall);
        this.thermalVariation = thermalVariation;
    }

    public void setLength(double length) {
        if (length <= 0)
            throw new IllegalArgumentException("Length cannot be less or equal to 0!");
        this.length = length;
    }

    public void setWidth(double width) {
        if (width <= 0)
            throw new IllegalArgumentException("Width cannot be less or equal to 0!");
        this.width = width;
    }

    public void setHeight(double height) {
        if (height <= 0)
            throw new IllegalArgumentException("Height cannot be less or equal to 0!");
        this.height = height;
    }

    public void setTopWall(List<Pair<WallMaterial, Double>> topWall) {
        if (topWall == null)
            throw new IllegalArgumentException("List of Materials for the top wall cannot be null!");
        if (topWall.isEmpty())
            throw new IllegalArgumentException("List of Materials for the top wall cannot be empty!");
        this.topWall = topWall;
    }

    public void setLeftWall(List<Pair<WallMaterial, Double>> leftWall) {
        if (leftWall == null)
            throw new IllegalArgumentException("List of Materials for the left wall cannot be null!");
        if (leftWall.isEmpty())
            throw new IllegalArgumentException("List of Materials for the left wall cannot be empty!");
        this.leftWall = leftWall;
    }

    public void setRightWall(List<Pair<WallMaterial, Double>> rightWall) {
        if (rightWall == null)
            throw new IllegalArgumentException("List of Materials for the right wall cannot be null!");
        if (rightWall.isEmpty())
            throw new IllegalArgumentException("List of Materials for the right wall cannot be empty!");
        this.rightWall = rightWall;
    }

    public void setBottomWall(List<Pair<WallMaterial, Double>> bottomWall) {
        if (bottomWall == null)
            throw new IllegalArgumentException("List of Materials for the bottom wall cannot be null!");
        if (bottomWall.isEmpty())
            throw new IllegalArgumentException("List of Materials for the bottom wall cannot be empty!");
        this.bottomWall = bottomWall;
    }

    private String getResistance() {
        StringBuilder result = new StringBuilder();
        double resistance = 0;
        double area = width * length;

        result.append("Top Wall: ");
        for (Pair<WallMaterial, Double> pair : topWall)
            resistance += thermalResistance(pair.get2nd(), pair.get1st().getK(), area);
        result.append(resistance).append("K/W\n");

        resistance = 0;
        area = height * length;
        result.append("Left Wall: ");
        for (Pair<WallMaterial, Double> pair : leftWall)
            resistance += thermalResistance(pair.get2nd(), pair.get1st().getK(), area);
        result.append(resistance).append("K/W\n");

        resistance = 0;
        result.append("Right Wall: ");
        for (Pair<WallMaterial, Double> pair : rightWall)
            resistance += thermalResistance(pair.get2nd(), pair.get1st().getK(), area);
        result.append(resistance).append("K/W\n");

        resistance = 0;
        area = width * length;
        result.append("Bottom Wall: ");
        for (Pair<WallMaterial, Double> pair : bottomWall)
            resistance += thermalResistance(pair.get2nd(), pair.get1st().getK(), area);
        result.append(resistance).append("K/W\n");

        return result.toString();
    }

    private String getThermalFlux() {
        StringBuilder result = new StringBuilder();
        double resistance = 0;
        double area = width * length;

        result.append("Top Wall: ");
        for (Pair<WallMaterial, Double> pair : topWall)
            resistance += thermalResistance(pair.get2nd(), pair.get1st().getK(), area);
        result.append(thermalFlux(thermalVariation, resistance)).append("W/m^2\n");

        resistance = 0;
        area = height * length;
        result.append("Left Wall: ");
        for (Pair<WallMaterial, Double> pair : leftWall)
            resistance += thermalResistance(pair.get2nd(), pair.get1st().getK(), area);
        result.append(thermalFlux(thermalVariation, resistance)).append("W/m^2\n");

        resistance = 0;
        result.append("Right Wall: ");
        for (Pair<WallMaterial, Double> pair : rightWall)
            resistance += thermalResistance(pair.get2nd(), pair.get1st().getK(), area);
        result.append(thermalFlux(thermalVariation, resistance)).append("W/m^2\n");

        resistance = 0;
        area = width * length;
        result.append("Bottom Wall: ");
        for (Pair<WallMaterial, Double> pair : bottomWall)
            resistance += thermalResistance(pair.get2nd(), pair.get1st().getK(), area);
        result.append(thermalFlux(thermalVariation, resistance)).append("W/m^2\n");

        return result.toString();
    }

    private double getVolume() {
        double wallThickness = 0;
        for (Pair<WallMaterial, Double> pair : topWall)
            wallThickness += pair.get2nd();
        for (Pair<WallMaterial, Double> pair : bottomWall)
            wallThickness += pair.get2nd();
        double height = this.height - wallThickness;

        wallThickness = 0;
        for (Pair<WallMaterial, Double> pair : leftWall)
            wallThickness += pair.get2nd();
        for (Pair<WallMaterial, Double> pair : rightWall)
            wallThickness += pair.get2nd();
        double width = this.width - wallThickness;

        return length*width*height;
    }

    @Override
    public String toString() {
        return "RefrigeratedContainer{" +
                "length=" + length +
                "m, width=" + width +
                "m, height=" + height +
                "m,\ntopWall=" + topWall +
                ",\nleftWall=" + leftWall +
                ",\nrightWall=" + rightWall +
                ",\nbottomWall=" + bottomWall +
                ",\nResistance= " + getResistance() +
                "Thermal Flux= " + getThermalFlux() +
                "Total cargo volume= " + getVolume() +
                "m^3\n}";
    }
}
