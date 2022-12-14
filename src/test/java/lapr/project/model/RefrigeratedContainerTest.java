package lapr.project.model;

import lapr.project.controller.RefrigeratedContainerController;
import lapr.project.model.fsiap.RefrigeratedContainer;
import lapr.project.model.fsiap.WallMaterial;
import oracle.ucp.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RefrigeratedContainerTest {
    RefrigeratedContainer refrigeratedContainer1;
    RefrigeratedContainer refrigeratedContainer2;
    List<Pair<WallMaterial, Double>> wallType1;
    List<Pair<WallMaterial, Double>> wallType2;
    double thermalVariation1 = 20 - (-5);
    double thermalVariation2 = 20 - 7;

    public RefrigeratedContainerTest() {
        wallType1 = new ArrayList<>(Arrays.asList(new Pair<>(WallMaterial.STAINLESS_STEEL, 0.025), new Pair<>(WallMaterial.POLYURETHANE_FOAM, 0.05), new Pair<>(WallMaterial.PLYWOOD, 0.025)));
        wallType2 = new ArrayList<>(Arrays.asList(new Pair<>(WallMaterial.CORTEN_STEEL, 0.02), new Pair<>(WallMaterial.EXPANDED_POLYSTYRENE, 0.06), new Pair<>(WallMaterial.BAMBOO, 0.02)));
        refrigeratedContainer1 = new RefrigeratedContainer(wallType1, thermalVariation1);
        refrigeratedContainer2 = new RefrigeratedContainer(wallType1, wallType2, wallType2, wallType1, wallType2, wallType1, thermalVariation2);
    }

    @Test
    void toStringTest() {
        assertEquals("RefrigeratedContainer{length=2.44m, width=2.44m, height=6.1m,\n" +
                "topWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "leftWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "rightWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "bottomWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "Resistance= Top Wall: 0.31250295096259445K/W\n" +
                "Left Wall: 0.1250011803850378K/W\n" +
                "Right Wall: 0.1250011803850378K/W\n" +
                "Bottom Wall: 0.31250295096259445K/W\n" +
                "Front Door: 0.1250011803850378K/W\n" +
                "Back Door: 0.1250011803850378K/W\n" +
                "Thermal Flux= Top Wall: 80.0W/m^2\n" +
                "Left Wall: 200.0W/m^2\n" +
                "Right Wall: 200.0W/m^2\n" +
                "Bottom Wall: 80.0W/m^2\n" +
                "Front Door: 200.0W/m^2\n" +
                "Back Door: 200.0W/m^2\n" +
                "Total cargo volume= 32.24703999999999m^3\n" +
                "}", refrigeratedContainer1.toString());
        assertEquals("RefrigeratedContainer{length=2.44m, width=2.44m, height=6.1m,\n" +
                "topWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "leftWall=[[CORTEN_STEEL, 0.02], [EXPANDED_POLYSTYRENE, 0.06], [BAMBOO, 0.02]],\n" +
                "rightWall=[[CORTEN_STEEL, 0.02], [EXPANDED_POLYSTYRENE, 0.06], [BAMBOO, 0.02]],\n" +
                "bottomWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "Resistance= Top Wall: 0.31250295096259445K/W\n" +
                "Left Wall: 0.09013111142506K/W\n" +
                "Right Wall: 0.09013111142506K/W\n" +
                "Bottom Wall: 0.31250295096259445K/W\n" +
                "Front Door: 0.09013111142506K/W\n" +
                "Back Door: 0.1250011803850378K/W\n" +
                "Thermal Flux= Top Wall: 41.6W/m^2\n" +
                "Left Wall: 144.23W/m^2\n" +
                "Right Wall: 144.23W/m^2\n" +
                "Bottom Wall: 41.6W/m^2\n" +
                "Front Door: 144.23W/m^2\n" +
                "Back Door: 104.0W/m^2\n" +
                "Total cargo volume= 32.24703999999999m^3\n" +
                "}", refrigeratedContainer2.toString());

        refrigeratedContainer1.setLength(10);
        refrigeratedContainer1.setWidth(10);
        refrigeratedContainer1.setHeight(10);
        assertEquals("RefrigeratedContainer{length=10.0m, width=10.0m, height=10.0m,\n" +
                "topWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "leftWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "rightWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "bottomWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "Resistance= Top Wall: 0.018605175688509022K/W\n" +
                "Left Wall: 0.018605175688509022K/W\n" +
                "Right Wall: 0.018605175688509022K/W\n" +
                "Bottom Wall: 0.018605175688509022K/W\n" +
                "Front Door: 0.018605175688509022K/W\n" +
                "Back Door: 0.018605175688509022K/W\n" +
                "Thermal Flux= Top Wall: 1343.71W/m^2\n" +
                "Left Wall: 1343.71W/m^2\n" +
                "Right Wall: 1343.71W/m^2\n" +
                "Bottom Wall: 1343.71W/m^2\n" +
                "Front Door: 1343.71W/m^2\n" +
                "Back Door: 1343.71W/m^2\n" +
                "Total cargo volume= 960.4000000000001m^3\n" +
                "}", refrigeratedContainer1.toString());
    }

    @Test
    void invalidSetsOfWalls() {
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer2 = new RefrigeratedContainer(null, wallType2, wallType2, wallType1, wallType2, wallType1, thermalVariation2), "List of Materials for the top wall cannot be null!");
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer2 = new RefrigeratedContainer(wallType1, null, wallType2, wallType1, wallType2, wallType1, thermalVariation2), "List of Materials for the left wall cannot be null!");
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer2 = new RefrigeratedContainer(wallType1, wallType2, null, wallType1, wallType2, wallType1, thermalVariation2), "List of Materials for the right wall cannot be null!");
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer2 = new RefrigeratedContainer(wallType1, wallType2, wallType2, null, wallType2, wallType1, thermalVariation2), "List of Materials for the bottom wall cannot be null!");
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer2 = new RefrigeratedContainer(wallType1, wallType2, wallType2, wallType1, null, wallType1, thermalVariation2), "List of Materials for the bottom wall cannot be null!");
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer2 = new RefrigeratedContainer(wallType1, wallType2, wallType2, wallType1, wallType2, null, thermalVariation2), "List of Materials for the bottom wall cannot be null!");

        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer2 = new RefrigeratedContainer(new ArrayList<>(), wallType2, wallType2, wallType1, wallType2, wallType1, thermalVariation2), "List of Materials for the top wall cannot be empty!");
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer2 = new RefrigeratedContainer(wallType1, new ArrayList<>(), wallType2, wallType1, wallType2, wallType1, thermalVariation2), "List of Materials for the left wall cannot be empty!");
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer2 = new RefrigeratedContainer(wallType1, wallType2, new ArrayList<>(), wallType1, wallType2, wallType1, thermalVariation2), "List of Materials for the right wall cannot be empty!");
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer2 = new RefrigeratedContainer(wallType1, wallType2, wallType2, new ArrayList<>(), wallType2, wallType1, thermalVariation2), "List of Materials for the bottom wall cannot be empty!");
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer2 = new RefrigeratedContainer(wallType1, wallType2, wallType2, wallType1, new ArrayList<>(), wallType1, thermalVariation2), "List of Materials for the bottom wall cannot be empty!");
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer2 = new RefrigeratedContainer(wallType1, wallType2, wallType2, wallType1, wallType2, new ArrayList<>(), thermalVariation2), "List of Materials for the bottom wall cannot be empty!");

        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer1.setLength(0), "Length cannot be less or equal to 0!");
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer1.setWidth(0), "Width cannot be less or equal to 0!");
        assertThrows(IllegalArgumentException.class, () -> refrigeratedContainer1.setHeight(0), "Height cannot be less or equal to 0!");
    }

    @Test
    void controllerTest() {
        RefrigeratedContainerController controller = new RefrigeratedContainerController();
        List<List<Pair<WallMaterial, Double>>> walls = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> controller.getDetails(walls, thermalVariation1), "The list has to contain at least a composition of one wall!");

        walls.add(wallType1);
        assertEquals("RefrigeratedContainer{length=2.44m, width=2.44m, height=6.1m,\n" +
                "topWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "leftWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "rightWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "bottomWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "Resistance= Top Wall: 0.31250295096259445K/W\n" +
                "Left Wall: 0.1250011803850378K/W\n" +
                "Right Wall: 0.1250011803850378K/W\n" +
                "Bottom Wall: 0.31250295096259445K/W\n" +
                "Front Door: 0.1250011803850378K/W\n" +
                "Back Door: 0.1250011803850378K/W\n" +
                "Thermal Flux= Top Wall: 80.0W/m^2\n" +
                "Left Wall: 200.0W/m^2\n" +
                "Right Wall: 200.0W/m^2\n" +
                "Bottom Wall: 80.0W/m^2\n" +
                "Front Door: 200.0W/m^2\n" +
                "Back Door: 200.0W/m^2\n" +
                "Total cargo volume= 32.24703999999999m^3\n" +
                "}", controller.getDetails(walls, thermalVariation1).toString());
        assertEquals("RefrigeratedContainer{length=10.0m, width=10.0m, height=10.0m,\n" +
                "topWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "leftWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "rightWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "bottomWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "Resistance= Top Wall: 0.018605175688509022K/W\n" +
                "Left Wall: 0.018605175688509022K/W\n" +
                "Right Wall: 0.018605175688509022K/W\n" +
                "Bottom Wall: 0.018605175688509022K/W\n" +
                "Front Door: 0.018605175688509022K/W\n" +
                "Back Door: 0.018605175688509022K/W\n" +
                "Thermal Flux= Top Wall: 1343.71W/m^2\n" +
                "Left Wall: 1343.71W/m^2\n" +
                "Right Wall: 1343.71W/m^2\n" +
                "Bottom Wall: 1343.71W/m^2\n" +
                "Front Door: 1343.71W/m^2\n" +
                "Back Door: 1343.71W/m^2\n" +
                "Total cargo volume= 960.4000000000001m^3\n" +
                "}", controller.getDetails(walls, thermalVariation1, 10, 10, 10).toString());

        walls.add(wallType2);
        assertThrows(IllegalArgumentException.class, () -> controller.getDetails(walls, thermalVariation1), "The list can only have 1 or 4 compositions for walls");
        walls.add(wallType2);
        walls.add(wallType1);
        walls.add(wallType2);
        walls.add(wallType1);
        assertEquals("RefrigeratedContainer{length=2.44m, width=2.44m, height=6.1m,\n" +
                "topWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "leftWall=[[CORTEN_STEEL, 0.02], [EXPANDED_POLYSTYRENE, 0.06], [BAMBOO, 0.02]],\n" +
                "rightWall=[[CORTEN_STEEL, 0.02], [EXPANDED_POLYSTYRENE, 0.06], [BAMBOO, 0.02]],\n" +
                "bottomWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "Resistance= Top Wall: 0.31250295096259445K/W\n" +
                "Left Wall: 0.09013111142506K/W\n" +
                "Right Wall: 0.09013111142506K/W\n" +
                "Bottom Wall: 0.31250295096259445K/W\n" +
                "Front Door: 0.09013111142506K/W\n" +
                "Back Door: 0.1250011803850378K/W\n" +
                "Thermal Flux= Top Wall: 80.0W/m^2\n" +
                "Left Wall: 277.37W/m^2\n" +
                "Right Wall: 277.37W/m^2\n" +
                "Bottom Wall: 80.0W/m^2\n" +
                "Front Door: 277.37W/m^2\n" +
                "Back Door: 200.0W/m^2\n" +
                "Total cargo volume= 32.24703999999999m^3\n" +
                "}", controller.getDetails(walls, thermalVariation1).toString());
        assertEquals("RefrigeratedContainer{length=10.0m, width=10.0m, height=10.0m,\n" +
                "topWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "leftWall=[[CORTEN_STEEL, 0.02], [EXPANDED_POLYSTYRENE, 0.06], [BAMBOO, 0.02]],\n" +
                "rightWall=[[CORTEN_STEEL, 0.02], [EXPANDED_POLYSTYRENE, 0.06], [BAMBOO, 0.02]],\n" +
                "bottomWall=[[STAINLESS_STEEL, 0.025], [POLYURETHANE_FOAM, 0.05], [PLYWOOD, 0.025]],\n" +
                "Resistance= Top Wall: 0.018605175688509022K/W\n" +
                "Left Wall: 0.013415114624505928K/W\n" +
                "Right Wall: 0.013415114624505928K/W\n" +
                "Bottom Wall: 0.018605175688509022K/W\n" +
                "Front Door: 0.013415114624505928K/W\n" +
                "Back Door: 0.018605175688509022K/W\n" +
                "Thermal Flux= Top Wall: 1343.71W/m^2\n" +
                "Left Wall: 1863.57W/m^2\n" +
                "Right Wall: 1863.57W/m^2\n" +
                "Bottom Wall: 1343.71W/m^2\n" +
                "Front Door: 1863.57W/m^2\n" +
                "Back Door: 1343.71W/m^2\n" +
                "Total cargo volume= 960.4000000000001m^3\n" +
                "}", controller.getDetails(walls, thermalVariation1, 10, 10, 10).toString());
    }

}
