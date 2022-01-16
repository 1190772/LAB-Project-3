package lapr.project.controller;

import lapr.project.data.DatabaseFunctions;
import lapr.project.model.fsiap.RefrigeratedContainer;
import lapr.project.model.fsiap.WallMaterial;
import oracle.ucp.util.Pair;

import java.util.ArrayList;

public class TripEnergyController {

    public String getTripEnergy(int tripID) {
        String[] values = DatabaseFunctions.getTripEnergyDetails(tripID).split(",");
        if (!values[0].equals("An error occurred."))
            return calculateTripEnergy(Integer.parseInt(values[0]),
                                       Integer.parseInt(values[1]),
                                       Float.parseFloat(values[2]),
                                       Float.parseFloat(values[3]),
                                       Integer.parseInt(values[4]),
                                       Integer.parseInt(values[5]));
        else return values[0];
    }

    public String calculateTripEnergy(int seconds1, int seconds2, float temperature1, float temperature2, int amount1, int amount2) {
        double energy1 = 0;
        double energy2 = 0;

        ArrayList<Pair<WallMaterial, Double>> materialList1 = new ArrayList<>();
        materialList1.add(new Pair<>(WallMaterial.STAINLESS_STEEL, WallMaterial.STAINLESS_STEEL.getK()));
        materialList1.add(new Pair<>(WallMaterial.POLYURETHANE_FOAM, WallMaterial.POLYURETHANE_FOAM.getK()));
        materialList1.add(new Pair<>(WallMaterial.PLYWOOD, WallMaterial.PLYWOOD.getK()));
        RefrigeratedContainer type1part1 = new RefrigeratedContainer(materialList1, temperature1 + 5);
        double totalArea = 0;
        totalArea += type1part1.getWallArea("Door") * 2;
        totalArea += type1part1.getWallArea("Side") * 2;
        totalArea += type1part1.getWallArea("Top") * 2;
        double thermalFlux1part1 = 0;
        for (double thermalFlux : type1part1.getThermalFlux())
            thermalFlux1part1 += thermalFlux;
        energy1 += thermalFlux1part1 * seconds1 * totalArea;
        RefrigeratedContainer type1part2 = new RefrigeratedContainer(materialList1, temperature2 + 5);
        double thermalFlux1part2 = 0;
        for (double thermalFlux : type1part2.getThermalFlux())
            thermalFlux1part2 += thermalFlux;
        energy1 += thermalFlux1part2 * seconds2 * totalArea;
        energy1 *= amount1;

        ArrayList<Pair<WallMaterial, Double>> materialList2 = new ArrayList<>();
        materialList2.add(new Pair<>(WallMaterial.CORTEN_STEEL, WallMaterial.CORTEN_STEEL.getK()));
        materialList2.add(new Pair<>(WallMaterial.EXPANDED_POLYSTYRENE, WallMaterial.EXPANDED_POLYSTYRENE.getK()));
        materialList2.add(new Pair<>(WallMaterial.BAMBOO, WallMaterial.BAMBOO.getK()));
        RefrigeratedContainer type2part1 = new RefrigeratedContainer(materialList2, temperature1 - 7);
        double thermalFlux2part1 = 0;
        for (double thermalFlux : type2part1.getThermalFlux())
            thermalFlux2part1 += thermalFlux;
        energy2 += thermalFlux2part1 * seconds1 * totalArea;
        RefrigeratedContainer type2part2 = new RefrigeratedContainer(materialList2, temperature2 - 7);
        double thermalFlux2part2 = 0;
        for (double thermalFlux : type2part2.getThermalFlux())
            thermalFlux2part2 += thermalFlux;
        energy2 += thermalFlux2part2 * seconds2 * totalArea;
        energy2 *= amount2;

        return "Total energy for -5ºC containers: " + energy1 + "J.\nTotal energy for 7ºC containers: " + energy2 + "J.";
    }

}
