package lapr.project.ui.console;

import lapr.project.controller.CenterOfMassController;
import oracle.ucp.util.Pair;

public class UnladenCenterOfMassUI implements Runnable {

    private final CenterOfMassController controller;

    public UnladenCenterOfMassUI() {
        controller = new CenterOfMassController();
    }

    @Override
    public void run() {
        double[] m;
        double[] x;
        double[] y;
        int number;
        Pair<Double, Double> centerOfMass;

        number = Utils.readIntegerFromConsole("Number of masses:");
        m = new double[number];
        x = new double[number];
        y = new double[number];

        for (int i = 0; i < number; i++) {
            m[i] = Utils.readDoubleFromConsole("Mass of m"+(i+1)+":");
            x[i] = Utils.readDoubleFromConsole("X position of m"+(i+1)+":");
            y[i] = Utils.readDoubleFromConsole("Y position of m"+(i+1)+":");
        }
        System.out.println();

        centerOfMass = controller.unladenCenterOfMass(m, x, y);

        System.out.println("Center of Mass X position = " + centerOfMass.get1st() + " m.");
        System.out.println("Center of Mass Y position = " + centerOfMass.get2nd() + " m.");
    }
}
