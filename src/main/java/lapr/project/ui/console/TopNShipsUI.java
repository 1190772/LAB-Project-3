package lapr.project.ui.console;

import lapr.project.controller.TopNShipsController;
import lapr.project.model.Ship;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI of US106
 *
 * @author José Silva 1190772
 */
public class TopNShipsUI implements Runnable {

    /**
     * The Controller associated to this UI.
     */
    private final TopNShipsController controller;

    /**
     * Builds an instance of the UI.
     */
    public TopNShipsUI() {
        controller = new TopNShipsController();
    }

    /**
     * Runs the US.
     */
    public void run() {
        int n;
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        String startDate;
        String[] start;
        String endDate;
        String[] end;
        ArrayList<Ship>[] topNLists;
        Scanner in = new Scanner(System.in);

        System.out.println("Choose the number of ships:");
        n = in.nextInt();
        in.nextLine();
        System.out.print("Enter start date (YYYY-MM-DD-HH-MM): ");
        startDate = in.nextLine();
        start = startDate.split("-");
        System.out.print("Enter end date (YYYY-MM-DD-HH-MM): ");
        endDate = in.nextLine();
        end = endDate.split("-");
        startDateTime = LocalDateTime.of(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(start[2]), Integer.parseInt(start[3]), Integer.parseInt(start[4]));
        endDateTime = LocalDateTime.of(Integer.parseInt(end[0]), Integer.parseInt(end[1]), Integer.parseInt(end[2]), Integer.parseInt(end[3]), Integer.parseInt(end[4]));
        topNLists = controller.topNShips(n, startDateTime, endDateTime);
        LocalDateTime.of(Integer.parseInt(end[0]), Integer.parseInt(end[1]), Integer.parseInt(end[2]), Integer.parseInt(end[3]), Integer.parseInt(end[4]));

        for (ArrayList<Ship> ships : topNLists) {
            if (ships != null) {
                for (Ship ship : ships) {
                    System.out.println("MMSI: " + ship.getMMSI() + " | VesselType: " + ship.getVesselType() + " | Kilometers Travelled: " + ship.getPosition().travelledDistance(startDateTime, endDateTime) + " | MeanSOG: " + ship.getPosition().meanSOG(startDateTime, endDateTime));
                }
            }
        }
    }
}
