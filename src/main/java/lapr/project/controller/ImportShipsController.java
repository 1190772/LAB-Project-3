package lapr.project.controller;

import lapr.project.utils.AVL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportShipsController {

    BufferedReader in;
    String word;

    public AVL<Integer> ship_AVL_mmsi;
    public AVL<Integer> ship_AVL_imo;
    public AVL<Integer> ship_AVL_callSign;

    public ImportShipsController() {

    }
    //TODO

    public void importShipsByMMSI() {
        ship_AVL_mmsi = new AVL<>();
        try {
            in = new BufferedReader(new FileReader("sships.csv"));
            String[] aux_array;
            in.readLine();
            while ((word = in.readLine()) != null) {
                aux_array = word.split(",");
                ship_AVL_mmsi.insert(Integer.parseInt(aux_array[0]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importShipsByIMO() {
        ship_AVL_imo = new AVL<>();
        try {
            in = new BufferedReader(new FileReader("sships.csv"));
            String[] aux_array;
            in.readLine();
            while ((word = in.readLine()) != null) {
                aux_array = word.split(",");
                ship_AVL_imo.insert(Integer.parseInt(aux_array[8]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importShipsByCallSign() {
        ship_AVL_callSign = new AVL<>();
        try {
            in = new BufferedReader(new FileReader("sships.csv"));
            String[] aux_array;
            in.readLine();
            while ((word = in.readLine()) != null) {
                aux_array = word.split(",");
                ship_AVL_callSign.insert(Integer.parseInt(aux_array[9]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
