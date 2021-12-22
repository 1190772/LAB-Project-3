package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryStoreDb {

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
