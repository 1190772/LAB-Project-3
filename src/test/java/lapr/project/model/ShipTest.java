package lapr.project.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    final Ship ship;
    final String mmsi = "210950000";
    final String name = "name";
    final String imo = "IMO9395044";
    final int numberOfEnergyGenerators = 10;
    final int generatorPowerOutput = 70;
    final String callSign = "C4SQ2";
    final int vesselType = 70;
    final int length = 166;
    final int width = 25;
    final int capacity = 100;
    final float draft = (float) 9.5;

    public ShipTest() {
        ship = new Ship(mmsi,name,imo,numberOfEnergyGenerators,generatorPowerOutput,callSign,vesselType,length,width,capacity,draft);
    }

    @Test
    void getMMSI() {
        Assert.assertEquals(mmsi, ship.getMMSI());
    }

    @Test
    void getName() {
        Assert.assertEquals(name, ship.getName());
    }

    @Test
    void getIMO() {
        Assert.assertEquals(imo, ship.getIMO());
    }

    @Test
    void getNumberEnergyGenerators() {
        Assert.assertEquals(numberOfEnergyGenerators, ship.getNumberEnergyGenerators());
    }

    @Test
    void getGeneratorPowerOutput() {
        Assert.assertEquals(generatorPowerOutput, ship.getGeneratorPowerOutput());
    }

    @Test
    void getCallSign() {
        Assert.assertEquals(callSign, ship.getCallSign());
    }

    @Test
    void getVesselType() {
        Assert.assertEquals(vesselType, ship.getVesselType());
    }

    @Test
    void getLength() {
        Assert.assertEquals(length, ship.getLength());
    }

    @Test
    void getWidth() {
        Assert.assertEquals(width, ship.getWidth());
    }

    @Test
    void getCapacity() {
        Assert.assertEquals(capacity, ship.getCapacity());
    }

    @Test
    void getDraft() {
        Assert.assertEquals(draft, ship.getDraft(), 0.5);
    }

    @Test
    public void toStringTest() {
        String expected = "Ship{MMSI code = '210950000', Name = 'name', IMO code = 'IMO9395044', Number of energy generators = 10, Generator power output = 70, Call Sign code = 'C4SQ2', Vessel type = '70', Length = 166m, Width = 25m, Capacity = 100m3, Draft = 9.5}";
        assertEquals(expected, ship.toString());
    }

    @Test
    public void getPositionsTest() {
        ArrayList<ShipPosition> expected = new ArrayList<>();
        ArrayList<ShipPosition> actual;

        ShipPosition pos1 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 19), 42.97875,-66.97001,12.9,13.1,355, 'B');
        ShipPosition pos2 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 3), 42.97875,-66.97001,12.9,13.1,355, 'B');
        ShipPosition pos3 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 16, 20), 42.97875,-66.97001,12.9,13.1,355, 'B');
        ShipPosition pos4 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 16), 42.97875,-66.97001,12.9,13.1,355, 'B');

        ship.addPosition(pos1);
        ship.addPosition(pos2);
        ship.addPosition(pos3);
        ship.addPosition(pos4);

        expected.add(pos2);
        expected.add(pos4);

        actual = ship.getPositions(LocalDateTime.of(2020, 12, 31, 17, 0), LocalDateTime.of(2020, 12, 31, 17, 18));

        assertEquals(expected, actual);
    }
}