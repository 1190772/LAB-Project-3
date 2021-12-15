package lapr.project.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryStoreDb {

    public String getCountryCodeByName(Connection connection, String name) throws SQLException {
        String res = "";

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
