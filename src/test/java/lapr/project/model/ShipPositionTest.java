package lapr.project.model;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.BorderUIResource;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ShipPositionTest {

    private static final char B = 8;

    final ShipPosition shipPosition;
    final LocalDateTime baseDateTime = null;
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
    final char transceiverClass = B;



    public ShipPositionTest() {
        shipPosition = new ShipPosition(year,month,day,hour,minute,latitude,longitude,sog,cog,heading,transceiverClass);
    }



    @Test
    void getBaseDateTime() {
        assertEquals(baseDateTime, shipPosition.getBaseDateTime());
    }

    @Test
    void getLatitude() {
        assertEquals(latitude,shipPosition.getLatitude());
    }

    @Test
    void getLongitude() {
        assertEquals(longitude, shipPosition.getLongitude());
    }

//    @Test
//    void testToString() {
//        String expected="ShipPosition{year=2020, month=12, day=24, hour=13, minute=30, latitude=42.97875, longitude=-66.97001, sog=12.9, cog=13.1, heading=355.0, transceiverClass=|b}";
//        Assert.assertEquals(expected, shipPosition.toString());
//    }
//
//    @Test
//    void compareTo() {
//        Assert.assertTrue(baseDateTime.compareTo(shipPosition.getBaseDateTime()) == 0);
//    }
}