package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FindClosestPortControllerTest {
    private ArrayList<Ship> shipBST;
    private String[] namePort = {"Cristobal", "Cutuco", "Cutuco", "Vancouver", "Los Angeles", "Cutuco", "Cutuco", "Puerto Limon", "Cutuco", "Cutuco"};

    public FindClosestPortControllerTest() {
        new ImportShipsController().importShips("bships.csv");
        shipBST =(ArrayList<Ship>) App.getInstance().getCompany().getShips().inOrder();
    }

    @Test
    public void nullShipTest() {
        Assertions.assertNull(new FindClosestPortController().findClosestPortController("wrong callsign", LocalDateTime.now()));
    }

    @Test
    public void shipWithNoPositions() {
        Ship s = new Ship(null, null, null, "callsign", 0, 0, 0, 0f);
        shipBST.add(s);
        Assertions.assertNull(new FindClosestPortController().findClosestPortController("callsign", LocalDateTime.now()));
        shipBST.remove(s);
    }

    @Test
    public void findClosestPortControllerTest(){
        new ImportPortsController().importPorts("bports.csv");
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(namePort[i], new FindClosestPortController().findClosestPortController(shipBST.get(i).getCallSign(), ((ArrayList<ShipPosition>)shipBST.get(i).getPosition().inOrder()).get(((ArrayList<ShipPosition>)shipBST.get(i).getPosition().inOrder()).size()/2).getBaseDateTime()).getName());
        }
    }
}
