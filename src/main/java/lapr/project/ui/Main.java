package lapr.project.ui;

import lapr.project.model.CalculatorExample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        CalculatorExample calculatorExample = new CalculatorExample();
        int value = calculatorExample.sum(3, 5);

        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.log(Level.INFO, String.valueOf(value));
        }
        System.out.println("Enter one of the following commands:");
        System.out.println("1 - Import Ships");
        System.out.println("2 - ");
        System.out.println("3 - ");
        Scanner scanchoice = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter \"1\", \"2\" or \"3\"");
        int choiceentry = scanchoice.nextInt();

        while (choiceentry != 3) {

            if (choiceentry < 1 || choiceentry > 3) {

                System.out.println("Enter \"1\", \"2\", \"3\" or \"4\"");
                choiceentry = scanchoice.nextInt();

            }

            else if(choiceentry == 1) {
                ImportShipsUI ui = new ImportShipsUI();
                ui.run();

            }
            else if(choiceentry == 2) {
                //..something else
            }
            else if(choiceentry == 3) {
                //...exit program
            }

        }

    }


}

