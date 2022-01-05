package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.model.Border;
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

public class BorderStoreDb implements Persistable{

    @Override
    public boolean save(DatabaseConnection databaseConnection, Object object) {
        Connection connection = databaseConnection.getConnection();
        Border border = (Border) object;

        String sqlCommand = "select * from Border where id_country1 = ? and id_country2 = ?";
        boolean returnValue = true;
        try (PreparedStatement getBordersPreparedStatement = connection.prepareStatement(sqlCommand)) {
            getBordersPreparedStatement.setString(1, border.getCountry1().getAlpha2code());
            getBordersPreparedStatement.setString(2, border.getCountry2().getAlpha2code());
            try (ResultSet bordersResultSet = getBordersPreparedStatement.executeQuery()) {
                if (!bordersResultSet.next()) {
                    sqlCommand = "insert into Border(id_country1, id_country2) values (?, ?)";
                    try (PreparedStatement saveBorderPreparedStatement = connection.prepareStatement(sqlCommand)) {
                        saveBorderPreparedStatement.setString(1, border.getCountry1().getAlpha2code());
                        saveBorderPreparedStatement.setString(2, border.getCountry2().getAlpha2code());
                        saveBorderPreparedStatement.executeUpdate();
                    }
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
        Border border = (Border) object;

        boolean returnValue;
        try {
            String sqlCommand;
            sqlCommand = "delete from Border where id_country1 = ? and id_country2 = ?";
            try (PreparedStatement deleteBorderPreparedStatement = conn.prepareStatement(sqlCommand)) {
                deleteBorderPreparedStatement.setString(1, border.getCountry1().getAlpha2code());
                deleteBorderPreparedStatement.setString(2, border.getCountry2().getAlpha2code());
                deleteBorderPreparedStatement.executeUpdate();
                returnValue = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PortStoreDb.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
            returnValue = false;
        }
        return returnValue;
    }

    public List<Border> getAllBorders(List<Country> countries) throws SQLException {
        Connection connection = App.getInstance().getCompany().getDatabaseConnection().getConnection();
        String sqlCommand = "select * from Border";
        ArrayList<Border> res = new ArrayList<>();
        String countryID;
        int country1Index;
        int country2Index;
        int i;

        try (PreparedStatement bordersPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet borders = bordersPreparedStatement.executeQuery()) {
                while (borders.next()) {
                    country1Index = -1;
                    country2Index = -1;

                    countryID = borders.getString("id_country1");
                    i = 0;
                    while (country1Index == -1 && i < countries.size()) {
                        if (countries.get(i).getAlpha2code().equals(countryID))
                            country1Index = i;
                        i++;
                    }

                    countryID = borders.getString("id_country2");
                    i = 0;
                    while (country2Index == -1 && i < countries.size()) {
                        if (countries.get(i).getAlpha2code().equals(countryID))
                            country2Index = i;
                        i++;
                    }

                    if (country1Index != -1 && country2Index != -1)
                        res.add(new Border(countries.get(country1Index), countries.get(country2Index)));
                }
            }
        }
    return res;
    }
}
