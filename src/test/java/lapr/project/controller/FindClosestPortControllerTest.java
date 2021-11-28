package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class FindClosestPortControllerTest {
    private ArrayList<Ship> shipBST;
    private String[] namePort = {"Cristobal", "Cutuco", "Cutuco", "Vancouver", "Los Angeles", "Cutuco", "Cutuco", "Puerto Limon", "Cutuco", "Cutuco"};

    public FindClosestPortControllerTest() {
        new ImportShipsController().importShips("bships.csv");
        new ImportPortsController().importPorts("bports.csv");
        shipBST =(ArrayList<Ship>) App.getInstance().getCompany().getShips().inOrder();
    }

    @Test
    public void findClosestPortControllerTest(){
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(namePort[i], new FindClosestPortController().findClosestPortController(shipBST.get(i).getCallSign(), ((ArrayList<ShipPosition>)shipBST.get(i).getPosition().inOrder()).get(((ArrayList<ShipPosition>)shipBST.get(i).getPosition().inOrder()).size()/2).getBaseDateTime()).getName());
        }
    }
}
