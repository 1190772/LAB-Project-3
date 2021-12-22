package lapr.project.utils.graph;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * @author DEI-ISEP
 */
public class Algorithms {

    /**
     * Calculates the minimum distance graph using Floyd-Warshall
     *
     * @param g   initial graph
     * @param ce  comparator between elements of type E
     * @param sum sum two elements of type E
     * @return the minimum distance graph
     */
    public static <V, E> MatrixGraph<V, E> minDistGraph(Graph<V, E> g, Comparator<E> ce, BinaryOperator<E> sum) {
        int numVerts = g.numVertices();
        if (numVerts == 0)
            return null;
        @SuppressWarnings("unchecked")
        E[][] matrix = (E[][]) new Object[numVerts][numVerts];
        for (int i = 0; i < numVerts; i++)
            for (int j = 0; j < numVerts; j++) {
                Edge<V, E> edge = g.edge(i, j);
                if (edge != null)
                    matrix[i][j] = edge.getWeight();
            }

        for (int k = 0; k < matrix.length; k++)
            for (int i = 0; i < matrix.length; i++) {
                if (i != k && matrix[i][k] != null)
                    for (int j = 0; j < matrix.length; j++)
                        if (i != j && k != j && matrix[k][j] != null) {
                            E eSum = sum.apply(matrix[i][k], matrix[k][j]);
                            if (matrix[i][j] == null || ce.compare(eSum, matrix[i][j]) < 0)
                                matrix[i][j] = eSum;
                        }
            }
        return new MatrixGraph<>(g.isDirected(), g.vertices(), matrix);
    }
}
