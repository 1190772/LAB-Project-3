package lapr.project.utils.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author DEI-ISEP
 */
public class MatrixGraphTest {
    final ArrayList<String> co = new ArrayList<>(Arrays.asList("A", "A", "B", "C", "C", "D", "E", "E", "F", "G", "H", "I", "J", "K"));
    final ArrayList<String> cd = new ArrayList<>(Arrays.asList("B", "C", "D", "D", "E", "A", "D", "E", "F", "G", "H", "I", "J", "K"));
    final ArrayList<Integer> cw = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));

    final ArrayList<String> ov = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"));
    MatrixGraph<String, Integer> instance = null;

    @BeforeEach
    public void initializeGraph() {
        instance = new MatrixGraph<>(true);
    }

    /**
     * Test of copy constructor of class Graph.
     */
    @Test
    public void testCopyConstructor() {

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Graph<String, Integer> g = new MatrixGraph<>(instance);
        assertEquals(instance.getClass(), g.getClass(), "The graphs should be from the same class");
        assertEquals(instance, g, "The graphs should have equal contents");
    }

    /**
     * Test of isDirected method, of class Graph.
     */
    @Test
    public void testIsDirected() {
        assertTrue(instance.isDirected(), "result should be true");
        instance = new MatrixGraph<>(false);
        assertFalse(instance.isDirected(), "result should be false");
    }

    /**
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {
        assertEquals(0, instance.numVertices(), "result should be zero");
        instance.addVertex("A");
        assertEquals(1, instance.numVertices(), "result should be one");
        instance.addVertex("B");
        assertEquals(2, instance.numVertices(), "result should be two");
        instance.removeVertex("A");
        assertEquals(1, instance.numVertices(), "result should be one");
        instance.removeVertex("B");
        assertEquals(0, instance.numVertices(), "result should be zero");
    }

    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {
        assertEquals(0, instance.vertices().size(), "vertices should be empty");
        assertNull(instance.adjVertices("A"));

        instance.addVertex("A");
        instance.addVertex("B");

        assertEquals("A", instance.vertex(0));
        assertNull(instance.vertex(-1));
        assertNull(instance.vertex(3));
        assertEquals("B", instance.vertex(s -> s.equals("B")));
        assertNull(instance.vertex(s -> s.equals("C")));

        Collection<String> cs = instance.vertices();
        assertEquals(2, cs.size(), "Must have 2 vertices");
        cs.removeAll(Arrays.asList("A", "B"));
        assertEquals(0, cs.size(), "Vertices should be A and B");

        instance.removeVertex("A");

        cs = instance.vertices();
        assertEquals(1, cs.size(), "Must have 1 vertice1");
        cs.removeAll(Arrays.asList("B"));
        assertEquals(0, cs.size(), "Vertice should be B");

        instance.removeVertex("B");
        cs = instance.vertices();
        assertEquals(0, cs.size(), "Must not have any vertice");
    }

    /**
     * Test of validVertex method, of class Graph.
     */
    @Test
    public void testValidVertex() {
        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (String v : co)
            assertTrue(instance.validVertex(v), "vertices should exist");


        assertFalse(instance.validVertex("Z"), "vertice should not exist");
    }

    /**
     * Test of key method, of class Graph.
     */
    @Test
    public void testKey() {
        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < ov.size(); i++)
            assertEquals(i, instance.key(ov.get(i)), "vertices should exist");

        assertEquals(-1, instance.key("Z"), "vertice should not exist");
    }

    /**
     * Test of testAdjVertices method, of class Graph.
     */
    @Test
    public void testAdjVertices() {
        assertNull(instance.adjVertices("A"));
        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection<String> cs = instance.adjVertices("A");
        assertEquals(2, cs.size(), "Num adjacents should be 2");
        cs.removeIf(s -> s.equals("B") || s.equals("C"));
        assertEquals(0, cs.size(), "Adjacents should be B and C");

        cs = instance.adjVertices("B");
        assertEquals(1, cs.size(), "Num adjacents should be 1");
        cs.removeIf(s -> s.equals("D"));
        assertEquals(0, cs.size(), "Adjacents should be S");

        cs = instance.adjVertices("E");
        assertEquals(2, cs.size(), "Num adjacents should be 2");
        cs.removeIf(s -> s.equals("D") || s.equals("E"));
        assertEquals(0, cs.size(), "Adjacents should be D and E");

    }

    /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testNumEdges() {
        assertEquals(0, instance.numEdges(), "result should be zero");

        instance.addEdge("A", "B", 1);
        assertEquals(1, instance.numEdges(), "result should be one");

        instance.addEdge("A", "C", 2);
        assertEquals(2, instance.numEdges(), "result should be two");

        instance.removeEdge("A", "B");
        assertEquals(1, instance.numEdges(), "result should be one");

        instance.removeEdge("A", "C");
        assertEquals(0, instance.numEdges(), "result should be zero");
    }

    /**
     * Test of edges method, of class Graph.
     */
    @Test
    public void testEdges() {
        assertEquals(0, instance.edges().size(), "edges should be empty");
        assertNull(instance.edge(0, 0));

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));
        instance.removeVertex("F");
        instance.removeVertex("G");
        instance.removeVertex("H");
        instance.removeVertex("I");
        instance.removeVertex("J");
        instance.removeVertex("K");
        assertNull(instance.edge(0, co.size()));
        assertNull(instance.edge(co.size(), 0));
        assertThrows(RuntimeException.class, () -> {
            instance.addEdge(null, "A", 0);
        });
        assertThrows(RuntimeException.class, () -> {
            instance.addEdge("A", null, 0);
        });
        assertThrows(RuntimeException.class, () -> {
            instance.addEdge(null, null, 0);
        });
        assertFalse(instance.addEdge("A", "B", 1));

        Collection<Edge<String, Integer>> ced = instance.edges();
        assertEquals(8, ced.size(), "Must have 8 edges");
        for (int i = 0; i < co.size(); i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(co.get(finalI)) && e.getVDest().equals(cd.get(finalI)) && e.getWeight().equals(cw.get(finalI)));
        }
        assertEquals(0, ced.size(), "Edges should be as inserted");

        instance.removeEdge("A", "B");
        ced = instance.edges();
        assertEquals(7, ced.size(), "Must have 7 edges");
        for (int i = 1; i < co.size(); i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(co.get(finalI)) && e.getVDest().equals(cd.get(finalI)) && e.getWeight().equals(cw.get(finalI)));
        }
        assertEquals(0, ced.size(), "Edges should be as inserted");

        instance.removeEdge("E", "E");
        ced = instance.edges();
        assertEquals(6, ced.size(), "Must have 6 edges");
        for (int i = 1; i < co.size() - 1; i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(co.get(finalI)) && e.getVDest().equals(cd.get(finalI)) && e.getWeight().equals(cw.get(finalI)));
        }
        assertEquals(0, ced.size(), "Edges should be as inserted");

        instance.removeEdge("A", "C");
        instance.removeEdge("B", "D");
        instance.removeEdge("C", "D");
        instance.removeEdge("C", "E");
        instance.removeEdge("D", "A");
        instance.removeEdge("E", "D");

        assertEquals(0, instance.edges().size(), "edges should be empty");

        instance.addEdge(co.get(0), cd.get(0), cw.get(0));
        instance.edge(co.get(0), cd.get(0)).setWeight(0);
        assertEquals("A -> B\n" +
                "Weight: 0", instance.edge(co.get(0), cd.get(0)).toString());
        assertEquals(((ArrayList<Edge<String, Integer>>) instance.edges()).get(0), ((ArrayList<Edge<String, Integer>>) instance.edges()).get(0));
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < co.size(); i++)
            assertEquals(cw.get(i), instance.edge(co.get(i), cd.get(i)).getWeight(), "edge between " + co.get(i) + " - " + cd.get(i) + " should be " + cw.get(i));

        assertNull(instance.edge("A", "E"), "edge should be null");
        assertNull(instance.edge("D", "B"), "edge should be null");
        instance.removeEdge("D", "A");
        assertNull(instance.edge("D", "A"), "edge should be null");
    }

    /**
     * Test of getEdge by key method, of class Graph.
     */
    @Test
    public void testGetEdgeByKey() {
        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < co.size(); i++)
            assertEquals(cw.get(i), instance.edge(instance.key(co.get(i)), instance.key(cd.get(i))).getWeight(), "edge between " + co.get(i) + " - " + cd.get(i) + " should be " + cw.get(i));

        assertNull(instance.edge(instance.key("A"), instance.key("E")), "edge should be null");
        assertNull(instance.edge(instance.key("D"), instance.key("B")), "edge should be null");
        instance.removeEdge("D", "A");
        assertNull(instance.edge(instance.key("D"), instance.key("A")), "edge should be null");
    }


    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(-1, instance.outDegree("L"), "degree should be -1");
        assertEquals(2, instance.outDegree("A"), "degree should be 2");
        assertEquals(1, instance.outDegree("B"), "degree should be 1");
        assertEquals(2, instance.outDegree("E"), "degree should be 2");
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(-1, instance.inDegree("L"), "degree should be -1");
        assertEquals(1, instance.inDegree("A"), "degree should be 1");
        assertEquals(3, instance.inDegree("D"), "degree should be 3");
        assertEquals(2, instance.inDegree("E"), "degree should be 2");
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {
        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        Collection<Edge<String, Integer>> coe = instance.outgoingEdges("C");
        assertEquals(2, coe.size(), "Outgoing edges of vert C should be 2");
        coe.removeIf(e -> e.getWeight() == 4 || e.getWeight() == 5);
        assertEquals(0, coe.size(), "Outgoing edges of vert C should be 4 and 5");

        coe = instance.outgoingEdges("E");
        assertEquals(2, coe.size(), "Outgoing edges of vert E should be 2");
        coe.removeIf(e -> e.getWeight() == 7 || e.getWeight() == 8);
        assertEquals(0, coe.size(), "Outgoing edges of vert E should be 7 and 8");

        instance.removeEdge("E", "E");

        coe = instance.outgoingEdges("E");
        assertEquals(1, coe.size(), "Outgoing edges of vert E should be 1");
        coe.removeIf(e -> e.getWeight() == 7);
        assertEquals(0, coe.size(), "Outgoing edges of vert E should be 7");

        instance.removeEdge("E", "D");

        coe = instance.outgoingEdges("E");
        assertEquals(0, coe.size(), "Outgoing edges of vert E should be empty");
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {
        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));
        assertEquals(0, instance.incomingEdges("L").size());

        Collection<Edge<String, Integer>> cie = instance.incomingEdges("D");
        assertEquals(3, cie.size(), "Incoming edges of vert C should be 3");
        cie.removeIf(e -> e.getWeight() == 3 || e.getWeight() == 4 || e.getWeight() == 7);
        assertEquals(0, cie.size(), "Incoming edges of vert C should be 3, 4 and 7");

        cie = instance.incomingEdges("E");
        assertEquals(2, cie.size(), "Incoming edges of vert E should be 2");
        cie.removeIf(e -> e.getWeight() == 5 || e.getWeight() == 8);
        assertEquals(0, cie.size(), "Incoming edges of vert C should be 5 and 8");

        instance.removeEdge("E", "E");

        cie = instance.incomingEdges("E");
        assertEquals(1, cie.size(), "Incoming edges of vert E should be 1");
        cie.removeIf(e -> e.getWeight() == 5);
        assertEquals(0, cie.size(), "Incoming edges of vert C should be 5");

        instance.removeEdge("C", "E");

        cie = instance.incomingEdges("E");
        assertEquals(0, cie.size(), "Incoming edges of vert C should be empty");
    }


    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {
        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));
        instance.removeVertex("F");
        instance.removeVertex("G");
        instance.removeVertex("H");
        instance.removeVertex("I");
        instance.removeVertex("J");
        instance.removeVertex("K");


        assertEquals(5, instance.numVertices(), "Num vertices should be 5");
        assertEquals(8, instance.numEdges(), "Num vertices should be 8");
        instance.removeVertex("A");
        assertEquals(4, instance.numVertices(), "Num vertices should be 4");
        assertEquals(5, instance.numEdges(), "Num vertices should be 5");
        instance.removeVertex("B");
        assertEquals(3, instance.numVertices(), "Num vertices should be 3");
        assertEquals(4, instance.numEdges(), "Num vertices should be 4");
        instance.removeVertex("C");
        assertEquals(2, instance.numVertices(), "Num vertices should be 2");
        assertEquals(2, instance.numEdges(), "Num vertices should be 2");
        instance.removeVertex("D");
        assertEquals(1, instance.numVertices(), "Num vertices should be 1");
        assertEquals(1, instance.numEdges(), "Num vertices should be 1");
        instance.removeVertex("E");
        assertEquals(0, instance.numVertices(), "Num vertices should be 0");
        assertEquals(0, instance.numEdges(), "Num vertices should be 0");
    }

    /**
     * Test of removeEdge method, of class Graph.
     */
    @Test
    public void testRemoveEdge() {
        assertEquals(0, instance.numEdges(), "Num edges should be 0");

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertFalse(instance.removeEdge("L", "A"));
        assertFalse(instance.removeEdge("A", "L"));
        assertFalse(instance.removeEdge("A", "A"));

        assertEquals(11, instance.numVertices(), "Num edges should be 11");
        assertEquals(14, instance.numEdges(), "Num edges should be 14");

        for (int i = 0; i < co.size() - 1; i++) {
            instance.removeEdge(co.get(i), cd.get(i));
            Collection<Edge<String, Integer>> ced = instance.edges();
            int expected = co.size() - i - 1;
            assertEquals(expected, ced.size(), "Expected size is " + expected);
            for (int j = i + 1; j < co.size(); j++) {
                int finalJ = j;
                ced.removeIf(e -> e.getVOrig().equals(co.get(finalJ)) && e.getVDest().equals(cd.get(finalJ)) && e.getWeight().equals(cw.get(finalJ)));
            }
            assertEquals(0, ced.size(), "Expected size is 0");
        }
    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testClone() {
        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(11, instance.numVertices(), "Num vertices should be 11");
        assertEquals(14, instance.numEdges(), "Num vertices should be 14");

        Graph<String, Integer> instClone = instance.clone();

        for (int i = 0; i < co.size(); i++) {
            Edge<String, Integer> ec = instClone.edge(co.get(i), cd.get(i));
            assertEquals(co.get(i), ec.getVOrig());
            assertEquals(cd.get(i), ec.getVDest());
            assertEquals(cw.get(i), ec.getWeight());
        }

        for (String v : co)
            instClone.removeVertex(v);

        assertEquals(11, instance.numVertices(), "Num vertices should be 11");
        assertEquals(14, instance.numEdges(), "Num vertices should be 14");
        assertEquals(0, instClone.numVertices(), "Num vertices should be 0");
        assertEquals(0, instClone.numEdges(), "Num vertices should be 0");
    }

    @Test
    public void testEquals() {
        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        MatrixGraph<String, Integer> otherInst = new MatrixGraph<>(true);
        for (int i = 0; i < co.size(); i++)
            otherInst.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals(instance, otherInst, "Graphs should be equal");

        otherInst.removeVertex("A");

        assertNotEquals(instance, otherInst, "Graphs should NOT be equal");

        instance.removeVertex("A");

        assertEquals(instance, otherInst, "Graphs should be equal");

        otherInst.removeEdge("C", "E");

        assertNotEquals(instance, otherInst, "Graphs should NOT be equal");

        instance.removeEdge("C", "E");

        assertEquals(instance, otherInst, "Graphs should be equal");

        assertEquals(instance, instance);

        assertNotEquals(new ArrayList<>(), instance);
    }

    @Test
    public void testUnDirectedGraph() {
        instance = new MatrixGraph<>(false);

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        for (int i = 0; i < co.size(); i++) {
            Edge<String, Integer> ec = instance.edge(co.get(i), cd.get(i));
            assertEquals(co.get(i), ec.getVOrig());
            assertEquals(cd.get(i), ec.getVDest());
            assertEquals(cw.get(i), ec.getWeight());
            Edge<String, Integer> ecu = instance.edge(cd.get(i), co.get(i));
            assertEquals(cd.get(i), ecu.getVOrig());
            assertEquals(co.get(i), ecu.getVDest());
            assertEquals(cw.get(i), ecu.getWeight());
        }

        instance.removeEdge(co.get(0), cd.get(0));

        for (int i = 1; i < co.size(); i++) {
            Edge<String, Integer> ec = instance.edge(co.get(i), cd.get(i));
            assertEquals(co.get(i), ec.getVOrig());
            assertEquals(cd.get(i), ec.getVDest());
            assertEquals(cw.get(i), ec.getWeight());
            Edge<String, Integer> ecu = instance.edge(cd.get(i), co.get(i));
            assertEquals(cd.get(i), ecu.getVOrig());
            assertEquals(co.get(i), ecu.getVDest());
            assertEquals(cw.get(i), ecu.getWeight());
        }
    }

    @Test
    void hashCodeTest() {
        assertEquals(961683, instance.hashCode());
        instance.addEdge(co.get(0), cd.get(0), cw.get(0));
        assertEquals(3042, ((ArrayList<Edge<String, Integer>>) instance.edges()).get(0).hashCode());
    }

    @Test
    void toStringTest() {

        for (int i = 0; i < co.size(); i++)
            instance.addEdge(co.get(i), cd.get(i), cw.get(i));

        assertEquals("Vertices:\n" +
                "A\n" +
                "B\n" +
                "C\n" +
                "D\n" +
                "E\n" +
                "F\n" +
                "G\n" +
                "H\n" +
                "I\n" +
                "J\n" +
                "K\n" +
                "\n" +
                "Matrix:\n" +
                "    |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10 \n" +
                " 0\t|     |  X  |  X  |     |     |     |     |     |     |     |     \n" +
                " 1\t|     |     |     |  X  |     |     |     |     |     |     |     \n" +
                " 2\t|     |     |     |  X  |  X  |     |     |     |     |     |     \n" +
                " 3\t|  X  |     |     |     |     |     |     |     |     |     |     \n" +
                " 4\t|     |     |     |  X  |  X  |     |     |     |     |     |     \n" +
                " 5\t|     |     |     |     |     |  X  |     |     |     |     |     \n" +
                " 6\t|     |     |     |     |     |     |  X  |     |     |     |     \n" +
                " 7\t|     |     |     |     |     |     |     |  X  |     |     |     \n" +
                " 8\t|     |     |     |     |     |     |     |     |  X  |     |     \n" +
                " 9\t|     |     |     |     |     |     |     |     |     |  X  |     \n" +
                " 10\t|     |     |     |     |     |     |     |     |     |     |  X  \n" +
                "\n" +
                "Edges:\n" +
                "From 0 to 1-> A -> B\n" +
                "Weight: 1\n" +
                "From 0 to 2-> A -> C\n" +
                "Weight: 2\n" +
                "From 1 to 3-> B -> D\n" +
                "Weight: 3\n" +
                "From 2 to 3-> C -> D\n" +
                "Weight: 4\n" +
                "From 2 to 4-> C -> E\n" +
                "Weight: 5\n" +
                "From 3 to 0-> D -> A\n" +
                "Weight: 6\n" +
                "From 4 to 3-> E -> D\n" +
                "Weight: 7\n" +
                "From 4 to 4-> E -> E\n" +
                "Weight: 8\n" +
                "From 5 to 5-> F -> F\n" +
                "Weight: 9\n" +
                "From 6 to 6-> G -> G\n" +
                "Weight: 10\n" +
                "From 7 to 7-> H -> H\n" +
                "Weight: 11\n" +
                "From 8 to 8-> I -> I\n" +
                "Weight: 12\n" +
                "From 9 to 9-> J -> J\n" +
                "Weight: 13\n" +
                "From 10 to 10-> K -> K\n" +
                "Weight: 14\n" +
                "\n", instance.toString());
    }

}