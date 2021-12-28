package lapr.project.model.store;

import lapr.project.controller.App;
import lapr.project.data.BorderStoreDb;
import lapr.project.model.Border;
import lapr.project.utils.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorderStore {

    private ArrayList<Border> borders;
    private final BorderStoreDb bordersDb;

    public BorderStore() {
        borders = new ArrayList<>();
        bordersDb = new BorderStoreDb();
    }

    public void saveBordersToDb() {
        DatabaseConnection connection = App.getInstance().getSql().getDatabaseConnection();
        for ( Border border: borders ) {
            bordersDb.save(connection, border);
        }
    }

    public void refresh() throws SQLException {
        borders = (ArrayList<Border>) bordersDb.getAllBorders(App.getInstance().getCompany().getCountryStore().getCountries());
    }

    public List<Border> getBorders() { return new ArrayList<>(borders); }

    public void addBorder(Border border) { borders.add(border); }
}
