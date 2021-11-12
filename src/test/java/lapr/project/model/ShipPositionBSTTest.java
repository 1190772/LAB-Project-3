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
    double[] deltaDistance = {1.243, 1.719, 17.86, 12160.0, 2469.0, 7.444, 0.0, 33250.0, 1527.0, 0.0, 0.0, 58870.0, 72.0, 85220.0, 13523.0, 138600.0};
    double[] travelledDistance = {2.0, 54.0, 93.0, 12158.0, 2555.0, 20.0, 0.0, 33256.0, 1527.0, 0.0, 0.0, 58875.0, 72.0, 85262.0, 13525.0, 138595.0, 6543.0, 0.0, 54077.0, 75787.0, 23.0, 78959.0};
    String[] totalMovementTime = {"00:57", "00:57", "09:54", "00:42", "04:30", "09:14", "00:00", "01:44", "00:07", "00:00", "00:00", "02:31", "02:03", "03:56", "00:29", "08:29", "00:14", "00:00", "02:33", "02:03", "00:03", "03:23"};
    int[] totalNumberMovements = {19, 18, 7, 25, 4, 4, 0, 13, 3, 0, 0, 24, 1, 18, 6, 1, 3, 0, 4, 3, 1, 5};
    double[] maxSOG = {0.0, 0.0, 0.1, 10.1, 1.4, 0.0, 3.5, 10.5, 7.4, 4.8, 15.9, 13.7, 1.1, 12.0, 15.0, 7.8, 15.3, 0.2, 11.7, 20.1, 0.1, 13.2};
    double[] meanSOG = {0.0, 0.0, 0.05, 9.54, 1.14, 0.0, 3.5, 10.34, 7.18, 4.8, 15.9, 13.02, 0.95, 11.73, 14.77, 7.8, 15.08, 0.2, 11.52, 19.75, 0.1, 12.48};
    double[] maxCOG = {130.3, 197.5, 173.9, -122.2, 194.5, -189.6, -61.6, 76.0, 140.1, -145.4, 66.2, 16.3, 34.1, 131.1, -91.6, 82.1, 66.0, 143.9, 120.8, -109.7, 82.8, 118.3};
    double[] meanCOG = {-55.04, -73.94, -14.15, -129.75, 156.52, -190.4, -61.6, 73.0, 139.7, -145.4, 66.2, -15.61, -54.9, 129.04, -94.46, 3.25, 65.4, 143.9, 117.48, -110.93, 58.7, 117.57};
    String toString = "ShipMovements{\n" +
            "\tShip Code='IMO6421086', \n" +
            "\tShip Name='TUSTUMENA', \n" +
            "\tStart Base Date Time=2020-12-31T23:02, \n" +
            "\tEnd Base Date Time=2020-12-31T23:59, \n" +
            "\tTotal Movement Time=00:57, \n" +
            "\tTotal Number Movements=19, \n" +
            "\tMax SOG=0.0, \n" +
            "\tMean SOG=0.0, \n" +
            "\tMax COG=130.3, \n" +
            "\tMean COG=-55.04, \n" +
            "\tDeparture Latitude=60.08477, \n" +
            "\tDeparture Longitude=-149.34998, \n" +
            "\tArrival Latitude=60.08476, \n" +
            "\tArrival Longitude=-149.34997, \n" +
            "\tTravelled Distance=2.0, \n" +
            "\tDelta Distance=1.0}";

    public ShipPositionBSTTest() {
        shipBST = App.getInstance().getCompany().getShips();
        new ImportShipsController().importShips();
        ShipMovementsController controller = new ShipMovementsController();

        shipMovementsList = new ArrayList<>();
        for (Ship s : shipBST.inOrder())
            shipMovementsList.add(controller.getAttributes(s.getIMO()));
        for (Ship s : shipBST.inOrder())
            shipBST.remove(s);

    }

    @Test
    public void deltaDistanceTest() {
        for (int i = 0; i < deltaDistance.length; i++)
            Assert.assertEquals(deltaDistance[i], shipMovementsList.get(i).getDeltaDistance(), 5);

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
            Assert.assertEquals(travelledDistance[i], shipMovementsList.get(i).getTravelledDistance(), 5);


        Assert.assertEquals(-1.0, new ShipPositionBST().travelledDistance(), 0);
        ShipPositionBST newBst = new ShipPositionBST();
        newBst.insert(new ShipPosition(null, 66, -66, 0, 0, 0, 'B', 0));
        Assert.assertEquals(0.0, newBst.travelledDistance(), 0);

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
            Assert.assertEquals(totalMovementTime[i], shipMovementsList.get(i).getTotalMovementTime().toString());
    }

    @Test
    public void totalNumberMovementsTest() {
        for (int i = 0; i < totalNumberMovements.length; i++)
            Assert.assertEquals(totalNumberMovements[i], shipMovementsList.get(i).getTotalNumberMovements());
    }

    @Test
    public void maxMeanSOGTest() {
        for (int i = 0; i < maxSOG.length; i++)
            Assert.assertEquals(maxSOG[i], shipMovementsList.get(i).getMaxSOG(), 0);

        for (int i = 0; i < meanSOG.length; i++)
            Assert.assertEquals(meanSOG[i], shipMovementsList.get(i).getMeanSOG(), 0);
    }

    @Test
    public void maxMeanCOGTest() {
        for (int i = 0; i < maxCOG.length; i++)
            Assert.assertEquals(maxCOG[i], shipMovementsList.get(i).getMaxCOG(), 0);

        for (int i = 0; i < meanCOG.length; i++)
            Assert.assertEquals(meanCOG[i], shipMovementsList.get(i).getMeanCOG(), 0);
    }

    @Test
    public void toStringTest() {
        Assert.assertEquals(toString, shipMovementsList.get(0).toString());
    }

}
