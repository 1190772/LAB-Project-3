package lapr.project.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoDTreeTest {
    int[] elements = {20,15,10,13,8,17,40,50,30,7};
    double[] x = {17.2,12.1,7.4,9.6,2.3,15.9,24.5,32.0,21.7,4.8};
    double[] y = {20.9,15.6,10.8,13.5,8.3,17.7,40.1,50.6,30.2,7.3};
    int[] height={0,1,2,3,3,3,3,3,3,4};

    TwoDTree<Integer> instance;

    @BeforeEach
    void setUp() {
        instance = new TwoDTree<>();
        for(int i = 0; i < elements.length; i++)
            instance.insert(elements[i], x[i], y[i]);
    }

    /**
     * Test of size method, of class TwoDTree.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        assertEquals(elements.length, instance.size());

        TwoDTree<String> sInstance = new TwoDTree<>();
        assertEquals(0, sInstance.size());
        sInstance.insert("A", 4.23, 8.98);
        assertEquals(1, sInstance.size());
        sInstance.insert("B", 6.56, 10.68);
        assertEquals(2, sInstance.size());
        sInstance.insert("A", 4.23, 8.98);
        assertEquals(2, sInstance.size());
    }

    /**
     * Test of height method, of class TwoDTree.
     */
    @Test
    public void testHeight() {
        System.out.println("height");

        instance = new TwoDTree<>();
        assertEquals(instance.height(), -1);

        for(int idx=0; idx<elements.length; idx++) {
        instance.insert(elements[idx], x[idx], y[idx]);
        assertEquals(instance.height(), height[idx]);
        }

        instance = new TwoDTree<>();
        assertEquals(instance.height(), -1);
    }

    /**
     * Test of insert method, of class TwoDTree.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        int[] elements2 = {20,15,10,13,8,17,40,50,30,20,15,10};
        double[] x2 = {17.2,12.1,7.4,9.6,2.3,15.9,24.5,32.0,21.7,4.8, 3.1, 24.8};
        double[] y2 = {20.9,15.6,10.8,13.5,8.3,17.7,40.1,50.6,30.2,7.3, 1.2, 30.1};
        TwoDTree<Integer> instance2 = new TwoDTree<>();

        for (int i=0; i<elements2.length; i++) { //new elements
            instance2.insert(elements2[i], x2[i], y2[i]);
            assertEquals(i+1, instance2.size());
        }

        for (int i=9; i < elements2.length; i++) { //duplicated elements => same size
            instance2.insert(elements2[i], x2[i], y2[i]);
            assertEquals(elements2.length, instance2.size());
        }
    }

    @Test
    void findNearestNeighbour() {
        System.out.println("nearestNeighbour");
        int expected;
        int actual;

        expected = 50;
        actual = instance.findNearestNeighbour(50.0, 50.0);
        assertEquals(expected, actual);

        expected = 17;
        actual = instance.findNearestNeighbour(15.8, 17.8);
        assertEquals(expected, actual);

        expected = 8;
        actual = instance.findNearestNeighbour(0.0, 0.0);
        assertEquals(expected, actual);
    }

}