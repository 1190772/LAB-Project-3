package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.DatabaseConnection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseFunctions {
    private static final String SHIP_IMO = "ship_imo char(10)";
    private static final String ID_TRUCK = "id_truck integer";
    private static final String ID_CONTAINER = "id_container char(11)";
    private static final String POSITION_CODE = "position_code number(6,0)";
    private static final String CARGO_WEIGHT = "cargo_weight integer";
    private static final String REFRIGERATION_TEMPERATURE = "refrigeration_temperature number(3,1)";
    private static final String TARE = "tare integer";
    private static final String VALUE_LENGTH = "value_length integer";
    private static final String VALUE_WIDTH = "value_width integer";
    private static final String VALUE_HEIGHT = "value_height integer";
    private static final String DATE_TIME_START = "date_time_start timestamp";
    private static final String DATE_TIME_END = "date_time_end timestamp";
    private static final String ID_START_PORT = "id_start_port char(6)";
    private static final String ID_DESTINATION_PORT = "id_destination_port char(6)";
    private static final String ID_START_WAREHOUSE = "id_start_warehouse integer";
    private static final String ID_DESTINATION_WAREHOUSE = "id_destination_warehouse integer";
    private static final String DROP_TABLE_RESULT = "drop table result";

    public static void executeLine(DatabaseConnection databaseConnection, String sqlLine) {
        try {
            Statement statement = databaseConnection.getConnection().createStatement();
            statement.execute(sqlLine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void scriptInTextFile(DatabaseConnection databaseConnection, String filename) {
        try (Scanner in = new Scanner((new FileReader(filename)))) {
            StringBuilder sqlLine = new StringBuilder();
            while (in.hasNextLine()) {
                sqlLine.append(in.nextLine());
                if (sqlLine.toString().endsWith(";")) {
                    executeLine(databaseConnection, sqlLine.toString().replace(";", ""));
                    sqlLine = new StringBuilder();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadFunction(DatabaseConnection databaseConnection, String filename) {
        try (Scanner in = new Scanner((new FileReader(filename)))) {
            StringBuilder sqlLine = new StringBuilder();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.equals("/")) {
                    executeLine(databaseConnection, sqlLine.toString().trim());
                    return;
                }
                sqlLine.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String prepForResultSet(DatabaseConnection databaseConnection, String function, String columnsForTable) {
        String sqlCommand = "create table result(" + columnsForTable + ")";
        executeLine(databaseConnection, sqlCommand);

        sqlCommand = "declare\n" +
                "        tempCursor SYS_REFCURSOR;\n" +
                "        type resultRowType is table of result%rowtype;\n" +
                "        c_t resultRowType;\n" +
                "        begin\n" +
                "        tempCursor:=" + function + ";\n" +
                "        fetch tempCursor bulk collect into c_t;\n" +
                "        close tempCursor;\n" +
                "        forall indx in c_t.first..c_t.last\n" +
                "        insert into result\n" +
                "        values c_t(indx);\n" +
                "        end;\n";
        executeLine(databaseConnection, sqlCommand);

        return "select * from result";
    }

    public static String getContainersRoute(DatabaseConnection databaseConnection, String clientID, String containerID) {
        loadFunction(databaseConnection, "docs/Sprint 3/US/US305/US305Script.sql");

        StringBuilder result = new StringBuilder();
        result.append("Route:\nContainer: ").append(containerID).append("\n");
        String function = "getContainersRoute(" + clientID + ", '" + containerID + "')";
        String columnsForTable = SHIP_IMO + ",\n" +
                ID_TRUCK + ",\n" +
                ID_START_PORT + ",\n" +
                ID_DESTINATION_PORT + ",\n" +
                ID_START_WAREHOUSE + ",\n" +
                ID_DESTINATION_WAREHOUSE + ",\n" +
                DATE_TIME_START + ",\n" +
                DATE_TIME_END;

        Connection connection = databaseConnection.getConnection();



        try (PreparedStatement resultPreparedStatement = connection.prepareStatement(prepForResultSet(databaseConnection, function, columnsForTable))) {
            try (ResultSet resultSet = resultPreparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String aid = resultSet.getString(1);
                    if (aid != null)
                        result.append("ship_imo = ").append(aid);
                    else
                        result.append("id_truck = ").append(resultSet.getString(2));

                    aid = resultSet.getString(3);
                    if (aid != null)
                        result.append(" id_start_port = ").append(aid);
                    else
                        result.append(" id_start_warehouse = ").append(resultSet.getString(5));

                    aid = resultSet.getString(4);
                    if (aid != null)
                        result.append(" id_destination_port = ").append(aid);
                    else
                        result.append(" id_destination_warehouse = ").append(resultSet.getString(6));

                    result.append(" date_time_start = ").append(resultSet.getTimestamp(7));
                    result.append(" date_time_end = ").append(resultSet.getTimestamp(8)).append("\n");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseFunctions.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }

        executeLine(databaseConnection, DROP_TABLE_RESULT);

        return result.toString();
    }

    public static String getContainersOfShip(DatabaseConnection databaseConnection, String shipIMO) {
        loadFunction(databaseConnection, "docs/Sprint 3/US/US311/US311Script.sql");

        StringBuilder result = new StringBuilder();
        result.append("Ship: ").append(shipIMO).append("\n");
        String function = "getContainersOfShip('" + shipIMO + "')";
        String columnsForTable = ID_CONTAINER + ",\n" +
                POSITION_CODE + ",\n" +
                CARGO_WEIGHT + ",\n" +
                REFRIGERATION_TEMPERATURE;

        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement resultPreparedStatement = connection.prepareStatement(prepForResultSet(databaseConnection, function, columnsForTable))) {
            try (ResultSet resultSet = resultPreparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result.append("id_container = ").append(resultSet.getString(1));
                    String aid = resultSet.getString(2);
                    if (aid == null)
                        result.append(" This container is not refrigerated");
                    else
                        result.append(" refrigeration_temperature = ").append(aid).append("ºC");
                    result.append(" position_code = ").append(resultSet.getString(3));
                    result.append(" cargo_weight = ").append(resultSet.getString(4)).append("\n");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseFunctions.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }

        executeLine(databaseConnection, DROP_TABLE_RESULT);

        return result.toString();
    }

    public static String getTripEnergyDetails(int tripID) {
        String res = "An error occurred.";
        Connection connection = App.getInstance().getCompany().getDatabaseConnection().getConnection();

        String sqlCommand = "select tripEnergyDetails(?) from dual";
        try (PreparedStatement tripPreparedStatement = connection.prepareStatement(sqlCommand)) {
            tripPreparedStatement.setInt(1, tripID);
            try (ResultSet tripResultSet = tripPreparedStatement.executeQuery()) {
                tripResultSet.next();
                res = tripResultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }

    public static String getResourcesOfPortNextWeek(DatabaseConnection databaseConnection, String portID) {
        loadFunction(databaseConnection, "docs/Sprint 4/US/US407/US407Script.sql");

        StringBuilder result = new StringBuilder();
        result.append("Date/Time/Ship/Truck/Container/Position/Refrigeration Temperature/Length/Width/Height/Total weight\n");
        String function = "getContainersToUnloadInVehicleNextWeek('" + portID + "')";
        String columnsForTable = DATE_TIME_END + ",\n" +
                SHIP_IMO + ",\n" +
                ID_TRUCK + ",\n" +
                ID_CONTAINER + ",\n" +
                POSITION_CODE + ",\n" +
                CARGO_WEIGHT + ",\n" +
                REFRIGERATION_TEMPERATURE + ",\n" +
                TARE + ",\n" +
                VALUE_LENGTH + ",\n" +
                VALUE_WIDTH + ",\n" +
                VALUE_HEIGHT;


        Connection connection = databaseConnection.getConnection();

        try (PreparedStatement resultPreparedStatement = connection.prepareStatement(prepForResultSet(databaseConnection, function, columnsForTable))) {
            try (ResultSet resultSet = resultPreparedStatement.executeQuery()) {
                result.append("Containers to unload:\n\t");
                while (resultSet.next()) {
                    for (int i = 1; i < 6; i++)
                        result.append(resultSet.getString(i)).append(" ");
                    result.append(resultSet.getString(7)).append(" ");
                    for (int i = 9; i < 12; i++)
                        result.append(resultSet.getString(i)).append(" ");
                    result.append(resultSet.getInt(6) + resultSet.getInt(8)).append(" ");
                    result.append("\n\t");
                }
            }
        } catch (SQLException ex) {
            result.append("No Containers to unload.");
            Logger.getLogger(DatabaseFunctions.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }

        executeLine(databaseConnection, DROP_TABLE_RESULT);


        function = "getContainersToLoadInVehicleNextWeek('" + portID + "')";
        columnsForTable = DATE_TIME_START + ",\n" +
                SHIP_IMO + ",\n" +
                ID_TRUCK + ",\n" +
                ID_CONTAINER + ",\n" +
                POSITION_CODE + ",\n" +
                CARGO_WEIGHT + ",\n" +
                REFRIGERATION_TEMPERATURE + ",\n" +
                TARE + ",\n" +
                VALUE_LENGTH + ",\n" +
                VALUE_WIDTH + ",\n" +
                VALUE_HEIGHT;

        try (PreparedStatement resultPreparedStatement = connection.prepareStatement(prepForResultSet(databaseConnection, function, columnsForTable))) {
            try (ResultSet resultSet = resultPreparedStatement.executeQuery()) {
                result.append("\nContainers to load:\n\t");
                while (resultSet.next()) {
                    for (int i = 1; i < 6; i++)
                        result.append(resultSet.getString(i)).append(" ");
                    result.append(resultSet.getString(7)).append(" ");
                    for (int i = 9; i < 12; i++)
                        result.append(resultSet.getString(i)).append(" ");
                    result.append(resultSet.getInt(6) + resultSet.getInt(8)).append(" ");
                    result.append("\n\t");
                }
            }
        } catch (SQLException ex) {
            result.append("No Containers to load.");
            Logger.getLogger(DatabaseFunctions.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }

        executeLine(databaseConnection, DROP_TABLE_RESULT);

        return result.toString();
    }

    public static String getLightTrips() {
        String res = "An error occurred.";
        Connection connection = App.getInstance().getCompany().getDatabaseConnection().getConnection();

        String sqlCommand = "select lightTrips from dual";
        try (PreparedStatement tripPreparedStatement = connection.prepareStatement(sqlCommand)) {
            try (ResultSet tripResultSet = tripPreparedStatement.executeQuery()) {
                tripResultSet.next();
                res = tripResultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }
}
