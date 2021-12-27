package lapr.project.model.store;

import lapr.project.controller.App;
import lapr.project.data.CountryStoreDb;
import lapr.project.model.Country;
import lapr.project.utils.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class CountryStore {

    public ArrayList<Country> countries;
    private final CountryStoreDb countriesDb;

    public CountryStore() {
        countries = new ArrayList<>();
        countriesDb = new CountryStoreDb();
    }

    public Country getCountryByName(String countryName) {
        int i = 0;
        Country found = null;

        while (i < countries.size() && found == null) {
            if (countries.get(i).getName().equals(countryName))
                found = countries.get(i);
            i++;
        }
        return found;
    }

    public void saveCountriesToDb() {
        DatabaseConnection connection = App.getInstance().getSql().getDatabaseConnection();
        for ( Country country: countries ) {
            countriesDb.save(connection, country);
        }
    }

    public void refresh() throws SQLException {
        countries = (ArrayList<Country>) countriesDb.getAllCountries();
    }
}
