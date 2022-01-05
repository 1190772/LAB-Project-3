package lapr.project.controller;

import lapr.project.model.fsiap.RefrigeratedContainer;
import lapr.project.model.fsiap.WallMaterial;
import oracle.ucp.util.Pair;

import java.util.List;

public class RefrigeratedContainerController {
    public RefrigeratedContainer getDetails(List<List<Pair<WallMaterial, Double>>> walls, double thermalVariation) {
        if (walls.isEmpty())
            throw new IllegalArgumentException("The list has to contain at least a composition of one wall!");
        if (walls.size()==1)
        return new RefrigeratedContainer(walls.get(0), walls.get(0), walls.get(0), walls.get(0), thermalVariation);
        if (walls.size()!=4)
            throw new IllegalArgumentException("The list can only have 1 or 4 compositions for walls");
        return new RefrigeratedContainer(walls.get(0), walls.get(1), walls.get(2), walls.get(3), thermalVariation);
    }

    public RefrigeratedContainer getDetails(List<List<Pair<WallMaterial, Double>>> walls, double thermalVariation, double length, double width, double height) {
        RefrigeratedContainer result = getDetails(walls, thermalVariation);
        result.setLength(length);
        result.setWidth(width);
        result.setHeight(height);
        return result;
    }
}
