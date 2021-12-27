package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Country;
import lapr.project.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountryStoreDb implements Persistable {

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Connection connection = databaseConnection.getConnection();
        Country country = (Country) object;

        String sqlCommand = "select * from Country where alpha2_code = ?";
        boolean returnValue;
        try (PreparedStatement getCountryPreparedStatement = connection.prepareStatement(sqlCommand)) {
            getCountryPreparedStatement.setString(1, country.getAlpha2_code());
            try (ResultSet addressesResultSet = getCountryPreparedStatement.executeQuery()) {
                if (addressesResultSet.next()) {
                    sqlCommand = "update Country set alpha3_code = ?, country = ?, capital = ?, continent = ?, population = ?, latitude = ?, longitude = ? where alpha2_code = ?";
                } else {
                    sqlCommand = "insert into Country(alpha3_code, country, capital, continent, population, latitude, longitude, alpha2_code) values (?, ?, ?, ?, ?, ?, ?, ?)";
                }

                try (PreparedStatement saveCountryPreparedStatement = connection.prepareStatement(sqlCommand)) {
                    saveCountryPreparedStatement.setString(1, country.getAlpha3_code());
                    saveCountryPreparedStatement.setString(2, country.getName());
                    saveCountryPreparedStatement.setString(3, country.getCapital());
                    saveCountryPreparedStatement.setString(4, country.getContinent());
                    saveCountryPreparedStatement.setDouble(5, country.getPopulation());
                    saveCountryPreparedStatement.setDouble(6, country.getLatitude());
                    saveCountryPreparedStatement.setDouble(7, country.getLongitude());
                    saveCountryPreparedStatement.setString(8, country.getAlpha2_code());
                    saveCountryPreparedStatement.executeUpdate();
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
        Country country = (Country) object;

        boolean returnValue;
        try {
            String sqlCommand;
            sqlCommand = "delete from Country where alpha2_code = ?";
            try (PreparedStatement deleteCountryPreparedStatement = conn.prepareStatement(sqlCommand)) {
                deleteCountryPreparedStatement.setString(1, country.getAlpha2_code());
                deleteCountryPreparedStatement.executeUpdate();
                returnValue = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDb.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    public List<Country> getAllCountries() throws SQLException {
        Connection connection = App.getInstance().getSql().getDatabaseConnection().getConnection();
        String sqlCommand = "select * from Country";
        ResultSet countries;
        ArrayList<Country> res = new ArrayList<>();

        try (PreparedStatement seaDistancesPreparedStatement = connection.prepareStatement(sqlCommand)) {
            countries = seaDistancesPreparedStatement.executeQuery();
            while (countries.next()) {
                res.add(new Country(countries.getNString(1),
                        countries.getString(2),
                        countries.getString(3),
                        countries.getString(4),
                        countries.getString(5),
                        countries.getDouble(6),
                        countries.getDouble(7),
                        countries.getDouble(8)));
            }
            countries.close();
        }
        return res;
    }

    public String getCountryCodeByName(String name) throws SQLException {
        String res = "";
        Connection connection = App.getInstance().getSql().getDatabaseConnection().getConnection();
        String sqlCommand = "select alpha2_code from Country where country = ?";

        try (PreparedStatement CountryCodePreparedStatement = connection.prepareStatement(sqlCommand)) {
            CountryCodePreparedStatement.setString(1, name);

            try (ResultSet shipsResultSet = CountryCodePreparedStatement.executeQuery()) {
                if(shipsResultSet.next())
                    res = shipsResultSet.getString(1);
            }
        }
        return res;
    }
}
