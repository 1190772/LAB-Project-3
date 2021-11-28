package lapr.project.utils;

import lapr.project.model.shared.Utils;

import java.awt.geom.Point2D;
import java.util.Comparator;

/**
 *
 * @author David Magalhães 1201237
 */
public class TwoDTree<T> {
    protected static class Node2D<T> {
    protected Point2D.Double coords;
    protected T element;
    protected Node2D<T> left;
    protected Node2D<T> right;

    /**
     * Constructs a node with the given element and coordinates.
     *
     * @param element the element to be stored
     * @param x x value
     * @param y y value
     */
    protected Node2D(T element, double x, double y){
    this.element = element;
    this.coords = new Point2D.Double();
    this.coords.x = x;
    this.coords.y = y;
    }

    // accessor methods
    protected double getX() { return coords.x; }
    protected double getY() { return coords.y; }
    protected T getElement() { return element; }
    protected Node2D<T> getLeft() { return left; }
    protected Node2D<T> getRight() { return right; }

    // update methods
    protected void setX(Double x) { coords.x = x; }
    protected void setY(Double y) { coords.y = y; }
    protected void setElement(Node2D<T> node) { element = node.element; }
    protected void setLeft(Node2D<T> leftChild) { left = leftChild; }
    protected void setRight(Node2D<T> rightChild) { right = rightChild; }
    }

    protected Node2D<T> root; // root of the tree

    /* Constructs an empty TwoDTree. */
    public TwoDTree() { root = null; }

    /*
     * @return root Node of the tree (or null if tree is empty)
     */
    protected Node2D<T> root() {
    return root;
    }

    /*
     * Verifies if the tree is empty
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty(){
    return root==null;
    }

    /*
     * Returns the number of nodes in the tree.
     * @return number of nodes in the tree
     */
    public int size(){
    return size(root);
    }

    private int size(Node2D<T> node) {
        if (node == null)
            return 0;

        return (1 + size(node.getLeft()) + size(node.getRight()));
    }

    /*
     * Returns the height of the tree
     * @return height
     */
    public int height(){
        if (root == null)
            return -1;

        return height(root)-1;
    }

    /*
     * Returns the height of the subtree rooted at Node node.
     * @param node A valid Node within the tree
     * @return height
     */
    protected int height(Node2D<T> node){
        if (node == null)
            return 0;

        int hl = height(node.getLeft());
        int hr = height(node.getRight());

        return 1 + (Math.max(hl, hr));
    }

    protected final Comparator<Node2D<T>> cmpX = new Comparator<Node2D<T>>() {
        @Override
        public int compare(Node2D<T> p1, Node2D<T> p2) {
            return Double.compare(p1.getX(), p2.getX());
        }
    };

    protected final Comparator<Node2D<T>> cmpY = new Comparator<Node2D<T>>() {
        @Override
        public int compare(Node2D<T> p1, Node2D<T> p2) {
            return Double.compare(p1.getY(), p2.getY());
        }
    };

    public void insert(T obj, Double x, Double y) {
        Node2D<T> node = new Node2D<>(obj, x, y);

        if (root != null)
            insert(root, node, true);
        else
            root = node;
    }

    private void insert(Node2D<T> currentNode, Node2D<T> node, boolean divX) {
        if (node.coords.equals(currentNode.coords))
            return;
        int cmpResult = (divX ? cmpX : cmpY).compare(node, currentNode);
        if (cmpResult == -1)
            if (currentNode.getLeft() == null)
                currentNode.setLeft(node);
            else
                insert(currentNode.getLeft (), node, !divX);
        else
        if (currentNode.getRight() == null)
            currentNode.setRight(node);
        else
            insert(currentNode.right, node, !divX);
    }

    public T findNearestNeighbour(double x, double y) {
        return findNearestNeighbour(root, x, y, root, true).getElement();
    }

    private Node2D<T> findNearestNeighbour(Node2D<T> node, double x, double y, Node2D<T> closestNode, boolean divX) {
        if (node == null)
            return closestNode;

        double d = Utils.distanceBetweenTwoCoordinates(node.getX(), node.getY(), x, y);
        double closestDist = Utils.distanceBetweenTwoCoordinates(closestNode.getX(), closestNode.getY(), x, y);

        if (closestDist >= d)
            closestNode = node;

        double delta = divX ? x - node.getX() : y - node.getY();
        double delta2 = delta * delta;

        Node2D<T> node1 = delta < 0 ? node.getLeft() : node.getRight();
        Node2D<T> node2 = delta < 0 ? node.getRight() : node.getLeft();

        closestNode = findNearestNeighbour(node1, x, y, closestNode, !divX);

        if (delta2 < closestDist)
            closestNode = findNearestNeighbour(node2, x, y, closestNode, !divX);

        return closestNode;
    }

    int balanceFactor(Node2D<T> node) {
        return height(node.getRight()) - height(node.getLeft());
    }

}
