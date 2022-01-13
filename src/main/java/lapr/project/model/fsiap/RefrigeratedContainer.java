package lapr.project.model.fsiap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lapr.project.model.shared.Utils.*;

import oracle.ucp.util.Pair;

public class RefrigeratedContainer {
    private double length = 244;
    private double width = 244;
    private double height = 610;
    private final double thermalVariation;
    private final String[] walls = {"Top Wall: ", "Left Wall: ", "Right Wall: ", "Bottom Wall: ", "Front Door: ", "Back Door: "};

    private List<Pair<WallMaterial, Double>> topWall;
    private List<Pair<WallMaterial, Double>> leftWall;
    private List<Pair<WallMaterial, Double>> rightWall;
    private List<Pair<WallMaterial, Double>> bottomWall;
    private List<Pair<WallMaterial, Double>> frontDoor;
    private List<Pair<WallMaterial, Double>> backDoor;

    public RefrigeratedContainer(List<Pair<WallMaterial, Double>> topWall, List<Pair<WallMaterial, Double>> leftWall, List<Pair<WallMaterial, Double>> rightWall, List<Pair<WallMaterial, Double>> bottomWall, List<Pair<WallMaterial, Double>> frontDoor, List<Pair<WallMaterial, Double>> backDoor, double thermalVariation) {
        setTopWall(topWall);
        setLeftWall(leftWall);
        setRightWall(rightWall);
        setBottomWall(bottomWall);
        setFrontDoor(frontDoor);
        setBackDoor(backDoor);
        this.thermalVariation = thermalVariation;
    }

    public RefrigeratedContainer(List<Pair<WallMaterial, Double>> wall, double thermalVariation) {
        setTopWall(wall);
        setLeftWall(wall);
        setRightWall(wall);
        setBottomWall(wall);
        setFrontDoor(wall);
        setBackDoor(wall);
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
        this.topWall = new ArrayList<>(topWall);
    }

    public void setLeftWall(List<Pair<WallMaterial, Double>> leftWall) {
        if (leftWall == null)
            throw new IllegalArgumentException("List of Materials for the left wall cannot be null!");
        if (leftWall.isEmpty())
            throw new IllegalArgumentException("List of Materials for the left wall cannot be empty!");
        this.leftWall = new ArrayList<>(leftWall);
    }

    public void setRightWall(List<Pair<WallMaterial, Double>> rightWall) {
        if (rightWall == null)
            throw new IllegalArgumentException("List of Materials for the right wall cannot be null!");
        if (rightWall.isEmpty())
            throw new IllegalArgumentException("List of Materials for the right wall cannot be empty!");
        this.rightWall = new ArrayList<>(rightWall);
    }

    public void setBottomWall(List<Pair<WallMaterial, Double>> bottomWall) {
        if (bottomWall == null)
            throw new IllegalArgumentException("List of Materials for the bottom wall cannot be null!");
        if (bottomWall.isEmpty())
            throw new IllegalArgumentException("List of Materials for the bottom wall cannot be empty!");
        this.bottomWall = new ArrayList<>(bottomWall);
    }

    public void setFrontDoor(List<Pair<WallMaterial, Double>> frontDoor) {
        if (frontDoor == null)
            throw new IllegalArgumentException("List of Materials for the front door cannot be null!");
        if (frontDoor.isEmpty())
            throw new IllegalArgumentException("List of Materials for the front door cannot be empty!");
        this.frontDoor = new ArrayList<>(frontDoor);
    }

    public void setBackDoor(List<Pair<WallMaterial, Double>> backDoor) {
        if (backDoor == null)
            throw new IllegalArgumentException("List of Materials for the back door cannot be null!");
        if (backDoor.isEmpty())
            throw new IllegalArgumentException("List of Materials for the back door cannot be empty!");
        this.backDoor = new ArrayList<>(backDoor);
    }

    public List<Double> getResistance() {
        return new ArrayList<>(Arrays.asList(calculateResistance(topWall, getWallArea("Top")), calculateResistance(leftWall, getWallArea("Side")), calculateResistance(rightWall, getWallArea("Side")), calculateResistance(bottomWall, getWallArea("Bottom")), calculateResistance(frontDoor, getWallArea("Door")), calculateResistance(backDoor, getWallArea("Door"))));
    }

    private double calculateResistance(List<Pair<WallMaterial, Double>> wallMaterial, double area) {
        double resistance = 0;
        for (Pair<WallMaterial, Double> pair : wallMaterial)
            resistance += thermalResistance(pair.get2nd(), pair.get1st().getK(), area);
        return resistance;
    }

    public double getWallArea(String side) {
        switch (side) {
            case "Door":
                return width * height;
            case "Side":
                return length * height;
            default:
                return length * width;
        }
    }

    public List<Double> getThermalFlux() {
        List<Double> thermalFlux = new ArrayList<>();
        for (double resistance : getResistance())
            thermalFlux.add(thermalFlux(thermalVariation, resistance));
        return thermalFlux;
    }

    private String getResistanceText() {
        String resistanceUnit = "K/W\n";
        StringBuilder result = new StringBuilder();
        List<Double> resistance = getResistance();

        for (int i = 0; i<walls.length;i++)
            result.append(walls[i]).append(resistance.get(i)).append(resistanceUnit);

        return result.toString();
    }

    private String getThermalFluxText() {
        String thermalFluxUnit = "W/m^2\n";
        StringBuilder result = new StringBuilder();
        List<Double> thermalFlux = getThermalFlux();

        for (int i = 0; i<walls.length;i++)
            result.append(walls[i]).append(thermalFlux.get(i)).append(thermalFluxUnit);

        return result.toString();
    }

    private double getVolume() {
        double wallThickness = 0;
        for (Pair<WallMaterial, Double> pair : topWall)
            wallThickness += pair.get2nd();
        for (Pair<WallMaterial, Double> pair : bottomWall)
            wallThickness += pair.get2nd();
        double newHeight = height - wallThickness;

        wallThickness = 0;
        for (Pair<WallMaterial, Double> pair : leftWall)
            wallThickness += pair.get2nd();
        for (Pair<WallMaterial, Double> pair : rightWall)
            wallThickness += pair.get2nd();
        double newWidth = width - wallThickness;

        return length * newWidth * newHeight;
    }

    @Override
    public String toString() {
        return "RefrigeratedContainer{" +
                "length=" + length +
                "cm, width=" + width +
                "cm, height=" + height +
                "cm,\ntopWall=" + topWall +
                ",\nleftWall=" + leftWall +
                ",\nrightWall=" + rightWall +
                ",\nbottomWall=" + bottomWall +
                ",\nResistance= " + getResistanceText() +
                "Thermal Flux= " + getThermalFluxText() +
                "Total cargo volume= " + getVolume() +
                "cm^3\n}";
    }
}
