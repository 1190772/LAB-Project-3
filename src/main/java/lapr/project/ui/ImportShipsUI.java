package lapr.project.ui;

import lapr.project.controller.ImportShipsController;

import java.util.Scanner;

public class ImportShipsUI implements Runnable {

    private final ImportShipsController controller;

    public ImportShipsUI() {
        controller = new ImportShipsController();
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        String parameter;
        System.out.println("Choose parameter:");
        parameter = input.nextLine();
        if (parameter.equals("MMSI")) {
            controller.importShipsByMMSI();
        } else {
            if (parameter.equals("IMO")) {
                controller.importShipsByIMO();
            } else {
                if (parameter.equals("CallSign")) {
                    controller.importShipsByCallSign();
                }else{
                    if (parameter.equals("All")) {
                        controller.importShipsAll();
                    } else{
                        System.out.println("Parameter does not exist.");
                    }
                }
            }
        }
    }
}


