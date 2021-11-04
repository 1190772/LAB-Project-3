package lapr.project.model;

import lapr.project.controller.App;

import javax.naming.OperationNotSupportedException;

/**
 * Finds ships in a ship BST.
 *
 * @author David Magalhães 1201237
 */
public class SearchShip {

    /**
     * The current App instance.
     */
    private App app;

    /**
     * The current Company instance.
     */
    private Company company;

    /**
     * The current ShipBST instance.
     */
    private ShipBST ships;

    /**
     * Constructor.
     */
    public SearchShip() {
        App app = App.getInstance();
        Company company = app.getCompany();
        ShipBST ships = company.getShips();
    }

    /**
     * Decides which method should be used to find the ship based on the provided code type.
     *
     * @param codeType the type of code.
     * @param code the code.
     *
     * @return a ship if found or null otherwise.
     */
    public Ship findShip(String codeType, String code) throws OperationNotSupportedException {
        Ship ship;

        if (codeType.equals("MMSI"))
            ship = findShipByMMSI(code);
        else if (codeType.equals("IMO"))
            ship = findShipByIMO(code);
        else
            ship = findShipByCallSign(code);

        return ship;
    }

    /**
     * Searches for a ship based on the Call Sign code.
     *
     * @param code the code.
     *
     * @return a ship if found or null otherwise.
     */
    private Ship findShipByCallSign(String code) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    /**
     * Searches for a ship based on the IMO code.
     *
     * @param code the code.
     *
     * @return a ship if found or null otherwise.
     */
    private Ship findShipByIMO(String code) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    /**
     * Searches for a ship based on the MMSI code.
     *
     * @param code the code.
     *
     * @return a ship if found or null otherwise.
     */
    private Ship findShipByMMSI(String code) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
}
