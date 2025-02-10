import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the methods of SkipList class
 * 
 * @author CS Staff
 * 
 * @version 2024-01-22
 */

public class SkipListTest extends TestCase {
    private SkipList<String, Rectangle> sl;

    /**
     * Sets up the testing environment
     */
    public void setUp() {
        sl = new SkipList<String, Rectangle>();
    }


    /***
     * Example 1: Test `randomLevel` method with
     * predetermined random values using `TestableRandom`
     */
    public void testRandomLevelOne() {
        TestableRandom.setNextInts(1);
        // TestableRandom.setNextBooleans(false);

        int randomLevelValue = sl.randomLevel();

        // This returns 0 because the first preset
        // random boolean is `false` which breaks
        // the `while condition inside the `randomLevel` method
        int expectedLevelValue = 0;

        // Compare the values
        assertEquals(expectedLevelValue, randomLevelValue);
    }


    public void testRandomLevelTwo() {
        TestableRandom.setNextInts(0, 1);
        // TestableRandom.setNextBooleans(false);
        int randomLevelValue = sl.randomLevel();

        // This returns 0 because the first preset
        // random boolean is `false` which breaks
        // the `while condition inside the `randomLevel` method
        int expectedLevelValue = 1;

        // Compare the values
        assertEquals(expectedLevelValue, randomLevelValue);
    }


    /***
     * Example 2: Test `randomLevel` method with
     * predetermined random values using `TestableRandom`
     */
    public void testRandomLevelFour() {
        TestableRandom.setNextInts(0, 2, 4, 3, 4, 3);
        // TestableRandom.setNextBooleans(true, true, true, false, true, false);

        int randomLevelValue = sl.randomLevel();

        // This returns 4 because the fourth preset
        // random boolean is `false` which breaks
        // the `while condition inside the `randomLevel` method
        int expectedLevelValue = 3;

        // Compare the values
        assertEquals(expectedLevelValue, randomLevelValue);
    }


    public void testInsert() {
        // insert in empty list
        Rectangle test = new Rectangle(0, 0, 10, 10);
        KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("b",
            test);
        sl.insert(pair);
        assertEquals(1, sl.size());
        ArrayList<KVPair<String, Rectangle>> search =
            new ArrayList<KVPair<String, Rectangle>>();
        search = sl.search("b");
        assertEquals(search.get(0).getValue(), test);

        // insert after head element
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("d",
            test);
        sl.insert(pair2);
        search = sl.search("b");
        assertEquals(2, sl.size());
        assertEquals(search.get(0), pair);
        search = sl.search("d");
        assertEquals(search.get(0).getValue(), pair2.getValue());

        // insert between two elements
        KVPair<String, Rectangle> pair3 = new KVPair<String, Rectangle>("c",
            test);
        sl.insert(pair3);
        search = sl.search("c");
        assertEquals(3, sl.size());
        assertEquals(search.get(0), pair3);
        // use dump here to validate order

        // insert at the beginning
        KVPair<String, Rectangle> pair4 = new KVPair<String, Rectangle>("a",
            test);
        sl.insert(pair4);
        search = sl.search("a");
        assertEquals(4, sl.size());
        assertEquals(search.get(0), pair4);
    }


    public void testRemove() {
        // remove from empty list
        Rectangle test = new Rectangle(0, 0, 10, 10);
        KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("b",
            test);
        sl.insert(pair);
        assertEquals(sl.size(), 1);
        sl.remove("b");
        assertEquals(sl.size(), 0);

        // remove from length 2 but from head
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("c",
            test);
        sl.insert(pair);
        sl.insert(pair2);
        assertEquals(sl.size(), 2);
        sl.remove("b");
        assertEquals(sl.size(), 1);
    }

    
    public void testRemoveByValue() {
        // remove from empty list
        Rectangle test = new Rectangle(0, 0, 10, 10);
        KVPair<String, Rectangle> pair = new KVPair<String, Rectangle>("b",
            test);
        sl.insert(pair);
        assertEquals(sl.size(), 1);
        sl.removeByValue(test);
        assertEquals(sl.size(), 0);

        // remove from length 2 but from head
        KVPair<String, Rectangle> pair2 = new KVPair<String, Rectangle>("c",
            test);
        sl.insert(pair);
        sl.insert(pair2);
        assertEquals(sl.size(), 2);
        sl.remove("b");
        assertEquals(sl.size(), 1);
    }

}
