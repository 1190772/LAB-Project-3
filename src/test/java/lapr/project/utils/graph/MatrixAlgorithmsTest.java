package lapr.project.utils.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixAlgorithmsTest {

    final Graph<String,Integer> completeMap = new MatrixGraph<>(false);
    Graph<String,Integer> incompleteMap = new MatrixGraph<>(false);

    public MatrixAlgorithmsTest() {
    }

    @BeforeEach
    public void setUp() {

        completeMap.addVertex("Porto");
        completeMap.addVertex("Braga");
        completeMap.addVertex("Vila Real");
        completeMap.addVertex("Aveiro");
        completeMap.addVertex("Coimbra");
        completeMap.addVertex("Leiria");

        completeMap.addVertex("Viseu");
        completeMap.addVertex("Guarda");
        completeMap.addVertex("Castelo Branco");
        completeMap.addVertex("Lisboa");
        completeMap.addVertex("Faro");

        completeMap.addEdge("Porto", "Aveiro", 75);
        completeMap.addEdge("Porto", "Braga", 60);
        completeMap.addEdge("Porto", "Vila Real", 100);
        completeMap.addEdge("Viseu", "Guarda", 75);
        completeMap.addEdge("Guarda", "Castelo Branco", 100);
        completeMap.addEdge("Aveiro", "Coimbra", 60);
        completeMap.addEdge("Coimbra", "Lisboa", 200);
        completeMap.addEdge("Coimbra", "Leiria", 80);
        completeMap.addEdge("Aveiro", "Leiria", 120);
        completeMap.addEdge("Leiria", "Lisboa", 150);

        incompleteMap = completeMap.clone();

        completeMap.addEdge("Aveiro", "Viseu", 85);
        completeMap.addEdge("Leiria", "Castelo Branco", 170);
        completeMap.addEdge("Lisboa", "Faro", 280);
    }

    private void checkContentEquals(List<String> l1, List<String> l2, String msg) {
        Collections.sort(l1);
        Collections.sort(l2);
        assertEquals(l1, l2, msg);
    }

    /**
     * Test minimum distance graph using Floyd-Warshall.
     */
    @Test
    public void testminDistGraph() {

        MatrixGraph<String, Integer> actual = Algorithms.minDistGraph(completeMap, Integer::compareTo, Integer::sum);
        assert actual != null;
        assertEquals("Vertices:\n" +
                "Porto\n" +
                "Braga\n" +
                "Vila Real\n" +
                "Aveiro\n" +
                "Coimbra\n" +
                "Leiria\n" +
                "Viseu\n" +
                "Guarda\n" +
                "Castelo Branco\n" +
                "Lisboa\n" +
                "Faro\n" +
                "\n" +
                "Matrix:\n" +
                "    |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10 \n" +
                " 0\t|     |  X  |  X  |  X  |  X  |  X  |  X  |  X  |  X  |  X  |  X  \n" +
                " 1\t|  X  |     |  X  |  X  |  X  |  X  |  X  |  X  |  X  |  X  |  X  \n" +
                " 2\t|  X  |  X  |     |  X  |  X  |  X  |  X  |  X  |  X  |  X  |  X  \n" +
                " 3\t|  X  |  X  |  X  |     |  X  |  X  |  X  |  X  |  X  |  X  |  X  \n" +
                " 4\t|  X  |  X  |  X  |  X  |     |  X  |  X  |  X  |  X  |  X  |  X  \n" +
                " 5\t|  X  |  X  |  X  |  X  |  X  |     |  X  |  X  |  X  |  X  |  X  \n" +
                " 6\t|  X  |  X  |  X  |  X  |  X  |  X  |     |  X  |  X  |  X  |  X  \n" +
                " 7\t|  X  |  X  |  X  |  X  |  X  |  X  |  X  |     |  X  |  X  |  X  \n" +
                " 8\t|  X  |  X  |  X  |  X  |  X  |  X  |  X  |  X  |     |  X  |  X  \n" +
                " 9\t|  X  |  X  |  X  |  X  |  X  |  X  |  X  |  X  |  X  |     |  X  \n" +
                " 10\t|  X  |  X  |  X  |  X  |  X  |  X  |  X  |  X  |  X  |  X  |     \n" +
                "\n" +
                "Edges:\n" +
                "From 0 to 1-> Porto -> Braga\n" +
                "Weight: 60\n" +
                "From 0 to 2-> Porto -> Vila Real\n" +
                "Weight: 100\n" +
                "From 0 to 3-> Porto -> Aveiro\n" +
                "Weight: 75\n" +
                "From 0 to 4-> Porto -> Coimbra\n" +
                "Weight: 135\n" +
                "From 0 to 5-> Porto -> Leiria\n" +
                "Weight: 195\n" +
                "From 0 to 6-> Porto -> Viseu\n" +
                "Weight: 160\n" +
                "From 0 to 7-> Porto -> Guarda\n" +
                "Weight: 235\n" +
                "From 0 to 8-> Porto -> Castelo Branco\n" +
                "Weight: 335\n" +
                "From 0 to 9-> Porto -> Lisboa\n" +
                "Weight: 335\n" +
                "From 0 to 10-> Porto -> Faro\n" +
                "Weight: 615\n" +
                "From 1 to 0-> Braga -> Porto\n" +
                "Weight: 60\n" +
                "From 1 to 2-> Braga -> Vila Real\n" +
                "Weight: 160\n" +
                "From 1 to 3-> Braga -> Aveiro\n" +
                "Weight: 135\n" +
                "From 1 to 4-> Braga -> Coimbra\n" +
                "Weight: 195\n" +
                "From 1 to 5-> Braga -> Leiria\n" +
                "Weight: 255\n" +
                "From 1 to 6-> Braga -> Viseu\n" +
                "Weight: 220\n" +
                "From 1 to 7-> Braga -> Guarda\n" +
                "Weight: 295\n" +
                "From 1 to 8-> Braga -> Castelo Branco\n" +
                "Weight: 395\n" +
                "From 1 to 9-> Braga -> Lisboa\n" +
                "Weight: 395\n" +
                "From 1 to 10-> Braga -> Faro\n" +
                "Weight: 675\n" +
                "From 2 to 0-> Vila Real -> Porto\n" +
                "Weight: 100\n" +
                "From 2 to 1-> Vila Real -> Braga\n" +
                "Weight: 160\n" +
                "From 2 to 3-> Vila Real -> Aveiro\n" +
                "Weight: 175\n" +
                "From 2 to 4-> Vila Real -> Coimbra\n" +
                "Weight: 235\n" +
                "From 2 to 5-> Vila Real -> Leiria\n" +
                "Weight: 295\n" +
                "From 2 to 6-> Vila Real -> Viseu\n" +
                "Weight: 260\n" +
                "From 2 to 7-> Vila Real -> Guarda\n" +
                "Weight: 335\n" +
                "From 2 to 8-> Vila Real -> Castelo Branco\n" +
                "Weight: 435\n" +
                "From 2 to 9-> Vila Real -> Lisboa\n" +
                "Weight: 435\n" +
                "From 2 to 10-> Vila Real -> Faro\n" +
                "Weight: 715\n" +
                "From 3 to 0-> Aveiro -> Porto\n" +
                "Weight: 75\n" +
                "From 3 to 1-> Aveiro -> Braga\n" +
                "Weight: 135\n" +
                "From 3 to 2-> Aveiro -> Vila Real\n" +
                "Weight: 175\n" +
                "From 3 to 4-> Aveiro -> Coimbra\n" +
                "Weight: 60\n" +
                "From 3 to 5-> Aveiro -> Leiria\n" +
                "Weight: 120\n" +
                "From 3 to 6-> Aveiro -> Viseu\n" +
                "Weight: 85\n" +
                "From 3 to 7-> Aveiro -> Guarda\n" +
                "Weight: 160\n" +
                "From 3 to 8-> Aveiro -> Castelo Branco\n" +
                "Weight: 260\n" +
                "From 3 to 9-> Aveiro -> Lisboa\n" +
                "Weight: 260\n" +
                "From 3 to 10-> Aveiro -> Faro\n" +
                "Weight: 540\n" +
                "From 4 to 0-> Coimbra -> Porto\n" +
                "Weight: 135\n" +
                "From 4 to 1-> Coimbra -> Braga\n" +
                "Weight: 195\n" +
                "From 4 to 2-> Coimbra -> Vila Real\n" +
                "Weight: 235\n" +
                "From 4 to 3-> Coimbra -> Aveiro\n" +
                "Weight: 60\n" +
                "From 4 to 5-> Coimbra -> Leiria\n" +
                "Weight: 80\n" +
                "From 4 to 6-> Coimbra -> Viseu\n" +
                "Weight: 145\n" +
                "From 4 to 7-> Coimbra -> Guarda\n" +
                "Weight: 220\n" +
                "From 4 to 8-> Coimbra -> Castelo Branco\n" +
                "Weight: 250\n" +
                "From 4 to 9-> Coimbra -> Lisboa\n" +
                "Weight: 200\n" +
                "From 4 to 10-> Coimbra -> Faro\n" +
                "Weight: 480\n" +
                "From 5 to 0-> Leiria -> Porto\n" +
                "Weight: 195\n" +
                "From 5 to 1-> Leiria -> Braga\n" +
                "Weight: 255\n" +
                "From 5 to 2-> Leiria -> Vila Real\n" +
                "Weight: 295\n" +
                "From 5 to 3-> Leiria -> Aveiro\n" +
                "Weight: 120\n" +
                "From 5 to 4-> Leiria -> Coimbra\n" +
                "Weight: 80\n" +
                "From 5 to 6-> Leiria -> Viseu\n" +
                "Weight: 205\n" +
                "From 5 to 7-> Leiria -> Guarda\n" +
                "Weight: 270\n" +
                "From 5 to 8-> Leiria -> Castelo Branco\n" +
                "Weight: 170\n" +
                "From 5 to 9-> Leiria -> Lisboa\n" +
                "Weight: 150\n" +
                "From 5 to 10-> Leiria -> Faro\n" +
                "Weight: 430\n" +
                "From 6 to 0-> Viseu -> Porto\n" +
                "Weight: 160\n" +
                "From 6 to 1-> Viseu -> Braga\n" +
                "Weight: 220\n" +
                "From 6 to 2-> Viseu -> Vila Real\n" +
                "Weight: 260\n" +
                "From 6 to 3-> Viseu -> Aveiro\n" +
                "Weight: 85\n" +
                "From 6 to 4-> Viseu -> Coimbra\n" +
                "Weight: 145\n" +
                "From 6 to 5-> Viseu -> Leiria\n" +
                "Weight: 205\n" +
                "From 6 to 7-> Viseu -> Guarda\n" +
                "Weight: 75\n" +
                "From 6 to 8-> Viseu -> Castelo Branco\n" +
                "Weight: 175\n" +
                "From 6 to 9-> Viseu -> Lisboa\n" +
                "Weight: 345\n" +
                "From 6 to 10-> Viseu -> Faro\n" +
                "Weight: 625\n" +
                "From 7 to 0-> Guarda -> Porto\n" +
                "Weight: 235\n" +
                "From 7 to 1-> Guarda -> Braga\n" +
                "Weight: 295\n" +
                "From 7 to 2-> Guarda -> Vila Real\n" +
                "Weight: 335\n" +
                "From 7 to 3-> Guarda -> Aveiro\n" +
                "Weight: 160\n" +
                "From 7 to 4-> Guarda -> Coimbra\n" +
                "Weight: 220\n" +
                "From 7 to 5-> Guarda -> Leiria\n" +
                "Weight: 270\n" +
                "From 7 to 6-> Guarda -> Viseu\n" +
                "Weight: 75\n" +
                "From 7 to 8-> Guarda -> Castelo Branco\n" +
                "Weight: 100\n" +
                "From 7 to 9-> Guarda -> Lisboa\n" +
                "Weight: 420\n" +
                "From 7 to 10-> Guarda -> Faro\n" +
                "Weight: 700\n" +
                "From 8 to 0-> Castelo Branco -> Porto\n" +
                "Weight: 335\n" +
                "From 8 to 1-> Castelo Branco -> Braga\n" +
                "Weight: 395\n" +
                "From 8 to 2-> Castelo Branco -> Vila Real\n" +
                "Weight: 435\n" +
                "From 8 to 3-> Castelo Branco -> Aveiro\n" +
                "Weight: 260\n" +
                "From 8 to 4-> Castelo Branco -> Coimbra\n" +
                "Weight: 250\n" +
                "From 8 to 5-> Castelo Branco -> Leiria\n" +
                "Weight: 170\n" +
                "From 8 to 6-> Castelo Branco -> Viseu\n" +
                "Weight: 175\n" +
                "From 8 to 7-> Castelo Branco -> Guarda\n" +
                "Weight: 100\n" +
                "From 8 to 9-> Castelo Branco -> Lisboa\n" +
                "Weight: 320\n" +
                "From 8 to 10-> Castelo Branco -> Faro\n" +
                "Weight: 600\n" +
                "From 9 to 0-> Lisboa -> Porto\n" +
                "Weight: 335\n" +
                "From 9 to 1-> Lisboa -> Braga\n" +
                "Weight: 395\n" +
                "From 9 to 2-> Lisboa -> Vila Real\n" +
                "Weight: 435\n" +
                "From 9 to 3-> Lisboa -> Aveiro\n" +
                "Weight: 260\n" +
                "From 9 to 4-> Lisboa -> Coimbra\n" +
                "Weight: 200\n" +
                "From 9 to 5-> Lisboa -> Leiria\n" +
                "Weight: 150\n" +
                "From 9 to 6-> Lisboa -> Viseu\n" +
                "Weight: 345\n" +
                "From 9 to 7-> Lisboa -> Guarda\n" +
                "Weight: 420\n" +
                "From 9 to 8-> Lisboa -> Castelo Branco\n" +
                "Weight: 320\n" +
                "From 9 to 10-> Lisboa -> Faro\n" +
                "Weight: 280\n" +
                "From 10 to 0-> Faro -> Porto\n" +
                "Weight: 615\n" +
                "From 10 to 1-> Faro -> Braga\n" +
                "Weight: 675\n" +
                "From 10 to 2-> Faro -> Vila Real\n" +
                "Weight: 715\n" +
                "From 10 to 3-> Faro -> Aveiro\n" +
                "Weight: 540\n" +
                "From 10 to 4-> Faro -> Coimbra\n" +
                "Weight: 480\n" +
                "From 10 to 5-> Faro -> Leiria\n" +
                "Weight: 430\n" +
                "From 10 to 6-> Faro -> Viseu\n" +
                "Weight: 625\n" +
                "From 10 to 7-> Faro -> Guarda\n" +
                "Weight: 700\n" +
                "From 10 to 8-> Faro -> Castelo Branco\n" +
                "Weight: 600\n" +
                "From 10 to 9-> Faro -> Lisboa\n" +
                "Weight: 280\n" +
                "\n", actual.toString());

    }
}
