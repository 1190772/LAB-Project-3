package lapr.project.controller;

import lapr.project.model.SeaDistance;
import lapr.project.model.store.SeaDistanceStore;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ImportSeaDistancesController {

    private final SeaDistanceStore seadistanceStore;

    public ImportSeaDistancesController() { seadistanceStore = App.getInstance().getCompany().getSeadistanceStore(); }

    public void importSeaDistances(String filePath) {
        String[] parameters;
        List<SeaDistance> list = seadistanceStore.getSeadists();

        try (Scanner in = new Scanner((new FileReader(filePath)))) {
            in.nextLine();
            while (in.hasNextLine()) {
                parameters = in.nextLine().split(",");
                int idPort1 = Integer.parseInt(parameters[1]);
                int idPort2 = Integer.parseInt(parameters[4]);
                int distance = Integer.parseInt(parameters[6]);
                list.add(new SeaDistance(idPort1, idPort2, distance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSeaDistancesToDb() {
        seadistanceStore.saveSeaDistancesToDb();
    }
}
