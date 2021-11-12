package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShipBSTTest {

    ShipBST shipBST;
    Ship ship1;
    Ship ship2;
    Ship ship3;
    Ship ship4;

    public ShipBSTTest() {
        shipBST = new ShipBST();
        ship1 = new Ship("210950000","name1","IMO9395044",10,70,"C4SQ2",70,166,25,100,(float) 9.5);
        ship2 = new Ship("212180000","name2","IMO9643544",10,70,"5BBA4",70,166,25,100,(float) 9.5);
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

}