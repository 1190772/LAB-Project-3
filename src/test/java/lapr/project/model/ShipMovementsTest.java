package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ShipMovementsTest {
    @Test
    public void getsTest(){

        ShipMovementsAllDetails shipMovements = new ShipMovementsAllDetails("123", "345", new ShipPosition(LocalDateTime.of(1, 1, 1, 1, 1), 1, 2, 0, 0, 0, 'A', 0), new ShipPosition(LocalDateTime.of(2, 2, 2, 2, 2), 3, 4, 0, 0, 0, 'A', 0), null, 0, 0,0,0,0,0,0);
        Assertions.assertEquals("123", shipMovements.getShipCode());
        Assertions.assertEquals(LocalDateTime.of(1, 1, 1, 1, 1), shipMovements.getStartBaseDateTime());
        Assertions.assertEquals(LocalDateTime.of(2, 2, 2, 2, 2), shipMovements.getEndBaseDateTime());
        Assertions.assertEquals(1, shipMovements.getDepartureLatitude());
        Assertions.assertEquals(2, shipMovements.getDepartureLongitude());
        Assertions.assertEquals(3, shipMovements.getArrivalLatitude());
        Assertions.assertEquals(4, shipMovements.getArrivalLongitude());
    }

    @Test
    public void compareToTest() {
        ShipMovements s1 = new ShipMovements("123", 0, 0, 1);
        ShipMovements s2 = new ShipMovements("123", 0, 0, 0);
        ShipMovements s3 = new ShipMovements("123", 0, 0, 2);
        Assertions.assertEquals(0, s1.compareTo(s1));
        Assertions.assertEquals(1, s1.compareTo(s2));
        Assertions.assertEquals(-1, s1.compareTo(s3));

    }

    @Test
    public void toStringTest() {
        ShipMovements s1 = new ShipMovements("123", 0, 0, 1);
        Assertions.assertEquals("ShipMovements{\n" +
                "\tShip Code='123', \n" +
                "\tTotal Number Movements=0, \n" +
                "\tTravelled Distance=0.0, \n" +
                "\tDelta Distance=1.0}", s1.toString());
    }
}
