package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.controller.ImportShipsController;
import lapr.project.controller.ShipMovementsController;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipPositionBSTTest {
    ShipBST shipBST;
    ArrayList<ShipMovements> shipMovementsList;
    double[] deltaDistance = {1.243, 1.719, 17.86, 12160.0, 2469.0, 7.444, 0.0, 33250.0, 1527.0, 0.0, 0.0, 58870.0, 72.0, 85220.0, 13523.0, 138600.0};
    double[] travelledDistance = {2.0, 54.0, 93.0, 12158.0, 2555.0, 20.0, 0.0, 33256.0, 1527.0, 0.0, 0.0, 58875.0, 72.0, 85262.0, 13525.0, 138595.0, 6543.0, 0.0, 54077.0, 75787.0, 23.0, 78959.0};
    String[] totalMovementTime = {"00:57", "00:57", "09:54", "00:42", "04:30", "09:14", "00:00", "01:44", "00:07", "00:00", "00:00", "02:31", "02:03", "03:56", "00:29", "08:29", "00:14", "00:00", "02:33", "02:03", "00:03", "03:23"};

    public ShipPositionBSTTest() {
        shipBST = App.getInstance().getCompany().getShips();
        new ImportShipsController().importShips();
        ShipMovementsController controller = new ShipMovementsController();

        shipMovementsList = new ArrayList<>();
        for (Ship s : shipBST.inOrder())
            shipMovementsList.add(controller.getAttributes(s.getIMO()));

    }

    @Test
    public void deltaDistanceTest() {
        for (int i = 0; i < deltaDistance.length; i++)
            assertEquals(deltaDistance[i], shipMovementsList.get(i).getDeltaDistance(), 5);

        //Confirmation http://www.movable-type.co.uk/scripts/latlong.html
        for (Ship ship : shipBST.inOrder()) {
            System.out.print(ship.getName());
            ArrayList<ShipPosition> list = (ArrayList<ShipPosition>) ship.getPosition().inOrder();
            System.out.println("\n" + list.get(0).getLatitude() + "\n" + list.get(0).getLongitude());
            System.out.println(list.get(list.size() - 1).getLatitude() + "\n" + list.get(list.size() - 1).getLongitude());
            System.out.println();
        }
        System.out.println();
        for (ShipMovements s : shipMovementsList)
            System.out.println(s.getShipName() + " " + s.getDeltaDistance());
    }

    @Test
    public void travelledDistanceTest() {
        for (int i = 0; i < travelledDistance.length; i++)
            assertEquals(travelledDistance[i], shipMovementsList.get(i).getTravelledDistance(), 5);

        //Confirmation http://www.movable-type.co.uk/scripts/latlong.html
        for (Ship ship : shipBST.inOrder()) {
            System.out.println(ship.getName());
            for (ShipPosition shipPosition : ship.getPosition().inOrder())
                System.out.println(shipPosition.getLatitude() + "\n" + shipPosition.getLongitude());
            System.out.println();
        }
        System.out.println();
        for (ShipMovements s : shipMovementsList)
            System.out.println(s.getShipName() + " " + s.getDeltaDistance());
    }

    @Test
    public void totalMovementTimeTest() {
        for (int i = 0; i < totalMovementTime.length; i++)
            assertEquals(totalMovementTime[i], shipMovementsList.get(i).getTotalMovementTime().toString());
    }


}
