package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(mmsi, ship.getMMSI());
    }

    @Test
    void getName() {
        assertEquals(name, ship.getName());
    }

    @Test
    void getIMO() {
        assertEquals(imo, ship.getIMO());
    }

    @Test
    void getNumberEnergyGenerators() {
        assertEquals(numberOfEnergyGenerators, ship.getNumberEnergyGenerators());
    }

    @Test
    void getGeneratorPowerOutput() {
        assertEquals(generatorPowerOutput, ship.getGeneratorPowerOutput());
    }

    @Test
    void getCallSign() {
        assertEquals(callSign, ship.getCallSign());
    }

    @Test
    void getVesselType() {
        assertEquals(vesselType, ship.getVesselType());
    }

    @Test
    void getLength() {
        assertEquals(length, ship.getLength());
    }

    @Test
    void getWidth() {
        assertEquals(width, ship.getWidth());
    }

    @Test
    void getCapacity() {
        assertEquals(capacity, ship.getCapacity());
    }

    @Test
    void getDraft() {
        assertEquals(draft, ship.getDraft(), 0.5);
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

        ShipPosition pos1 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 19), 42.97875,-66.97001,12.9,13.1,355, 'B', 0);
        ShipPosition pos2 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 3), 42.97875,-66.97001,12.9,13.1,355, 'B', 0);
        ShipPosition pos3 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 16, 20), 42.97875,-66.97001,12.9,13.1,355, 'B', 0);
        ShipPosition pos4 = new ShipPosition(LocalDateTime.of(2020, 12, 31, 17, 16), 42.97875,-66.97001,12.9,13.1,355, 'B', 0);

        ship.addPosition(pos1);
        ship.addPosition(pos2);
        ship.addPosition(pos3);
        ship.addPosition(pos4);

        expected.add(pos2);
        expected.add(pos4);

        actual =(ArrayList<ShipPosition>) ship.getPositions(LocalDateTime.of(2020, 12, 31, 17, 0), LocalDateTime.of(2020, 12, 31, 17, 18));

        assertEquals(expected, actual);
    }
}