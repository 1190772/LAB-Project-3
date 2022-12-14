
package lapr.project.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DEI-ESINF
 */
public class BSTTest {
    Integer[] arr = {20,15,10,13,8,17,40,50,30,7};
    int[] height={0,1,2,3,3,3,3,3,3,4};
    Integer[] inorderT= {7,8,10,13,15,17,20,30,40,50};
    Integer[] preorderT= {20, 15, 10, 8, 7, 13, 17, 40, 30, 50};
    Integer[] posorderT = {7, 8, 13, 10, 17, 15, 30, 50, 40, 20};
    
    BST<Integer> instance;    
    
    public BSTTest() {
    }
    
    @BeforeEach
    public void setUp(){
        instance = new BST();
        for(int i :arr)
            instance.insert(i);        
    }

    /**
     * Test of setElement method, of class BST.
     */
    @Test
    public void setElement() {
        int expected = 9;
        instance.root.setElement(expected);
        int actual = instance.root.getElement();
        assertEquals(expected, actual);
    }

    /**
     * Test of size method, of class BST.
     */
    @Test
    public void testSize() {
        assertEquals(instance.size(), arr.length);
        
        BST<String> sInstance = new BST();
        assertEquals(sInstance.size(), 0);
        sInstance.insert("A");
        assertEquals(sInstance.size(), 1);
        sInstance.insert("B");
        assertEquals(sInstance.size(), 2);
        sInstance.insert("A");
        assertEquals(sInstance.size(), 2);

        instance.root = null;
        assertEquals(0, instance.size());
    }
   
    /**
     * Test of insert method, of class BST.
     */
    @Test
    public void testInsert() {
        int arr[] = {20,15,10,13,8,17,40,50,30,20,15,10};
        BST<Integer> instance = new BST();
        for (int i=0; i<9; i++){            //new elements
            instance.insert(arr[i]);
            assertEquals(instance.size(), i+1);
        }
        for(int i=9; i<arr.length; i++){    //duplicated elements => same size
            instance.insert(arr[i]);
            assertEquals(instance.size(), 9);
        }
    }
    /**
     * Test of remove method, of class BST.
     */
    @Test
    public void testRemove() {

        int qtd=arr.length;
        instance.remove(999);

        assertEquals(instance.size(), qtd);
        for (int i=0; i<arr.length; i++){
            instance.remove(arr[i]);
            qtd--;
            assertEquals(qtd,instance.size());
        }
        
        instance.remove(999);
        assertEquals(0,instance.size());


    }
    /**
     * Test of isEmpty method, of class BST.
     */
    @Test
    public void testIsEmpty() {
        
        assertFalse(instance.isEmpty());
        instance = new BST();
        assertTrue(instance.isEmpty());

        instance.insert(11);
        assertFalse(instance.isEmpty());
        
        instance.remove(11);
        assertTrue(instance.isEmpty());
    }
    /**
     * Test of height method, of class BST.
     */
    @Test
    public void testHeight() {

        instance = new BST();
        assertEquals(instance.height(), -1);
        for(int idx=0; idx<arr.length; idx++){
            instance.insert(arr[idx]);
            assertEquals(instance.height(), height[idx]);
        }
        instance = new BST();
        assertEquals(instance.height(), -1);

        instance.root = null;
        assertEquals(-1, instance.height());
    }
    /**
     * Test of smallestelement method, of class TREE.
     */
    @Test
    public void testSmallestElement() {
        assertEquals(new Integer(7), instance.smallestElement());
        instance.remove(7);
        assertEquals(new Integer(8), instance.smallestElement());
        instance.remove(8);
        assertEquals(new Integer(10), instance.smallestElement());

        instance.root = null;
        assertNull(instance.smallestElement());
    }

    /**
     * Test of largestElement method, of class TREE.
     */
    @Test
    public void testLargestElement() {
        assertEquals(new Integer(50), instance.largestElement());
        instance.remove(50);
        assertEquals(new Integer(40), instance.largestElement());
        instance.remove(40);
        assertEquals(new Integer(30), instance.largestElement());

        instance.root = null;
        assertNull(instance.largestElement());
    }
    /**
     * Test of processBstByLevel method, of class TREE.
     */
    @Test
    public void testProcessBstByLevel() {
        Map<Integer,List<Integer>> expResult = new HashMap();
        expResult.put(0, Arrays.asList(new Integer[]{20}));
        expResult.put(1, Arrays.asList(new Integer[]{15,40}));
        expResult.put(2, Arrays.asList(new Integer[]{10,17,30,50}));
        expResult.put(3, Arrays.asList(new Integer[]{8,13}));
        expResult.put(4, Arrays.asList(new Integer[]{7}));
        
        Map<Integer,List<Integer>> result = instance.nodesByLevel();
        
        for(Map.Entry<Integer,List<Integer>> e : result.entrySet())
            assertEquals(expResult.get(e.getKey()), e.getValue());

        instance.root = null;
        assertEquals(new HashMap<>(), instance.nodesByLevel());
    }    
   

    /**
     * Test of inOrder method, of class BST.
     */
    @Test
    public void testInOrder() {
        List<Integer> lExpected = Arrays.asList(inorderT);
        assertEquals(lExpected, instance.inOrder());

        instance.root = null;
        Assertions.assertFalse(instance.inOrder().iterator().hasNext());
    }
    /**
     * Test of preOrder method, of class BST.
     */
    @Test
    public void testpreOrder() {
        List<Integer> lExpected = Arrays.asList(preorderT);
        assertEquals(lExpected, instance.preOrder());

        instance.root = null;
        Assertions.assertFalse(instance.preOrder().iterator().hasNext());
    }
    /**
     * Test of posOrder method, of class BST.
     */
    @Test
    public void testposOrder() {
        List<Integer> lExpected = Arrays.asList(posorderT);
        assertEquals(lExpected, instance.posOrder());

        instance.root = null;
        Assertions.assertFalse(instance.posOrder().iterator().hasNext());
    }

    @Test
    public void testFind() {
        BST<String> sInstance = new BST<>();
        sInstance.insert("B");
        sInstance.insert("A");
        sInstance.insert("E");
        sInstance.insert("C");
        sInstance.insert("F");
        sInstance.insert("D");
        String expected = "D";
        assertEquals(expected, sInstance.find(expected).getElement());
        expected = "G";
        assertNull(sInstance.find(expected));
    }
}
