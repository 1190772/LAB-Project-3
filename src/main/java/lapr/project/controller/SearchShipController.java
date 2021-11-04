package lapr.project.controller;

import lapr.project.model.SearchShip;
import lapr.project.model.Ship;

import javax.naming.OperationNotSupportedException;

/**
 * Controller of US102.
 *
 * @author David Magalhães 1201237
 */
public class SearchShipController {

    /**
     * The instance of the domain class associated to this controller.
     */
    private final SearchShip searchShip;

    /**
     * Holds the chosen code type.
     */
    private String codeType;

    /**
     * Builds an instance of the Controller.
     */
    public SearchShipController() {
        searchShip = new SearchShip();
    }

    /**
     * Gets the selected code type from the UI.
     */
    public void selectCodeType(String codeType) {
        codeType = codeType;
    }

    /**
     * Sends searchShip the necessary information the find the ship
     *
     * @return Dto of the found ship
     */
    public Ship findShip(String code) throws OperationNotSupportedException {
        return searchShip.findShip(codeType, code);
    }
}
