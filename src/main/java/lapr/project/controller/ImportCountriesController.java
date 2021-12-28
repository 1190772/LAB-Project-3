package lapr.project.controller;

import lapr.project.model.Country;
import lapr.project.model.store.CountryStore;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ImportCountriesController {

    private final CountryStore countryStore;

    public ImportCountriesController() { countryStore = App.getInstance().getCompany().getCountryStore(); }

    public void importCountries(String filePath) {
        String[] parameters;
        ArrayList<Country> list = countryStore.getCountries();

        try (Scanner in = new Scanner((new FileReader(filePath)))) {
            in.nextLine();
            while (in.hasNextLine()) {
                parameters = in.nextLine().split(",");
                String continent = parameters[0];
                String alpha2code = parameters[1];
                String alpha3code = parameters[2];
                String name = parameters[3];
                double population = Double.parseDouble(parameters[4]);
                String capital = parameters[5];
                double latitude = Double.parseDouble(parameters[6]);
                double longitude = Double.parseDouble(parameters[7]);
                list.add(new Country(alpha2code, alpha3code, name, capital, continent, population, latitude, longitude));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCountriesToDb() {
        countryStore.saveCountriesToDb();
    }
}
