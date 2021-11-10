package lapr.project.controller;

import lapr.project.utils.BST;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportShipsController {

    BufferedReader in;
    String word;

    public BST<Integer> ship_BST_mmsi;
    public BST<String> ship_BST_imo;
    public BST<String> ship_BST_callSign;
    public BST<String> ship_BST_all;

    public ImportShipsController() {

    }
    //TODO


    public void importShipsAll() {
        ship_BST_all = new BST<>();
        try {
            in = new BufferedReader((new FileReader("sships.csv")));
            in.readLine();
            while ((word = in.readLine()) != null) {
                ship_BST_all.insert(word);
            }
            System.out.println(ship_BST_all);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importShipsByMMSI() {
        ship_BST_mmsi = new BST<>();
        try {
            in = new BufferedReader(new FileReader("sships.csv"));
            String[] aux_array;
            in.readLine();
            while ((word = in.readLine()) != null) {
                aux_array = word.split(",");
                ship_BST_mmsi.insert(Integer.parseInt(aux_array[0]));
            }
            System.out.println(ship_BST_mmsi);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importShipsByIMO() {
        ship_BST_imo = new BST<>();
        try {
            in = new BufferedReader(new FileReader("sships.csv"));
            String[] aux_array;
            in.readLine();
            while ((word = in.readLine()) != null) {
                aux_array = word.split(",");
                ship_BST_imo.insert((aux_array[8]));
            }
            System.out.println(ship_BST_imo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importShipsByCallSign() {
        ship_BST_callSign = new BST<>();
        try {
            in = new BufferedReader(new FileReader("sships.csv"));
            String[] aux_array;
            in.readLine();
            while ((word = in.readLine()) != null) {
                aux_array = word.split(",");
                ship_BST_callSign.insert((aux_array[9]));
            }
            System.out.println(ship_BST_callSign);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
