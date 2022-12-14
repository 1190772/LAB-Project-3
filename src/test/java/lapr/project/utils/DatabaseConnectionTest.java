package lapr.project.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    private final DatabaseConnection connection = mock(DatabaseConnection.class);

    @Test
    public void getConnectionNull() {


        when(connection.getConnection()).thenThrow(new RuntimeException("Connection does not exit"));

        Exception e = Assertions.assertThrows(RuntimeException.class, () -> {
            Connection c = connection.getConnection();
        });
        String expected = "Connection does not exit";
        Assertions.assertEquals(expected, e.getMessage());
    }

    @Test
    public void errorTest() {
        SQLException error1 = new SQLException("Error1");
        SQLException error2 = new SQLException("Error2");
        when(connection.getLastError()).thenReturn(error2);
        connection.registerError(error1);
        connection.registerError(error2);
        Assertions.assertEquals(error2, connection.getLastError());
        when(connection.getLastError()).thenReturn(null);
        Assertions.assertNull(connection.getLastError());
    }
}
