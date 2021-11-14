package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.model.ShipBST;
import lapr.project.model.ShipMovements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ShipMovementsControllerTest {
    ShipBST shipBST;
    ArrayList<ShipMovements> travelledDistanceAsc;
    ArrayList<ShipMovements> travelledDistanceDesc;
    ArrayList<ShipMovements> deltaDistanceAsc;
    ArrayList<ShipMovements> deltaDistanceDesc;

    public ShipMovementsControllerTest() {
        shipBST = App.getInstance().getCompany().getShips();
        new ImportShipsController().importShips("sships.csv");
        ShipMovementsController controller = new ShipMovementsController();
        travelledDistanceAsc = controller.listAllShip("travelledDistanceAsc");
        travelledDistanceDesc = controller.listAllShip("travelledDistanceDesc");
        deltaDistanceAsc = controller.listAllShip("deltaDistanceAsc");
        deltaDistanceDesc = controller.listAllShip("deltaDistanceDesc");
        for (Ship s : shipBST.inOrder())
            shipBST.remove(s);
    }

    @Test
    public void travelledDistanceAscTest() {
        for (int i = 0; i<travelledDistanceAsc.size()-1;i++)
            Assertions.assertTrue(travelledDistanceAsc.get(i).getTravelledDistance()<=travelledDistanceAsc.get(i+1).getTravelledDistance());
    }

    @Test
    public void travelledDistanceDescTest() {
        for (int i = 0; i<travelledDistanceDesc.size()-1;i++)
            Assertions.assertTrue(travelledDistanceDesc.get(i).getTravelledDistance()>=travelledDistanceDesc.get(i+1).getTravelledDistance());

    }

    @Test
    public void deltaDistanceAscTest() {
        for (int i = 0; i<deltaDistanceAsc.size()-1;i++)
            Assertions.assertTrue(deltaDistanceAsc.get(i).getDeltaDistance()<=deltaDistanceAsc.get(i+1).getDeltaDistance());
    }

    @Test
    public void deltaDistanceDescTest() {
        for (int i = 0; i<deltaDistanceDesc.size()-1;i++)
            Assertions.assertTrue(deltaDistanceDesc.get(i).getDeltaDistance()>=deltaDistanceDesc.get(i+1).getDeltaDistance());
    }
}
