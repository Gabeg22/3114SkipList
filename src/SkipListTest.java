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
        sl = new SkipList<String, Rectangle>();
        int randomLevelValue = sl.randomLevel();

        // This returns 0 because the first preset
        // random boolean is `false` which breaks
        // the `while condition inside the `randomLevel` method
        int expectedLevelValue = 0;

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
        sl = new SkipList<String, Rectangle>();
        int randomLevelValue = sl.randomLevel();

        // This returns 4 because the fourth preset
        // random boolean is `false` which breaks
        // the `while condition inside the `randomLevel` method
        int expectedLevelValue = 3;

        // Compare the values
        assertEquals(expectedLevelValue, randomLevelValue);
    }

    // TODO: implement more tests

}
