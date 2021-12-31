package lapr.project.model.shared;

import lapr.project.utils.DatabaseConnection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Scanner;

public class SQL {
    private final DatabaseConnection databaseConnection;
    private static final String URL = "jdbc:oracle:thin:@//vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";

    public SQL(String user, String password) {
    databaseConnection = new DatabaseConnection(URL, user, password);
    }

    public boolean executeLine(String sqlLine) {
        try {
            return databaseConnection.getConnection().createStatement().execute(sqlLine);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean scriptInTextFile(String filename) {
        try (Scanner in = new Scanner((new FileReader(filename)))) {
            StringBuilder sqlLine = new StringBuilder();
            while (in.hasNextLine()) {
                sqlLine.append(in.nextLine());
                if (sqlLine.toString().endsWith(";")) {
                    executeLine(sqlLine.toString().replace(";", ""));
                    sqlLine = new StringBuilder();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }
}

