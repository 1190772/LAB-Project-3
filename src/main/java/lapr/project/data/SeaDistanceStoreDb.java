package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.SeaDistance;
import lapr.project.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeaDistanceStoreDb implements Persistable {

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Connection connection = databaseConnection.getConnection();
        SeaDistance seaDistance = (SeaDistance) object;

        String sqlCommand = "select * from Sea_Distance where id_port1 = ? and id_port2 = ?";
        boolean returnValue;
        try (PreparedStatement getSeaDistancePreparedStatement = connection.prepareStatement(sqlCommand)) {
            getSeaDistancePreparedStatement.setString(1, seaDistance.getIdPort1());
            getSeaDistancePreparedStatement.setString(2, seaDistance.getIdPort2());
            try (ResultSet addressesResultSet = getSeaDistancePreparedStatement.executeQuery()) {
                if (addressesResultSet.next()) {
                    sqlCommand = "update Sea_Distance set distance = ? where id_port1 = ? and id_port2 = ?";
                } else {
                    sqlCommand = "insert into Sea_Distance(distance, id_port1, id_port2) values (?, ?, ?)";
                }

                try (PreparedStatement saveSeaDistancePreparedStatement = connection.prepareStatement(sqlCommand)) {
                    saveSeaDistancePreparedStatement.setInt(1, seaDistance.getDistance());
                    saveSeaDistancePreparedStatement.setString(2, seaDistance.getIdPort1());
                    saveSeaDistancePreparedStatement.setString(3, seaDistance.getIdPort2());
                    saveSeaDistancePreparedStatement.executeUpdate();
                    returnValue = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDb.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public boolean delete(DatabaseConnection databaseConnection, Object object) {
        Connection conn = databaseConnection.getConnection();
        SeaDistance seaDistance = (SeaDistance) object;

        boolean returnValue;
        try {
            String sqlCommand;
            sqlCommand = "delete from Sea_Distance where id_port1 = ? and id_port2 = ?";
            try (PreparedStatement deleteSeaDistancePreparedStatement = conn.prepareStatement(sqlCommand)) {
                deleteSeaDistancePreparedStatement.setString(1, seaDistance.getIdPort1());
                deleteSeaDistancePreparedStatement.setString(2, seaDistance.getIdPort2());
                deleteSeaDistancePreparedStatement.executeUpdate();
                returnValue = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDb.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }


    public List<SeaDistance> getAllSeaDistances() throws SQLException {
        Connection connection = App.getInstance().getSql().getDatabaseConnection().getConnection();
        String sqlCommand = "select * from Sea_Distance";
        ArrayList<SeaDistance> res = new ArrayList<>();

        try (PreparedStatement seaDistancesPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet seaDistances = seaDistancesPreparedStatement.executeQuery()) {
            while (seaDistances.next()) {
                res.add(new SeaDistance(seaDistances.getString("id_port1"),
                        seaDistances.getString("id_port2"),
                        seaDistances.getInt("distance")));
                }
            }
        }
        return res;
    }
}
