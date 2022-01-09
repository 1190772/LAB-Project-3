package lapr.project.data;

import lapr.project.utils.DatabaseConnection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseFunctions {

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

    private static ResultSet getResultSetFromSysRefCursor(DatabaseConnection databaseConnection, String function, String columnsForTable) throws SQLException {
        Connection connection = databaseConnection.getConnection();
        ResultSet result;
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

        sqlCommand = "select * from result";

        PreparedStatement resultPreparedStatement = connection.prepareStatement(sqlCommand);
        result = resultPreparedStatement.executeQuery();
        resultPreparedStatement.close();


        sqlCommand = "drop table result";
        executeLine(databaseConnection, sqlCommand);
        return result;
    }

    private static void loadFunction(DatabaseConnection databaseConnection, String filename) {
        try (Scanner in = new Scanner((new FileReader(filename)))) {
            StringBuilder sqlLine = new StringBuilder();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.equals("/")) {
                    executeLine(databaseConnection,sqlLine.toString().trim());
                    return;
                }
                sqlLine.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getContainersRoute(DatabaseConnection databaseConnection, String clientID, String containerID) {
        loadFunction(databaseConnection, "docs/Sprint 3/US/US305/US305Script.sql");

        StringBuilder result = new StringBuilder();
        result.append("Route:\nContainer: ").append(containerID).append("\n");
        String function = "getContainersRoute(" + clientID + ", '" + containerID + "')";
        String columnsForTable = "ship_imo                  char(10),\n" +
                "    id_truck                  integer,\n" +
                "    id_start_port             char(6),\n" +
                "    id_destination_port       char(6),\n" +
                "    id_start_warehouse        integer,\n" +
                "    id_destination_warehouse  integer,\n" +
                "    date_time_start           timestamp,\n" +
                "    date_time_end             timestamp";

        try (ResultSet resultSet = getResultSetFromSysRefCursor(databaseConnection, function, columnsForTable)) {
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
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseFunctions.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }


        return result.toString();
    }

    public static String getContainersOfShip(DatabaseConnection databaseConnection, String shipIMO) {
        loadFunction(databaseConnection, "docs/Sprint 3/US/US311/US311Script.sql");

        StringBuilder result = new StringBuilder();
        result.append("Ship: ").append(shipIMO).append("\n");
        String function = "getContainersOfShip('" + shipIMO + "')";
        String columnsForTable = "id_container              char(11),\n" +
                "    position_code             number(6,0),\n" +
                "    cargo_weight              integer,\n" +
                "    refrigeration_temperature number(3,1)";

        try (ResultSet resultSet = getResultSetFromSysRefCursor(databaseConnection, function, columnsForTable)) {
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
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseFunctions.class.getName()).log(Level.SEVERE, null, ex);
            databaseConnection.registerError(ex);
        }


        return result.toString();
    }
}
