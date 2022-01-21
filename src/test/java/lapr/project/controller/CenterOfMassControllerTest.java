package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import oracle.ucp.util.Pair;

class CenterOfMassControllerTest {

    private final CenterOfMassController controller;

    CenterOfMassControllerTest() { controller = new CenterOfMassController(); }

    @Test
    void unladenCenterOfMassTest1() {
        double expectedX = 16;
        double expectedY = 126;
        Pair<Double, Double> actual;
        double[] m = {13054, 1202, 3244};
        double[] x = {16.16, 16.16, 16.16};
        double[] y = {144.78, 22.21, 89.71};

        actual = controller.unladenCenterOfMass(m, x, y);

        Assertions.assertEquals(expectedX, actual.get1st(), 1);
        Assertions.assertEquals(expectedY, actual.get2nd(), 1);
    }

    @Test
    void unladenCenterOfMassTest2() {
        double expectedX = 26;
        double expectedY = 173;
        Pair<Double, Double> actual;
        double[] m = {31095, 1908, 6997};
        double[] x = {25.63, 25.63, 25.63};
        double[] y = {183, 16.84, 168.41};

        actual = controller.unladenCenterOfMass(m, x, y);

        Assertions.assertEquals(expectedX, actual.get1st(), 1);
        Assertions.assertEquals(expectedY, actual.get2nd(), 1);
    }

    @Test
    void unladenCenterOfMassTest3() {
        double expectedX = 7.5;
        double expectedY = 43;
        Pair<Double, Double> actual;
        double[] m = {819, 76, 120, 52};
        double[] x = {7.5, 7.5, 7.5, 7.5};
        double[] y = {40, 6.15, 73.85, 76.8};

        actual = controller.unladenCenterOfMass(m, x, y);

        Assertions.assertEquals(expectedX, actual.get1st(), 1);
        Assertions.assertEquals(expectedY, actual.get2nd(), 1);
    }
}