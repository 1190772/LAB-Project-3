package lapr.project.ui.console;

import lapr.project.controller.ImportShipsController;

import java.util.Scanner;

public class ImportShipsUI implements Runnable {

    private final ImportShipsController controller;

    public ImportShipsUI() {
        controller = new ImportShipsController();
    }

    public void run() {
        controller.importShips();


    }
}