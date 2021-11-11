package lapr.project.ui.console;

import lapr.project.controller.SearchShipController;
import lapr.project.model.Ship;
import lapr.project.model.ShipPosition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        String startDate;
        String[] start;
        String endDate;
        String[] end;
        ArrayList<ShipPosition> positions;

        System.out.print("Enter code: ");
        code = input.nextLine();

        chosenShip = controller.findShip(code);

        if (chosenShip == null)
            System.out.println("No ship was found with the provided code.");
        else {
            System.out.println(chosenShip.toString());
            System.out.println("\nSee Positional details?");
            if (input.nextLine().equalsIgnoreCase("yes")) {
                System.out.print("Enter start date (YYYY-MM-DD-HH-MM): ");
                startDate = input.nextLine();
                start = startDate.split("-");
                System.out.print("Enter end date (YYYY-MM-DD-HH-MM): ");
                endDate = input.nextLine();
                end = endDate.split("-");
                positions = controller.getPositions(LocalDateTime.of(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(start[2]), Integer.parseInt(start[3]), Integer.parseInt(start[4])),
                                                    LocalDateTime.of(Integer.parseInt(end[0]), Integer.parseInt(end[1]), Integer.parseInt(end[2]), Integer.parseInt(end[3]), Integer.parseInt(end[4])));
                for (ShipPosition position : positions) {
                    System.out.println(position.toString());
                }
            }
        }
    }
}
