package lapr.project.controller;

import lapr.project.model.shared.Utils;
import oracle.ucp.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class PositionContainersController {
    public static class Coordinates {
        private double x, y, z;

        public Coordinates(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getZ() {
            return z;
        }

        public void setX(double x) {
            this.x = x;
        }

        public void setY(double y) {
            this.y = y;
        }

        public void setZ(double z) {
            this.z = z;
        }

        @Override
        public String toString() {
            return "Coordinates{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }
    }


    public String positionContainers(List<Pair<String, Double>> containers, Coordinates max, Coordinates centerMassOriginal, double shipMass) {
        Coordinates centerMassActual = new Coordinates(centerMassOriginal.getX(), centerMassOriginal.getY(), centerMassOriginal.getZ());
        double massActual = shipMass;

        List<Pair<String, Double>> containers1 = new ArrayList<>(containers);

        containers1.sort((o1, o2) -> {
            if (o1.get2nd().equals(o2.get2nd()))
                return 0;
            return (int) (o1.get2nd() - o2.get2nd());
        });


        String[][][] positions = new String[(int) max.getX()][(int) max.getY()][(int) max.getZ()];
        StringBuilder result = new StringBuilder();
        for (int k = 0; k < positions[0][0].length; k++) {
            for (int i = positions.length / 2; i >= 0; i--) {
                for (int j = positions[0].length / 2; j >= 0; j--) {
                    if (containers1.isEmpty()) {
                        i = 0;
                        j = 0;
                        k = positions[0][0].length;
                    } else {
                        Pair<String, Double> container = containers1.remove(0);
                        positions[i][j][k] = container.get1st();
                        centerMassActual = Utils.calculateCenterMass(centerMassActual, new Coordinates(i, j, k), massActual, container.get2nd());
                        massActual += container.get2nd();

                        if (containers1.isEmpty()) {
                            i = 0;
                            j = 0;
                            k = positions[0][0].length;
                        } else {
                            container = containers1.remove(0);
                            positions[positions.length - i-1][positions[0].length - j-1][k] = container.get1st();
                            centerMassActual = Utils.calculateCenterMass(centerMassActual, new Coordinates(i, j, k), massActual, container.get2nd());
                            massActual += container.get2nd();
                        }
                    }

                }
            }
        }

        result.append("Original Center of Mass:").append(centerMassOriginal).append("\n");
        result.append("Final Center of Mass:").append(centerMassActual).append("\n");
        centerMassActual.setX(centerMassActual.getX()*6.1- centerMassOriginal.getX()*6.1);
        centerMassActual.setY(centerMassActual.getY()*2.44- centerMassOriginal.getY()*2.44);
        centerMassActual.setZ(centerMassActual.getZ()*2.44- centerMassOriginal.getZ()*2.44);
        result.append("Difference in positioning of the ceneter of mass in meters:").append(centerMassActual).append("\n");
        result.append("Position of containers:\n");
        for (int k = 0; k < positions[0][0].length; k++) {
            result.append("Level ").append(k).append(":\n");
            for (int i = 0; i < positions.length; i++) {
                for (int j = 0; j < positions[0].length; j++) {

                    result.append(positions[i][j][k]).append(" ");
                }
                result.append("\n");
            }
            result.append("\n");
        }
        result.append("\n");
        result.append("Original mass:").append(shipMass).append(" kg\n");
        result.append("Final mass:").append(massActual).append(" kg\n");

        return result.toString();
    }


}
