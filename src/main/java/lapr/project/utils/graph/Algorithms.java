package lapr.project.utils.graph;

import java.util.*;
import java.util.function.BinaryOperator;

/**
 * @author DEI-ISEP
 */
public class Algorithms {

    /**
     * Performs breadth-first search of a Graph starting in a vertex
     *
     * @param g    Graph instance
     * @param vert vertex that will be the source of the search
     * @return a LinkedList with the vertices of breadth-first search
     */
    public static <V, E> List<V> breadthFirstSearch(Graph<V, E> g, V vert) {
        LinkedList<V> result = new LinkedList<>();
        if (!g.validVertex(vert))
            return result;

        ArrayList<V> qaux = new ArrayList<>();
        qaux.add(vert);
        boolean[] visited = new boolean[g.numVertices()];
        while (!qaux.isEmpty()) {
            V v = qaux.remove(0);
            if (!visited[g.key(v)])
                result.add(v);
            visited[g.key(v)] = true;
            for (V ve : g.adjVertices(v))
                if (!visited[g.key(ve)] && !qaux.contains(ve))
                    qaux.add(ve);
        }


        return result;

    }

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param g       Graph instance
     * @param vOrig   vertex of graph g that will be the source of the search
     * @param visited set of previously visited vertices
     * @param qdfs    return LinkedList with vertices of depth-first search
     */
    private static <V, E> void depthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, List<V> qdfs) {
        if (visited[g.key(vOrig)])
            return;
        qdfs.add(vOrig);
        visited[g.key(vOrig)] = true;
        for (V adjVert : g.adjVertices(vOrig))
            depthFirstSearch(g, adjVert, visited, qdfs);
    }

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param g    Graph instance
     * @param vert vertex of graph g that will be the source of the search
     * @return a LinkedList with the vertices of depth-first search
     */
    public static <V, E> List<V> depthFirstSearch(Graph<V, E> g, V vert) {
        LinkedList<V> qdfs = new LinkedList<>();
        if (!g.validVertex(vert))
            return qdfs;
        boolean[] visited = new boolean[g.numVertices()];
        depthFirstSearch(g, vert, visited, qdfs);
        return qdfs;
    }


    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g       Graph instance
     * @param vOrig   Vertex that will be the source of the path
     * @param vDest   Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path    stack with vertices of the current path (the path is in reverse order)
     * @param paths   ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
                                        LinkedList<V> path, List<LinkedList<V>> paths) {

        path.add(vOrig);
        visited[g.key(vOrig)] = true;
        for (V vAdj : g.adjVertices(vOrig)) {
            if (vAdj.equals(vDest)) {
                path.add(vDest);
                paths.add(new LinkedList<>(path));
                path.removeLast();
            } else if (!visited[g.key(vAdj)])
                allPaths(g, vAdj, vDest, visited, path, paths);
        }
        path.removeLast();
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g     Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from vOrig to vDest
     */
    public static <V, E> List<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {

        ArrayList<LinkedList<V>> result = new ArrayList<>();
        boolean[] visited = new boolean[g.numVertices()];
        LinkedList<V> path = new LinkedList<>();
        allPaths(g, vOrig, vDest, visited, path, result);
        return result;
    }


    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non-negative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of previously visited vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    private static <V, E> void shortestPathDijkstra(Graph<V, E> g, V vOrig,
                                                    Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                                    boolean[] visited, V[] pathKeys, E[] dist) {
        dist[g.key(vOrig)] = zero;
        pathKeys[g.key(vOrig)] = vOrig;

        while (vOrig != null) {
            visited[g.key(vOrig)] = true;
            for (V vAdj : g.adjVertices(vOrig)) {
                Edge<V, E> edge = g.edge(vOrig, vAdj);

                if (!visited[g.key(vAdj)] && (dist[g.key(vAdj)]==null || ce.compare(dist[g.key(vAdj)], sum.apply(dist[g.key(vOrig)], edge.getWeight())) > 0)) {
                    dist[g.key(vAdj)] = sum.apply(dist[g.key(vOrig)], edge.getWeight());
                    pathKeys[g.key(vAdj)] = vOrig;
                }
            }

            E minDist = null;
            vOrig = null;
            for (V vert : g.vertices()) {
                int i = g.key(vert);
                if (!visited[i] && dist[i] != null &&
                        (minDist == null || ce.compare(dist[i], minDist) < 0)) {
                    minDist = dist[i];
                    vOrig = vert;
                }
            }
        }
    }


    /**
     * Shortest-path between two vertices
     *
     * @param g         graph
     * @param vOrig     origin vertex
     * @param vDest     destination vertex
     * @param ce        comparator between elements of type E
     * @param sum       sum two elements of type E
     * @param zero      neutral element of the sum in elements of type E
     * @param shortPath returns the vertices which make the shortest path
     * @return if vertices exist in the graph and are connected, true, false otherwise
     */
    public static <V, E> E shortestPath(Graph<V, E> g, V vOrig, V vDest,
                                        Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                        LinkedList<V> shortPath) {
        if (g.key(vOrig)==-1||g.key(vDest)==-1)
            return null;

        @SuppressWarnings("unchecked")
        E[] dist = (E[]) new Object[g.numVertices()];
        @SuppressWarnings("unchecked")
        V[] path = (V[]) new Object[g.numVertices()];
        boolean[] visited = new boolean[g.numVertices()];
        for (int i = 0; i < g.numVertices(); i++) {
            dist[i] = null;
            path[i] = null;
        }
        dist[g.key(vOrig)] = zero;

        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, path, dist);
        E lengthPath = dist[g.key(vDest)];
        if (lengthPath == null)
            return null;
        getPath(g, vOrig, vDest, path, shortPath);
        return lengthPath;

    }

    /**
     * Shortest-path between a vertex and all other vertices
     *
     * @param g     graph
     * @param vOrig start vertex
     * @param ce    comparator between elements of type E
     * @param sum   sum two elements of type E
     * @param zero  neutral element of the sum in elements of type E
     * @param paths returns all the minimum paths
     * @param dists returns the corresponding minimum distances
     * @return if vOrig exists in the graph true, false otherwise
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig,
                                               Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                               List<LinkedList<V>> paths, List<E> dists) {

        if (g.key(vOrig) == -1)
            return false;
        for (V vDest : g.vertices()) {
            LinkedList<V> path = new LinkedList<>();
            dists.add(g.key(vDest), shortestPath(g, vOrig, vDest, ce, sum, zero, path));
            paths.add(g.key(vDest), path);
        }
        return true;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest,
                                       V[] pathKeys, LinkedList<V> path) {

        path.add(vDest);
        while (!path.getFirst().equals(vOrig)){
            path.addFirst(pathKeys[g.key(path.getFirst())]);
        }
    }

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