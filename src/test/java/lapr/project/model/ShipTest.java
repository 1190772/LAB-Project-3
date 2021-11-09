package lapr.project.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

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
}