package lapr.project.ui.console;

import lapr.project.controller.ShipPairsController;
import lapr.project.model.Ship;

import java.util.ArrayList;

/**
 * UI of US102.
 *
 * @author David Magalhães 1201237
 */
public class ShipPairsUI implements Runnable {

    /**
     * The Controller associated to this UI.
     */
    private ShipPairsController controller;

    /**
     * Builds an instance of the UI.
     */
    public ShipPairsUI() {
controller = new ShipPairsController();
}

    @Override
    public void run() {
        ArrayList<Ship[]> pairs;

        controller.refreshShips();
        pairs = controller.getShipPairs();

        for (Ship[] pair : pairs) {
            System.out.println(pair[0].toString());
            System.out.println(pair[1].toString());
            System.out.println();
        }
    }
}
