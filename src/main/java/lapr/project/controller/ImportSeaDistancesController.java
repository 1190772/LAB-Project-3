package lapr.project.controller;

import lapr.project.model.SeaDistance;
import lapr.project.model.store.SeaDistanceStore;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ImportSeaDistancesController {

    private final SeaDistanceStore seadistanceStore;

    public ImportSeaDistancesController() { seadistanceStore = App.getInstance().getCompany().getSeadistanceStore(); }

    public void importSeaDistances(String filePath) {
        String[] parameters;

        try (Scanner in = new Scanner((new FileReader(filePath)))) {
            in.nextLine();
            while (in.hasNextLine()) {
                parameters = in.nextLine().split(",");
                String idPort1 = parameters[1];
                String idPort2 = parameters[4];
                int distance = Integer.parseInt(parameters[6]);
                seadistanceStore.addSeaDistance(new SeaDistance(idPort1, idPort2, distance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSeaDistancesToDb() {
        seadistanceStore.saveSeaDistancesToDb();
    }
}
