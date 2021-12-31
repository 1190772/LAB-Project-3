package lapr.project.ui.console;

import lapr.project.controller.ContainerPositionsController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContainerPositionsUI implements Runnable {

    private final ContainerPositionsController controller;

    public ContainerPositionsUI() {
        controller = new ContainerPositionsController();
    }

    @Override
    public void run() {
        int cargoManifestID;
        ArrayList<String> containerPositions;
        FileWriter fileWriter;

        cargoManifestID = Utils.readIntegerFromConsole("Cargo Manifest ID: ");
        containerPositions = controller.getContainerPositions(cargoManifestID);

        try {
            fileWriter = new FileWriter("positions.txt", false);
            for (String string : containerPositions)
                fileWriter.write(string + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
