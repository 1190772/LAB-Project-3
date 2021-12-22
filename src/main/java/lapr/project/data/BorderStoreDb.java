package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Border;
import lapr.project.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorderStoreDb {

    public List<Border> getAllBorders(ArrayList<Country> countries) throws SQLException {
        Connection connection = App.getInstance().getSql().getDatabaseConnection().getConnection();
        String sqlCommand = "select * from Border";
        ResultSet borders;
        ArrayList<Border> res = new ArrayList<>();
        String countryID;
        int country1Index;
        int country2Index;
        int i;

        try (PreparedStatement bordersPreparedStatement = connection.prepareStatement(sqlCommand)) {
            borders = bordersPreparedStatement.executeQuery();
            while (borders.next()) {
                country1Index = -1;
                country2Index = -1;

                countryID = borders.getString("id_country1");
                i = 0;
                while (country1Index == -1 && i < countries.size()) {
                    if (countries.get(i).getAlpha2_code() .equals(countryID))
                        country1Index = i;
                    i++;
                }

                countryID = borders.getString("id_country2");
                i = 0;
                while (country2Index == -1 && i < countries.size()) {
                    if (countries.get(i).getAlpha2_code().equals(countryID))
                        country2Index = i;
                    i++;
                }

                if (country1Index != -1 && country2Index != -1)
                    res.add(new Border(countries.get(country1Index), countries.get(country2Index)));
            }
            borders.close();
        }
    return res;
    }
}
