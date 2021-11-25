package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ShipPositionTest {

    final ShipPosition shipPosition;
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


    public ShipPositionTest() {
        shipPosition = new ShipPosition(baseDateTime, latitude,longitude,sog,cog,heading,transceiverClass, cargo);
    }

    @Test
    void getBaseDateTime() {
        Assertions.assertEquals(baseDateTime, shipPosition.getBaseDateTime());
    }

    @Test
    void getLatitude() {
    Assertions.assertEquals(latitude,shipPosition.getLatitude());
    }

    @Test
    void getLongitude() {
    Assertions.assertEquals(longitude, shipPosition.getLongitude());
    }

    @Test
    void getSOG(){
        Assertions.assertEquals(sog, shipPosition.getSOG());
    }
    @Test
    void getCOG(){
        Assertions.assertEquals(cog, shipPosition.getCOG());
    }
    @Test
    void getHeading(){
        Assertions.assertEquals(heading, shipPosition.getHeading());
    }
    @Test
    void getTransceiverClass(){
        Assertions.assertEquals(transceiverClass, shipPosition.getTransceiverClass());
    }
    @Test
    void getCargo(){
        Assertions.assertEquals(cargo, shipPosition.getCargo());
    }

    @Test
    void testToString() {
        String expected="Ship Position{Base date and time = 2020-12-24T13:30, Latitude = 42.97875, Longitude = -66.97001, SOG = 12.9, COG = 13.1, Heading = 355.0, Transceiver Class = B, Cargo = 355.0}";
        Assertions.assertEquals(expected, shipPosition.toString());
    }

    @Test
    void compareTo() {
        Assertions.assertEquals(0, baseDateTime.compareTo(shipPosition.getBaseDateTime()));
    }
}