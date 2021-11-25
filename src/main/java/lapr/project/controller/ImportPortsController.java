package lapr.project.controller;

import lapr.project.model.Port;
import lapr.project.utils.TwoDTree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Controller responsible for importing ports.
 *
 * @author David Magalhães 1201237
 */
public class ImportPortsController {

    /**
     * Port 2D-tree.
     */
    private final TwoDTree<Port> ports;

    /**
     * Builds an instance of the Controller.
     */
    public ImportPortsController() {
        ports = new TwoDTree<>();
    }

    /**
     * Imports ports from a text file.
     *
     * @param fileName the file to import from.
     */
    public void importPorts(String fileName) {
        String[] parameters;

        try (Scanner in = new Scanner((new FileReader(fileName)))) {
            in.nextLine();
            while (in.hasNextLine()) {
                parameters = in.nextLine().split(",");
                String continent = parameters[0];
                String country = parameters[1];
                int id = Integer.parseInt(parameters[2]);
                String name = parameters[3];
                double latitude = Double.parseDouble(parameters[4]);
                double longitude = Double.parseDouble(parameters[5]);
                ports.insert(new Port(id, name, continent, country, latitude, longitude), latitude, longitude);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
