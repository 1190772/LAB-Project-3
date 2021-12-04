package lapr.project.data;

import lapr.project.model.Port;
import lapr.project.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortStoreDb implements Persistable {

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {

    Connection connection = databaseConnection.getConnection();
    Port port = (Port) object;

    String sqlCommand = "select * from Port where id_port = ?";
    boolean returnValue;
    try (PreparedStatement getAddressedPreparedStatement = connection.prepareStatement(sqlCommand)) {
        getAddressedPreparedStatement.setInt(1, port.getID());
        try (ResultSet addressesResultSet = getAddressedPreparedStatement.executeQuery()) {
            if (addressesResultSet.next()) {
                sqlCommand = "update Port set name = ?, continent = ?, country = ?, latitude = ?, longitude = ? where id_port = ?";
            } else {
                sqlCommand = "insert into Port(name, continent, country, latitude, longitude, id_port) values (?, ?, ?, ?, ?, ?)";
            }

            try (PreparedStatement saveAddressPreparedStatement = connection.prepareStatement(sqlCommand)) {
                saveAddressPreparedStatement.setString(1, port.getName());
                saveAddressPreparedStatement.setString(2, port.getContinent());
                saveAddressPreparedStatement.setString(3, port.getCountry());
                saveAddressPreparedStatement.setDouble(4, port.getLatitude());
                saveAddressPreparedStatement.setDouble(5, port.getLongitude());
                saveAddressPreparedStatement.setInt(6, port.getID());
                saveAddressPreparedStatement.executeUpdate();
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
    Port port = (Port) object;

    boolean returnValue;
    try {
        String sqlCommand;
        sqlCommand = "delete from Port where id_port = ?";
        try (PreparedStatement deleteAddressPreparedStatement = conn.prepareStatement(sqlCommand)) {
            deleteAddressPreparedStatement.setInt(1, port.getID());
            deleteAddressPreparedStatement.executeUpdate();
            returnValue = true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(PortStoreDb.class.getName()).log(Level.SEVERE, null, ex);
        databaseConnection.registerError(ex);
        returnValue = false;
    }
    return returnValue;
    }

    public ResultSet getAllPorts(DatabaseConnection databaseConnection) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        String sqlCommand = "select * from Port";
        try (PreparedStatement shipsPreparedStatement = connection.prepareStatement(sqlCommand)) {
            return shipsPreparedStatement.executeQuery();
        }
    }
}