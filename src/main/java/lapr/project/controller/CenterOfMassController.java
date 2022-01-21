package lapr.project.controller;

import oracle.ucp.util.Pair;

public class CenterOfMassController {

    public Pair<Double, Double> unladenCenterOfMass(double[] m, double[] x, double[] y) {
        double xPos = 0;
        double yPos = 0;
        double totalMass = 0;
        int i;

        // Determine total mass
        for (i = 0; i < m.length; i++) {
            totalMass += m[i];
        }

        // Determine x position
        for (i = 0; i < x.length; i++) {
            xPos += x[i]*m[i];
        }
        xPos = xPos / totalMass;

        // Round x position to 2 decimal places
        xPos = Math.round(xPos*100);
        xPos = xPos/100;

        // Determine y position
        for (i = 0; i < y.length; i++) {
            yPos += y[i]*m[i];
        }
        yPos = yPos / totalMass;

        // Round y position to 2 decimal places
        yPos = Math.round(yPos*100);
        yPos = yPos/100;

        return new Pair<>(xPos, yPos);
    }
}
