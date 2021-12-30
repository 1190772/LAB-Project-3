package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShipBSTTest {

    ShipBST shipBST;
    Ship ship1;
    Ship ship2;
    Ship ship3;
    Ship ship4;

    public ShipBSTTest() {
        shipBST = new ShipBST();
        ship1 = new Ship("210950000","name1","IMO9395044",10,70,"C4SQ2",60,166,25,100,(float) 9.5);
        ship2 = new Ship("212180000","name2","IMO9643544",10,70,"5BBA4",60,166,25,100,(float) 9.5);
        ship3 = new Ship("228339600","name3","IMO9450648",10,70,"FLSU",70,166,25,100,(float) 9.5);
        ship4 = new Ship("235092459","name4","IMO9517575",10,70,"2FMJ5",70,166,25,100,(float) 9.5);
        shipBST.insert(ship1);
        shipBST.insert(ship2);
        shipBST.insert(ship3);
        shipBST.insert(ship4);
    }

    @Test
    void findShipByIMOTest() {
        Ship expected = ship4;
        Ship found = shipBST.findShip("IMO9517575");
        assertEquals(expected, found);
    }

    @Test
    void findShipByMMSITest() {
        Ship expected = ship4;
        Ship found = shipBST.findShip("235092459");
        assertEquals(expected, found);
    }

    @Test
    void findShipByCallSignTest() {
        Ship expected = ship4;
        Ship found = shipBST.findShip("2FMJ5");
        assertEquals(expected, found);
    }

    @Test
    void topNShipsTest() {
        ArrayList<Ship>[] expected = new ArrayList[100];
        ArrayList<Ship>[] actual;

        ShipPosition pos1 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 0), 42.97875,-66.97001,12.9,13.1,355, 'B', 0);
        ShipPosition pos2 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 30), 42.97890,-66.97010,12.9,13.1,355, 'B', 0);
        ShipPosition pos3 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 16, 20), 42.97875,-66.97001,12.9,13.1,355, 'B', 0);
        ShipPosition pos4 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 16), 42.97900,-66.97020,12.9,13.1,355, 'B', 0);
        ShipPosition pos5 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 19), 42.97875,-66.97001,12.9,13.1,355, 'B', 0);
        ShipPosition pos6 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 18, 3), 42.97895,-66.970020,12.9,13.1,355, 'B', 0);
        ShipPosition pos7 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 16, 20), 42.97875,-66.97001,12.9,13.1,355, 'B', 0);
        ShipPosition pos8 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 16), 42.97880,-66.97010,12.9,13.1,355, 'B', 0);

        ship1.addPosition(pos1);
        ship1.addPosition(pos2);
        ship2.addPosition(pos3);
        ship2.addPosition(pos4);
        ship3.addPosition(pos5);
        ship3.addPosition(pos6);
        ship4.addPosition(pos7);
        ship4.addPosition(pos8);

        expected[60] = new ArrayList<>();
        expected[60].add(ship2);
        expected[70] = new ArrayList<>();
        expected[70].add(ship3);

        actual = shipBST.topNShips(1, LocalDateTime.of(2020, 12, 31, 16, 0), LocalDateTime.of(2020, 12, 31, 19, 18));

        assertSame(expected[60].get(0), actual[60].get(0));
        assertSame(expected[70].get(0), actual[70].get(0));
    }

    @Test
    void ShipPairsTest() {
        ArrayList<Ship[]> expected = new ArrayList<>();
        ArrayList<Ship[]> actual;

        ShipPosition pos1 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 0), 42.97800,-66.96500,12.9,13.1,355, 'B', 0);
        ShipPosition pos2 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 30), 60.97900,-65.97050,12.9,13.1,355, 'B', 0);

        ShipPosition pos3 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 16, 20), 30.97870,-30.97000,12.9,13.1,355, 'B', 0);
        ShipPosition pos4 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 16), 50.97880,-50.97000,12.9,13.1,355, 'B', 0);

        ShipPosition pos5 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 19), 42.97875,-66.97001,12.9,13.1,355, 'B', 0);
        ShipPosition pos6 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 18, 3), 60.97900,-65.97020,12.9,13.1,355, 'B', 0);

        ShipPosition pos7 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 16, 20), 30.97875,-30.97001,12.9,13.1,355, 'B', 0);
        ShipPosition pos8 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 16), 50.97890,-50.97010,12.9,13.1,355, 'B', 0);

        ship1.addPosition(pos1);
        ship1.addPosition(pos2);
        ship2.addPosition(pos3);
        ship2.addPosition(pos4);
        ship3.addPosition(pos5);
        ship3.addPosition(pos6);
        ship4.addPosition(pos7);
        ship4.addPosition(pos8);

        expected.add(new Ship[2]);
        expected.get(0)[0] = ship2;
        expected.get(0)[1] = ship4;

        expected.add(new Ship[2]);
        expected.get(1)[0] = ship1;
        expected.get(1)[1] = ship3;

        actual = (ArrayList<Ship[]>) shipBST.getShipPairs();

        assertSame(expected.get(0)[0], actual.get(0)[0]);
        assertSame(expected.get(0)[1], actual.get(0)[1]);
        assertSame(expected.get(1)[0], actual.get(1)[0]);
        assertSame(expected.get(1)[1], actual.get(1)[1]);
    }

    @Test
    void loadShipsFromDatabase() {
        ShipBST shipBST = mock(ShipBST.class);

        when(shipBST.loadShipsFromDatabase()).thenReturn(true);

        Assertions.assertTrue(shipBST.loadShipsFromDatabase());
    }

}