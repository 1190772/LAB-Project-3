package lapr.project.data;

import lapr.project.model.Ship;
import lapr.project.model.ShipPosition;
import lapr.project.utils.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShipStoreTest {

    private final ShipStore shipStore;
    private final DatabaseConnection connection;
    private final Ship ship1;
    private final Ship ship2;
    final ShipPosition shipPosition;
    final ShipPosition shipPositionInvalid;
    final int year = 2020;
    final int month = 12;
    final int day = 24;
    final int hour = 13;
    final int minute = 30;
    final double latitude = 42.97875;
    final double longitude = -66.97001;
    final double sog = 12.9;
    final double cog = 13.1;
    final int heading = 355;
    final char transceiverClass = 'B';
    final int cargo = 0;
    final LocalDateTime baseDateTime = LocalDateTime.of(year,month,day,hour,minute);

    ShipStoreTest() {
        shipStore = mock(ShipStore.class);
        connection = mock(DatabaseConnection.class);
        ship1 = new Ship("210950000","name1","IMO9395044",10,70,"C4SQ2",60,166,25,100,(float) 9.5);
        ship2 = new Ship("212180000","name2","IMO9643544",10,70,"5BBA4",60,166,25,100,(float) 9.5);
        shipPosition = new ShipPosition(baseDateTime, latitude,longitude, sog, cog, heading, transceiverClass, cargo);
        shipPositionInvalid = new ShipPosition(baseDateTime, latitude, longitude, sog, cog, 600, transceiverClass, cargo);
        ship1.addPosition(shipPosition);
        ship2.addPosition(shipPosition);
    }

    @Test
    void shipStoreTest() {

        try {
            when(shipStore.save(connection, ship1)).thenReturn(true);
            boolean result = shipStore.save(connection, ship1);
            assertTrue(result);
            Logger.getLogger(ShipStoreTest.class.getName()).log(Level.INFO, "Added Ship!");

            when(shipStore.save(connection, ship1)).thenReturn(true);
            result = shipStore.save(connection, ship1);
            assertTrue(result);
            Logger.getLogger(ShipStoreTest.class.getName()).log(Level.INFO, "Attempted to add the same ship again!");

            when(shipStore.save(connection, ship2)).thenReturn(true);
            result = shipStore.save(connection, ship2);
            assertTrue(result);
            Logger.getLogger(ShipStoreTest.class.getName()).log(Level.INFO, "Added a different ship!");

            when(shipStore.delete(connection, ship1)).thenReturn(true);
            result = shipStore.delete(connection, ship1);
            assertTrue(result);
            Logger.getLogger(ShipStoreTest.class.getName()).log(Level.INFO, "Deleted the first Ship!");

            when(shipStore.delete(connection, ship1)).thenReturn(true);
            result = shipStore.delete(connection, ship1);
            assertTrue(result);
            Logger.getLogger(ShipStoreTest.class.getName()).log(Level.INFO, "Deleted the other Ship!");

            ship2.addPosition(shipPositionInvalid);
            when(shipStore.save(connection, ship2)).thenReturn(false);
            result = shipStore.save(connection, ship2);
            assertFalse(result);
            Logger.getLogger(ShipStoreTest.class.getName()).log(Level.INFO, "Tried adding a ship with an invalid Position!");

        } catch (Exception ex) {
                Logger.getLogger(ShipStoreTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}