package lapr.project.data;

import lapr.project.controller.App;

import java.sql.*;

public class PortOccupationDb {

    public String getPortOccupation(String portID, String month) {
        Connection connection = App.getInstance().getSql().getDatabaseConnection().getConnection();
        String report = "An error occurred.";

        String sqlCommand = "SELECT portOccupation(?, TO_DATE(?,'YYYY-MM')) from dual";
        try (PreparedStatement portOccupationPreparedStatement = connection.prepareStatement(sqlCommand)) {
            portOccupationPreparedStatement.setString(1, portID);
            portOccupationPreparedStatement.setString(2, month);
            try (ResultSet portOccupationResultSet = portOccupationPreparedStatement.executeQuery()) {
                portOccupationResultSet.next();
                report = portOccupationResultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return report;
    }
}
