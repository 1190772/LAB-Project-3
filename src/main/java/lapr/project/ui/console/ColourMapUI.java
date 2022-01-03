package lapr.project.ui.console;

import lapr.project.controller.App;
import lapr.project.controller.ColourMapController;

import java.util.HashMap;
import java.util.Map;

public class ColourMapUI implements Runnable {

    private final ColourMapController controller;

    public ColourMapUI() {
        controller = new ColourMapController();
    }

    private<V> Map<V, Colours> remap(Map<V, Integer> map){
        Map<V, Colours> remap = new HashMap<>();
        for (V key : map.keySet()) {
            remap.put(key,Colours.ordinal(map.get(key)));
        }
        return remap;
    }

    @Override
    public void run() {
        System.out.println(remap(controller.colourMap(App.getInstance().getCompany().getFreightNetwork())));
    }
}
