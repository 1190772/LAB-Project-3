package lapr.project.model.shared;

import lapr.project.utils.DatabaseConnection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class SQL {
    private final DatabaseConnection databaseConnection;
    private final String url = "jdbc:oracle:thin:@//vsgate-s1.dei.isep.ipp.pt:10601/xepdb1";

    public SQL(String user, String password) {
    databaseConnection = new DatabaseConnection(url, user, password);
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

