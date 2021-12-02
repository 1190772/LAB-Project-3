package lapr.project.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a Ship.
 *
 * @author David Magalhães 1201237
 */
public class Ship implements Comparable<Ship> {

    /**
     * The MMSI code of the Ship.
     */
    private final String MMSI;

    /**
     * The name of the Ship.
     */
    private final String name;

    /**
     * The IMO code of the Ship.
     */
    private final String IMO;

    /**
     * The number of energy generators of the Ship.
     */
    private int numberEnergyGenerators;

    /**
     * The power output of the Ship's energy generators.
     */
    private int generatorPowerOutput;

    /**
     * The Call Sign code of the Ship.
     */
    private final String callSign;

    /**
     * The vessel type of the Ship.
     */
    private final int vesselType;

    /**
     * The length of the Ship, in meters.
     */
    private final int length;

    /**
     * The width of the Ship, in meters.
     */
    private final int width;

    /**
     * The load capacity of the Ship, in m3.
     */
    private int capacity;

    /**
     * The draft of the Ship, in meters. Varies with ship load and water density.
     */
    private float draft;

    /**
     * History of positions.
     */
    private ShipPositionBST positions;

    /**
     * Builds an instance of Ship receiving all the information.
     *
     * @param MMSI the MMSI code of the ship.
     * @param name the name of the Ship.
     * @param IMO the IMO code of the Ship.
     * @param numberEnergyGenerators the number of energy generators of the Ship.
     * @param generatorPowerOutput the power output of the Ship's energy generators.
     * @param callSign the Call Sign code of the Ship.
     * @param vesselType the vessel type of the Ship.
     * @param length the length of the Ship, in meters.
     * @param width the width of the Ship, in meters.
     * @param capacity the load capacity of the Ship, in m3.
     * @param draft the draft of the Ship, in meters.
     */
    public Ship(String MMSI, String name, String IMO, int numberEnergyGenerators,
              int generatorPowerOutput, String callSign, int vesselType, int length, int width, int capacity, float draft) {

        this.MMSI = MMSI;
        this.name = name;
        this.IMO = IMO;
        this.numberEnergyGenerators = numberEnergyGenerators;
        this.generatorPowerOutput = generatorPowerOutput;
        this.callSign = callSign;
        this.vesselType = vesselType;
        this.length = length;
        this.width = width;
        this.capacity = capacity;
        this.draft = draft;
        positions = new ShipPositionBST();
    }

    /**
     * Builds an instance of Ship receiving MMSI, name, IMO, CallSign, vesselType, length, width, draft and cargo.
     * @param MMSI the MMSI code of the ship.
     * @param name the name of the Ship.
     * @param IMO the IMO code of the Ship.
     * @param callSign the Call Sign code of the Ship.
     * @param vesselType the vessel type of the Ship.
     * @param length the length of the Ship, in meters.
     * @param width the width of the Ship, in meters.
     * @param draft the draft of the Ship, in meters.
     */
    public Ship(String MMSI, String name, String IMO, String callSign, int vesselType, int length, int width, float draft) {
        this.MMSI = MMSI;
        this.name = name;
        this.IMO = IMO;
        this.callSign = callSign;
        this.vesselType = vesselType;
        this.length = length;
        this.width = width;
        this.draft = draft;
        positions = new ShipPositionBST();
        capacity = 1;
    }

    /**
     * Returns the MMSI code of the ship.
     *
     * @return MMSI code of the ship
     */
    public String getMMSI() { return MMSI; }

    /**
     * Returns the name of the Ship.
     *
     * @return name of the Ship
     */
    public String getName() { return name; }

    /**
     * Returns the IMO code of the Ship.
     *
     * @return IMO code of the Ship
     */
    public String getIMO() { return IMO; }

    /**
     * Returns the number of energy generators of the Ship.
     *
     * @return number of energy generators of the Ship
     */
    public int getNumberEnergyGenerators() { return numberEnergyGenerators; }

    /**
     * Returns the power output of the Ship's energy generators.
     *
     * @return power output of the Ship's energy generators
     */
    public int getGeneratorPowerOutput() { return generatorPowerOutput; }

    /**
     * Returns the Call Sign code of the Ship.
     *
     * @return Call Sign code of the Ship
     */
    public String getCallSign() { return callSign; }

    /**
     * Returns the vessel type of the Ship.
     *
     * @return vessel type of the Ship
     */
    public int getVesselType() { return vesselType; }

    /**
     * Returns the length of the Ship, in meters.
     *
     * @return length of the Ship, in meters
     */
    public int getLength() { return length; }

    /**
     * Returns the width of the Ship, in meters.
     *
     * @return width of the Ship, in meters
     */
    public int getWidth() { return width; }

    /**
     * Returns the load capacity of the Ship, in m3.
     *
     * @return load capacity of the Ship, in m3
     */
    public int getCapacity() { return capacity; }

    /**
     * Returns the draft of the Ship, in meters.
     *
     * @return draft of the Ship, in meters
     */
    public float getDraft() { return draft; }

    /**
     * Adds a positions to the ship's list of positions.
     *
     * @param position a position.
     */
    public void addPosition(ShipPosition position) {
        positions.insert(position);
    }

    /**
     * Returns positions of ship given a date interval.
     *
     * @param startDate start of interval.
     * @param endDate end of interval.
     *
     * @return negative, 0, or positive, depending on whose IMO code comes first.
     */
    public ArrayList<ShipPosition> getPositions(LocalDateTime startDate, LocalDateTime endDate) {
        ArrayList<ShipPosition> res = new ArrayList<>();
        Iterator<ShipPosition> positions;
        ShipPosition position;
        boolean end = false;

        positions = this.positions.inOrder().iterator();
        position = positions.next();

        while (position.getBaseDateTime().compareTo(startDate) < 0)
            position = positions.next();

        while (position.getBaseDateTime().compareTo(endDate) <= 0 && !end) {
            res.add(position);
            if (positions.hasNext())
                position = positions.next();
            else
                end = true;
        }

        return res;
    }
    
    public ShipPositionBST getPosition(){
        return  positions;
    } 

    /**
     * Compares two ships based on IMO code.
     *
     * @param o another ship.
     *
     * @return negative, 0, or positive, depending on whose IMO code comes first.
     */
    @Override
    public int compareTo(Ship o) { return this.getIMO().compareTo(o.getIMO()); }

    /**
     * Returns a textual representation of the ship.
     *
     * @return textual representation of the ship.
     */
    @Override
    public String toString() {
        return "Ship{" +
                "MMSI code = '" + MMSI + '\'' +
                ", Name = '" + name + '\'' +
                ", IMO code = '" + IMO + '\'' +
                ", Number of energy generators = " + numberEnergyGenerators +
                ", Generator power output = " + generatorPowerOutput +
                ", Call Sign code = '" + callSign + '\'' +
                ", Vessel type = '" + vesselType + '\'' +
                ", Length = " + length + 'm' +
                ", Width = " + width + 'm' +
                ", Capacity = " + capacity + "m3" +
                ", Draft = " + draft +
                '}';
    }
    
}
