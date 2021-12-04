package lapr.project.model;

import lapr.project.utils.TwoDTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Port2DTreeTest {

    Port2DTree port2DTree;
    ArrayList<Port> portList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        port2DTree = new Port2DTree();
        portList.add(new Port( 1, "Liverpool", "Europe", "United Kingdom", 53.46666667,-3.033333333));
        portList.add(new Port( 2, "Liverpool", "Europe", "United Kingdom", 33.71666667,-118.2666667));
        portList.add(new Port( 3, "Liverpool", "Europe", "United Kingdom", 40.66666667,-74.16666667));
        portList.add(new Port( 4, "Liverpool", "Europe", "United Kingdom", -32.06666667,-52.06666667));
        portList.add(new Port( 5, "Liverpool", "Europe", "United Kingdom", -12.96666667,-38.51666667));
        portList.add(new Port( 6, "Liverpool", "Europe", "United Kingdom", -23.93333333,-46.31666667));
        portList.add(new Port( 7, "Liverpool", "Europe", "United Kingdom", 44.65,-63.56666667));
        portList.add(new Port( 8, "Liverpool", "Europe", "United Kingdom", 49.28333333,-123.1166667));
        portList.add(new Port( 9, "Liverpool", "Europe", "United Kingdom", -36.73333333,-73.15));
        portList.add(new Port(10, "Liverpool", "Europe", "United Kingdom", -33.01666667,-71.63333333));
    }

    @Test
    void createdBalancedPort2DTree() {
        port2DTree.createdBalancedPort2DTree(portList);
        verifyBalance(port2DTree.getRoot());
        assertEquals(7, port2DTree.findNearestNeighbour(43.7, -60.3).getID());
    }

    void verifyBalance(TwoDTree.Node2D<Port> node) {
        if (node == null)
            return;
        verifyBalance(node.getLeft());
        if (port2DTree.balanceFactor(node) > 1)
            fail();
        verifyBalance(node.getRight());
    }
}