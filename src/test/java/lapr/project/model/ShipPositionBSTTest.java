package lapr.project.model;

import lapr.project.controller.ImportShipsController;
import lapr.project.controller.ShipMovementsController;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ShipPositionBSTTest {
    ShipBST shipBST;
    ArrayList<ShipMovements> shipMovementsList;
    double[] deltaDistance = {1.243, 1.719, 17.86, 12160};

    public ShipPositionBSTTest(){
        shipBST = new ShipBST();
        new ImportShipsController(shipBST).importShips();
        ShipMovementsController controller = new ShipMovementsController();

        for (Ship ship: shipBST.inOrder()){
            System.out.println(ship.getName());
            ArrayList<ShipPosition> list = (ArrayList<ShipPosition>) ship.getPosition().inOrder();
            System.out.println(list.get(0).getLatitude()+" "+list.get(0).getLongitude());
            System.out.println(list.get(list.size()-1).getLatitude()+" "+list.get(list.size()-1).getLongitude());
            System.out.println();
        }
            for (Ship ship: shipBST.inOrder())
                shipMovementsList.add(controller.getAttributes(ship.getIMO()));


    }

    @Test
    public void deltaDistanceTest() {
        for (int i=0; i<deltaDistance.length; i++)
            Assert.assertEquals(deltaDistance[i], shipMovementsList.get(i).getDeltaDistance());
    }
}
