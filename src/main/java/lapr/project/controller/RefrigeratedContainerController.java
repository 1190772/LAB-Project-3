package lapr.project.controller;

import lapr.project.model.fsiap.RefrigeratedContainer;
import lapr.project.model.fsiap.WallMaterial;
import oracle.ucp.util.Pair;

import java.util.List;

public class RefrigeratedContainerController {
    public String getDetails(List<Pair<WallMaterial, Double>> topWall, List<Pair<WallMaterial, Double>> leftWall, List<Pair<WallMaterial, Double>> rightWall, List<Pair<WallMaterial, Double>> bottomWall, double thermalVariation) {
        return new RefrigeratedContainer(topWall, leftWall, rightWall, bottomWall, thermalVariation).toString();
    }

    public String getDetails(List<Pair<WallMaterial, Double>> topWall, List<Pair<WallMaterial, Double>> leftWall, List<Pair<WallMaterial, Double>> rightWall, List<Pair<WallMaterial, Double>> bottomWall, double thermalVariation, double length, double width, double height) {
        RefrigeratedContainer result = new RefrigeratedContainer(topWall, leftWall, rightWall, bottomWall, thermalVariation);
        result.setLength(length);
        result.setWidth(width);
        result.setHeight(height);
        return result.toString();
    }

    public String getDetails(List<Pair<WallMaterial, Double>> wall, double thermalVariation) {
        return new RefrigeratedContainer(wall, wall, wall, wall, thermalVariation).toString();
    }

    public String getDetails(List<Pair<WallMaterial, Double>> wall, double thermalVariation, double length, double width, double height) {
        RefrigeratedContainer result = new RefrigeratedContainer(wall, wall, wall, wall, thermalVariation);
        result.setLength(length);
        result.setWidth(width);
        result.setHeight(height);
        return result.toString();
    }
}
