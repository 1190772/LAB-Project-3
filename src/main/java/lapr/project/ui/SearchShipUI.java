package lapr.project.ui;

import lapr.project.controller.SearchShipController;
import lapr.project.model.Ship;

import javax.naming.OperationNotSupportedException;
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
        String[] codeTypes = new String[3];
        codeTypes[0] = "MMSI";
        codeTypes[1] = "IMO";
        codeTypes[2] = "Call Sign";
        int opcao;
        Scanner input = new Scanner(System.in);
        String code;
        Ship chosenShip = null;

        do {
            opcao = input.nextInt();
        } while (opcao < 0 || opcao > 3);
        input.nextLine();

        controller.selectCodeType(codeTypes[opcao]);

        System.out.print("Enter code: ");
        code = input.nextLine();

    try {
        chosenShip = controller.findShip(code);
    } catch (OperationNotSupportedException e) {
        e.printStackTrace();
    }

    if (chosenShip != null)
        System.out.println(chosenShip.toString());
    else
        System.out.println("No ship was found with the provided code.");
    }
}