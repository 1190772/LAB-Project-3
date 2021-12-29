package lapr.project.model.store;

import lapr.project.controller.App;
import lapr.project.data.SeaDistanceStoreDb;
import lapr.project.model.SeaDistance;
import lapr.project.utils.DatabaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeaDistanceStore {

    private ArrayList<SeaDistance> seadists;
    private final SeaDistanceStoreDb seadistsDb;

    public SeaDistanceStore() {
        seadists = new ArrayList<>();
        seadistsDb = new SeaDistanceStoreDb();
    }

    public void saveSeaDistancesToDb() {
        DatabaseConnection connection = App.getInstance().getSql().getDatabaseConnection();
        for ( SeaDistance seaDistance: seadists ) {
            seadistsDb.save(connection, seaDistance);
        }
    }

    public void refresh() {
        try {
            seadists = (ArrayList<SeaDistance>) seadistsDb.getAllSeaDistances();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SeaDistance> getSeadists() { return new ArrayList<>(seadists); }

    public void addSeaDistance(SeaDistance seadist) { seadists.add(seadist); }
}
