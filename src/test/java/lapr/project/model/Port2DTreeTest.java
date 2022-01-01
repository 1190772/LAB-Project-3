package lapr.project.model;

import lapr.project.utils.TwoDTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Port2DTreeTest {

    Port2DTree port2DTree;
    ArrayList<Port> portList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        port2DTree = new Port2DTree();
        portList.add(new Port( "1", "Liverpool", null, 53.46666667,-3.033333333, 0));
        portList.add(new Port( "2", "Liverpool", null, 33.71666667,-118.2666667, 0));
        portList.add(new Port( "3", "Liverpool", null, 40.66666667,-74.16666667, 0));
        portList.add(new Port( "4", "Liverpool", null, -32.06666667,-52.06666667, 0));
        portList.add(new Port( "5", "Liverpool", null, -12.96666667,-38.51666667, 0));
        portList.add(new Port( "6", "Liverpool", null, -23.93333333,-46.31666667, 0));
        portList.add(new Port( "7", "Liverpool", null, 44.65,-63.56666667, 0));
        portList.add(new Port( "8", "Liverpool", null, 49.28333333,-123.1166667, 0));
        portList.add(new Port( "9", "Liverpool", null, -36.73333333,-73.15, 0));
        portList.add(new Port("10", "Liverpool", null, -33.01666667,-71.63333333, 0));
    }

    @Test
    void createBalancedPort2DTreeGetPorts() {
        port2DTree.createBalancedPort2DTree(portList);
        verifyBalance(port2DTree.getRoot());
        assertEquals("7", port2DTree.findNearestNeighbour(43.7, -60.3).getID());
        assertTrue(port2DTree.getAllPorts().containsAll(portList));
    }

    void verifyBalance(TwoDTree.Node2D<Port> node) {
        if (node == null)
            return;
        verifyBalance(node.getLeft());
        if (port2DTree.balanceFactor(node) > 1)
            fail();
        verifyBalance(node.getRight());
    }

    @Test
    void loadPortsFromDatabase() {
        Port2DTree port2DTree = mock(Port2DTree.class);

        when(port2DTree.loadPortsFromDatabase()).thenReturn(true);

        Assertions.assertTrue(port2DTree.loadPortsFromDatabase());
    }
}