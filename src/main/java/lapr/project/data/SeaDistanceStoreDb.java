package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Port;
import lapr.project.model.SeaDistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeaDistanceStoreDb {

    public List<SeaDistance> getAllSeaDistances() throws SQLException {
        Connection connection = App.getInstance().getSql().getDatabaseConnection().getConnection();
        String sqlCommand = "select * from Sea_Distance";
        ResultSet seaDistances;
        ArrayList<SeaDistance> res = new ArrayList<>();

        try (PreparedStatement seaDistancesPreparedStatement = connection.prepareStatement(sqlCommand)) {
            seaDistances = seaDistancesPreparedStatement.executeQuery();
            while (seaDistances.next()) {
                res.add(new SeaDistance(Integer.parseInt(seaDistances.getString("id_port1")),
                                        Integer.parseInt(seaDistances.getString("id_port2")),
                                        seaDistances.getInt("distance")));
            }
            seaDistances.close();
        }
        return res;
    }
}
