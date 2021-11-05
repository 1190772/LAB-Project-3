package lapr.project.utils;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class Reader {

    private Scanner sc;
    private static final int VALOR_NA = -1;


    public Reader() {
        try {
            sc = new Scanner(new File("sships.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Reader(String nameFile) {
        try {
            sc = new Scanner(new File(nameFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        while (sc.hasNextLine()) {
            String[] infoLinha;
            String linha = sc.nextLine();
            if (linha.trim().isEmpty()) continue;
            infoLinha = fixLineData(linha.split(","));

        }
    }

    public String[] fixLineData(String[] original) {
        for (int i = 0; i < original.length; i++) {
            original[i] = original[i].replaceAll("\"", "");
            if (original[i].equals("NA")) {
                original[i] = String.valueOf(VALOR_NA);
            }
        }
        return original;
    }
}

