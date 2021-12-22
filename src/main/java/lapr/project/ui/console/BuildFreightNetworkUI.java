package lapr.project.ui.console;

import lapr.project.controller.BuildFreightNetworkController;

public class BuildFreightNetworkUI implements Runnable {

    private BuildFreightNetworkController controller;

    public BuildFreightNetworkUI() {
        controller = new BuildFreightNetworkController();
    }

    @Override
    public void run() {
        controller.BuildFreightNetwork();
    }
}
