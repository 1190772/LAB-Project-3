package lapr.project.utils;

import lapr.project.model.shared.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    final String url = "jdbc:oracle:thin:@//vsgate-s1.dei.isep.ipp.pt:10601/xepdb1";

    @Test
    public void getConnectionNull() {

        DatabaseConnection connection = new DatabaseConnection(url, Constants.DATABASE_TEST_USERNAME, "123");

        Exception e = Assertions.assertThrows(RuntimeException.class, () -> {
            Connection c = connection.getConnection();
        });
        String expected = "Connection does not exit";
        Assertions.assertEquals(expected, e.getMessage());
    }

    @Test
    public void errorTest() {
        DatabaseConnection connection = new DatabaseConnection(url, Constants.DATABASE_TEST_USERNAME, Constants.DATABASE_TEST_THE_OTHER_ONE);
        SQLException error1 = new SQLException("Error1");
        SQLException error2 = new SQLException("Error2");
        connection.registerError(error1);
        connection.registerError(error2);
        Assertions.assertEquals(error2, connection.getLastError());
        Assertions.assertNull(connection.getLastError());
    }
}
