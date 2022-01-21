package lapr.project.ui.console;

import lapr.project.controller.ContainerPositionsController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ContainerPositionsUI implements Runnable {

    private final ContainerPositionsController controller;

    public ContainerPositionsUI() {
        controller = new ContainerPositionsController();
    }

    @Override
    public void run() {
        int cargoManifestID;
        List<String> containerPositions;

        cargoManifestID = 12122;//Utils.readIntegerFromConsole("Cargo Manifest ID: ");
        containerPositions = controller.getContainerPositions(cargoManifestID);

        try (FileWriter fileWriter = new FileWriter("ARQCP/positions.txt", false)) {
            for (String string : containerPositions)
                fileWriter.write(string + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        containerPositions = controller.getContainerInfo(cargoManifestID);

        try (FileWriter fileWriter = new FileWriter("ARQCP/characteristics.txt", false)) {
            for (String string : containerPositions)
                fileWriter.write(string + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
