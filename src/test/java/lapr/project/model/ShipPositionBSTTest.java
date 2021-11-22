package lapr.project.model;

import lapr.project.controller.App;
import lapr.project.controller.ImportShipsController;
import lapr.project.controller.ShipMovementsController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class ShipPositionBSTTest {
    ShipBST shipBST;
    ArrayList<ShipMovementsAllDetails> shipMovementsList;
    double[] deltaDistance = {1.0, 4.0, 17.0, 2469.0, 33252.0, 1527.0, 0.0, 57225.0, 0.0, 85222.0, 0.0, 6542.0, 0.0, 54033.0, 23.0, 78953.0};
    double[] travelledDistance = {2.0, 11.0, 17.0, 2555.0, 33256.0, 1527.0, 0.0, 57231.0, 0.0, 85262.0, 0.0, 6543.0, 0.0, 54077.0, 23.0, 78959.0};
    String[] totalMovementTime = {"00:57", "00:33", "01:03", "04:30", "01:44", "00:07", "00:00", "02:27", "00:00", "03:56", "00:00", "00:14", "00:00", "02:33", "00:03", "03:23"};
    int[] totalNumberMovements = {5, 4, 2, 4, 13, 3, 0, 15, 0, 18, 0, 3, 0, 4, 1, 5};
    double[] maxSOG = {0.0, 0.0, 0.1, 1.4, 10.5, 7.4, 15.9, 13.6, 1.1, 12.0, 7.8, 15.3, 0.2, 11.7, 0.1, 13.2};
    double[] meanSOG = {0.0, 0.0, 0.03, 1.14, 10.34, 7.17, 15.9, 13.0, 1.1, 11.73, 7.8, 15.08, 0.2, 11.52, 0.1, 12.48};
    double[] maxCOG = {130.3, 197.5, 173.9, 194.5, 76.0, 140.1, 66.2, 16.3, 34.1, 131.1, 82.1, 66.0, 143.9, 120.8, 82.8, 118.3};
    double[] meanCOG = {103.93, 194.8, 123.07, 156.52, 73.0, 139.7, 66.2, 5.62, 34.1, 129.04, 82.1, 65.4, 143.9, 117.48, 58.7, 117.57};
    String toString = "ShipMovements{\n" +
            "\tShip Code='IMO6421086', \n" +
            "\tShip Name='TUSTUMENA', \n" +
            "\tStart Base Date Time=2020-12-31T23:02, \n" +
            "\tEnd Base Date Time=2020-12-31T23:59, \n" +
            "\tTotal Movement Time=00:57, \n" +
            "\tTotal Number Movements=5, \n" +
            "\tMax SOG=0.0, \n" +
            "\tMean SOG=0.0, \n" +
            "\tMax COG=130.3, \n" +
            "\tMean COG=103.93, \n" +
            "\tDeparture Latitude=60.08477, \n" +
            "\tDeparture Longitude=-149.34998, \n" +
            "\tArrival Latitude=60.08476, \n" +
            "\tArrival Longitude=-149.34997, \n" +
            "\tTravelled Distance=2.0, \n" +
            "\tDelta Distance=1.0}";

    public ShipPositionBSTTest() {
        shipBST = App.getInstance().getCompany().getShips();
        new ImportShipsController().importShips("sships.csv");
        ShipMovementsController controller = new ShipMovementsController();

        shipMovementsList = new ArrayList<>();
        for (Ship s : shipBST.inOrder()) {
            ShipMovementsAllDetails smad = controller.getAttributes(s.getIMO());
            if (smad != null)
                shipMovementsList.add(smad);
        }
        for (Ship s : shipBST.inOrder())
            shipBST.remove(s);
        Assertions.assertTrue(((ArrayList<Ship>) shipBST.inOrder()).isEmpty());
    }

    @Test
    public void deltaDistanceTest() {
        for (int i = 0; i < deltaDistance.length; i++)
            Assertions.assertEquals(deltaDistance[i], shipMovementsList.get(i).getDeltaDistance(), 5);

        //Confirmation http://www.movable-type.co.uk/scripts/latlong.html
        for (Ship ship : shipBST.inOrder()) {
            System.out.print(ship.getName());
            ArrayList<ShipPosition> list = (ArrayList<ShipPosition>) ship.getPosition().inOrder();
            System.out.println("\n" + list.get(0).getLatitude() + "\n" + list.get(0).getLongitude());
            System.out.println(list.get(list.size() - 1).getLatitude() + "\n" + list.get(list.size() - 1).getLongitude());
            System.out.println();
        }
        System.out.println();
        for (ShipMovementsAllDetails s : shipMovementsList)
            System.out.println(s.getShipName() + " " + s.getDeltaDistance());
    }

    @Test
    public void travelledDistanceTest() {
        for (int i = 0; i < travelledDistance.length; i++)
            Assertions.assertEquals(travelledDistance[i], shipMovementsList.get(i).getTravelledDistance(), 5);


        Assertions.assertEquals(-1.0, new ShipPositionBST().travelledDistance(), 0);
        ShipPositionBST newBst = new ShipPositionBST();
        newBst.insert(new ShipPosition(null, 66, -66, 0, 0, 0, 'B', 0));
        Assertions.assertEquals(0.0, newBst.travelledDistance(), 0);

        //Confirmation http://www.movable-type.co.uk/scripts/latlong.html
        for (Ship ship : shipBST.inOrder()) {
            System.out.println(ship.getName());
            for (ShipPosition shipPosition : ship.getPosition().inOrder())
                System.out.println(shipPosition.getLatitude() + "\n" + shipPosition.getLongitude());
            System.out.println();
        }
        System.out.println();
        for (ShipMovementsAllDetails s : shipMovementsList)
            System.out.println(s.getShipName() + " " + s.getDeltaDistance());
    }

    @Test
    public void totalMovementTimeTest() {
        for (ShipMovementsAllDetails s : shipMovementsList)
            System.out.print("\"" + s.getTotalMovementTime() + "\", ");
    }

    @Test
    public void totalNumberMovementsTest() {
        for (ShipMovementsAllDetails s : shipMovementsList)
            System.out.print(s.getTotalNumberMovements() + ", ");
    }

    @Test
    public void maxMeanSOGTest() {
        for (int i = 0; i < maxSOG.length; i++)
            Assertions.assertEquals(maxSOG[i], shipMovementsList.get(i).getMaxSOG(), 0);

        for (int i = 0; i < meanSOG.length; i++)
            Assertions.assertEquals(meanSOG[i], shipMovementsList.get(i).getMeanSOG(), 0);
    }

    @Test
    public void maxMeanCOGTest() {
        for (int i = 0; i < maxCOG.length; i++)
            Assertions.assertEquals(maxCOG[i], shipMovementsList.get(i).getMaxCOG(), 0);

        for (int i = 0; i < meanCOG.length; i++)
            Assertions.assertEquals(meanCOG[i], shipMovementsList.get(i).getMeanCOG(), 0);
    }

    @Test
    public void toStringTest() {
        Assertions.assertEquals(toString, shipMovementsList.get(0).toString());
    }

}
