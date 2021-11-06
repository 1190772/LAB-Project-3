package lapr.project.ui;

import lapr.project.controller.ImportShipsController;

import java.util.Scanner;

public class ImportShipsUI implements Runnable{

    private final ImportShipsController controller;

    public ImportShipsUI(){
        controller = new ImportShipsController();
    }

    public void run(){
        Scanner input = new Scanner(System.in);
    }
}
