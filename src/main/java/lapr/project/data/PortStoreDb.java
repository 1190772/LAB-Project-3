package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Port;
import lapr.project.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortStoreDb implements Persistable {

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {

    Connection connection = databaseConnection.getConnection();
    CountryStoreDb countryStoreDb = new CountryStoreDb();
    Port port = (Port) object;

    String sqlCommand = "select * from Port where id_port = ?";
    boolean returnValue;
    try (PreparedStatement getPortsPreparedStatement = connection.prepareStatement(sqlCommand)) {
        getPortsPreparedStatement.setInt(1, port.getID());
        try (ResultSet addressesResultSet = getPortsPreparedStatement.executeQuery()) {
            if (addressesResultSet.next()) {
                sqlCommand = "update Port set name = ?, capacity = ?, country_code = ?, latitude = ?, longitude = ? where id_port = ?";
            } else {
                sqlCommand = "insert into Port(name, capacity, country_code, latitude, longitude, id_port) values (?, ?, ?, ?, ?, ?)";
            }

            try (PreparedStatement savePortPreparedStatement = connection.prepareStatement(sqlCommand)) {
                savePortPreparedStatement.setString(1, port.getName());
                savePortPreparedStatement.setInt(2, port.getCapacity());
                savePortPreparedStatement.setString(3, countryStoreDb.getCountryCodeByName(port.getCountry().getName()));
                savePortPreparedStatement.setDouble(4, port.getLatitude());
                savePortPreparedStatement.setDouble(5, port.getLongitude());
                savePortPreparedStatement.setInt(6, port.getID());
                savePortPreparedStatement.executeUpdate();
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

    public List<Port> getAllPorts() throws SQLException {
        Connection connection = App.getInstance().getSql().getDatabaseConnection().getConnection();
        String sqlCommand = "select * from Port";
        ResultSet ports;
        ArrayList<Port> res = new ArrayList<>();

        try (PreparedStatement shipsPreparedStatement = connection.prepareStatement(sqlCommand)) {
            ports = shipsPreparedStatement.executeQuery();
            while (ports.next()) {
                res.add(new Port(ports.getInt("id_port"),
                            ports.getString("name"),
                            App.getInstance().getCompany().getCountryStore().getCountryByAlpha2code(ports.getString("country_code")),
                            ports.getDouble("latitude"),
                            ports.getDouble("longitude"),
                            ports.getInt("Capacity")
                        ));
            }
            ports.close();
        }
        return res;
    }
}
