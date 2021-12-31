package lapr.project.ui.console;

import lapr.project.controller.BuildFreightNetworkController;

public class BuildFreightNetworkUI implements Runnable {

    private final BuildFreightNetworkController controller;

    public BuildFreightNetworkUI() {
        controller = new BuildFreightNetworkController();
    }

    @Override
    public void run() {
        controller.buildFreightNetwork();
    }
}
