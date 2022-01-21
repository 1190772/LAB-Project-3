package lapr.project.ui.console;

import lapr.project.controller.MostEfficientCircuitController;
import lapr.project.model.FreightNetworkVertex;
import oracle.ucp.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * UI of US403.
 *
 * @author David Magalhães 1201237
 */
public class MostEfficientCircuitUI implements Runnable{

    /**
     * The Controller associated to this UI.
     */
    private final MostEfficientCircuitController controller;

    /**
     * Builds an instance of the UI.
     */
    public MostEfficientCircuitUI() {
    controller = new MostEfficientCircuitController();
    }

    private void copyFile(String path1, String path2) {
        Scanner fileReader;
        FileWriter fileWriter;

        try {
            fileReader = new Scanner(new File(path1));
            fileWriter = new FileWriter(path2);

            while (fileReader.hasNextLine()) {
                fileWriter.write(fileReader.nextLine()+"\n");
            }
            fileReader.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendFile(String path1, String path2) {
        Scanner fileReader;
        FileWriter fileWriter;

        try {
            fileReader = new Scanner(new File(path1));
            fileWriter = new FileWriter(path2, true);

            while (fileReader.hasNextLine()) {
                fileWriter.write(fileReader.nextLine()+"\n");
            }
            fileReader.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the US.
     */
    @Override
    public void run() {
        String sourceLocation;
        Pair<LinkedList<FreightNetworkVertex>, Integer> result;
        LinkedList<FreightNetworkVertex> circuit;
        int totalDistance;
        FileWriter fileWriter;

        sourceLocation = Utils.readLineFromConsole("Source Location: ");
        result = controller.getMostEfficientCircuit(sourceLocation);
        circuit = result.get1st();
        totalDistance = result.get2nd()/1000;

        try {
            fileWriter = new FileWriter("Mapa_Circuito/mapa2.json", false);
            fileWriter.write("var PATH = [\n");
            for (FreightNetworkVertex place : circuit) {
                System.out.println(place.getName() + " (" + place.getLatitude() + "," + place.getLongitude() + ')');
                fileWriter.write("[\"" + place.getName() + "\"," + place.getLatitude() + "," + place.getLongitude() + "],\n");
            }
            fileWriter.write("[\"Distância Total\","+totalDistance+",0]\n");
            fileWriter.write("]\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Amount of Places: " + circuit.size());
        System.out.println("Total Distance: " + totalDistance + " km");

        // Contrução do programa javascript de visualização do circuito
        copyFile("Mapa_Circuito/mapa1.htm", "Mapa_Circuito/mapa.html");
        appendFile("Mapa_Circuito/mapa2.json", "Mapa_Circuito/mapa.html");
        appendFile("Mapa_Circuito/mapa3.htm", "Mapa_Circuito/mapa.html");
    }
}
