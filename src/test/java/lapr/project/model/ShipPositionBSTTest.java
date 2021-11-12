package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.controller.ImportShipsController;
import lapr.project.controller.ShipMovementsController;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ShipPositionBSTTest {
    ShipBST shipBST;
    ArrayList<ShipMovements> shipMovementsList;
    double[] deltaDistance = {1.243, 1.719, 17.86, 12160, 2469, 7.444, 0, 33250, 1527, 0, 0, 58870, 72, 85220, 13523, 138600};

    public ShipPositionBSTTest(){
        shipBST = App.getInstance().getCompany().getShips();
        new ImportShipsController().importShips();
        ShipMovementsController controller = new ShipMovementsController();

        shipMovementsList = new ArrayList<>();
        for (Ship s:shipBST.inOrder())
                shipMovementsList.add(controller.getAttributes(s.getIMO()));


    }

    @Test
    public void deltaDistanceTest() {
        for (int i=0; i<deltaDistance.length; i++)
            Assert.assertEquals(deltaDistance[i], shipMovementsList.get(i).getDeltaDistance(), 5);

    //Confirmation http://www.movable-type.co.uk/scripts/latlong.html
        for (Ship ship: shipBST.inOrder()){
            System.out.print(ship.getName());
            ArrayList<ShipPosition> list = (ArrayList<ShipPosition>) ship.getPosition().inOrder();
            System.out.println("\n"+list.get(0).getLatitude()+"\n"+list.get(0).getLongitude());
            System.out.println(list.get(list.size()-1).getLatitude()+"\n"+list.get(list.size()-1).getLongitude());
            System.out.println();
        }
        System.out.println();
        for (ShipMovements s : shipMovementsList)
            System.out.println(s.getShipName()+" "+s.getDeltaDistance());
    }

    
}
