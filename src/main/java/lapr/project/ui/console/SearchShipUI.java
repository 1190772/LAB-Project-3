package lapr.project.ui.console;

import lapr.project.controller.SearchShipController;
import lapr.project.model.Ship;

import java.util.Scanner;

/**
 * UI of US102.
 *
 * @author David Magalhães 1201237
 */
public class SearchShipUI implements Runnable {

    /**
     * The Controller associated to this UI.
     */
    private final SearchShipController controller;

    /**
     * Builds an instance of the UI.
     */
    public SearchShipUI() {
        controller = new SearchShipController();
    }

    /**
     * Runs the US.
     */
    @Override
    public void run() {
        Scanner input = new Scanner(System.in);
        String code;
        Ship chosenShip;

        System.out.print("Enter code: ");
        code = input.nextLine();

        chosenShip = controller.findShip(code);

        if (chosenShip == null)
            System.out.println("No ship was found with the provided code.");
        else {
            System.out.println(chosenShip.toString());
        }
    }
}
