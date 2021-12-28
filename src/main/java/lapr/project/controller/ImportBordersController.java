package lapr.project.controller;

import lapr.project.model.Border;
import lapr.project.model.Country;
import lapr.project.model.store.BorderStore;
import lapr.project.model.store.CountryStore;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ImportBordersController {

    private final BorderStore borderStore;
    private final CountryStore countryStore;

    public ImportBordersController() {
        borderStore = App.getInstance().getCompany().getBorderStore();
        countryStore = App.getInstance().getCompany().getCountryStore();
    }

    public void importBorders(String filePath) {
        String[] parameters;
        List<Border> list = borderStore.getBorders();

        try (Scanner in = new Scanner((new FileReader(filePath)))) {
        in.nextLine();
        while (in.hasNextLine()) {
            parameters = in.nextLine().split(",");
            Country country1 = countryStore.getCountryByName(parameters[0]);
            Country country2 = countryStore.getCountryByName(parameters[1]);
            if (country1 != null && country2 != null)
                list.add(new Border(country1, country2));
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveBordersToDb() {
        borderStore.saveBordersToDb();
    }
}
