package lapr.project.controller;

import lapr.project.model.ShipBST;
import lapr.project.model.ShipMovements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShipMovementsControllerTest {
    ShipBST shipBST;
    List<ShipMovements> asc;
    List<ShipMovements> desc;

    public ShipMovementsControllerTest() {
        shipBST = App.getInstance().getCompany().getShips();
        new ImportShipsController().importShips("sships.csv");
        ShipMovementsController controller = new ShipMovementsController();
        asc = controller.listAllShip(true);
        desc = controller.listAllShip(false);

    }

    @Test
    public void ascTest() {
        for (int i = 0; i<asc.size()/2;i++){
            Assertions.assertTrue(asc.get(i).getTravelledDistance()<=asc.get(i+1).getTravelledDistance());
            Assertions.assertTrue(asc.get(i).getDeltaDistance()<=asc.get(i+1).getDeltaDistance());
        }
    }

    @Test
    public void descTest() {
        for (int i = 0; i<desc.size()/2;i++){
            Assertions.assertTrue(desc.get(i).getTravelledDistance()>=desc.get(i+1).getTravelledDistance());
            Assertions.assertTrue(desc.get(i).getDeltaDistance()>=desc.get(i+1).getDeltaDistance());
        }
    }
}
