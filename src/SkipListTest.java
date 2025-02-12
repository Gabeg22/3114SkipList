import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
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


    /***
     * Example 2: Test `randomLevel` method with
     * predetermined random values using `TestableRandom`
     */
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


    /**
     * Test insert()
     */
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
        search = sl.search("u");
        assert (search.isEmpty());
    }


    public void testRemove() {
        Rectangle test = new Rectangle(1, 0, 2, 4);
        KVPair<String, Rectangle> pair = new KVPair<>("a", test);
        sl.insert(pair);
        assertEquals(1, sl.size());
        assertEquals(pair, sl.remove("a"));
        assertEquals(0, sl.size());

        assertNull(sl.remove("b"));
    }

    /**
     * Test remove() with multiple elements and duplicate keys
     */
    public void testRemoveMultiple() {
        Rectangle test1 = new Rectangle(1, 0, 2, 4);
        Rectangle test2 = new Rectangle(2, 0, 4, 8);
        KVPair<String, Rectangle> pair1 = new KVPair<>("a", test1);
        KVPair<String, Rectangle> pair2 = new KVPair<>("a", test2);
        sl.insert(pair1);
        sl.insert(pair2);
        assertEquals(2, sl.size());
        assertNotNull(sl.remove("a"));
        assertEquals(1, sl.size());
    }

    /**
     * Test removeByValue() with existing and non-existing values
     */
    public void testRemoveByValue() {
        Rectangle test = new Rectangle(1, 0, 2, 4);
        KVPair<String, Rectangle> pair = new KVPair<>("a", test);
        sl.insert(pair);
        assertEquals(1, sl.size());
        assertEquals(pair, sl.removeByValue(test));
        assertEquals(0, sl.size());

        assertNull(sl.removeByValue(new Rectangle(2, 0, 4, 8)));
    }

    /**
     * Test removeByValue() with multiple matching values
     */
    public void testRemoveByValueMultiple() {
        Rectangle test = new Rectangle(1, 0, 2, 4);
        KVPair<String, Rectangle> pair1 = new KVPair<>("a", test);
        KVPair<String, Rectangle> pair2 = new KVPair<>("b", test);
        sl.insert(pair1);
        sl.insert(pair2);
        assertEquals(2, sl.size());
        assertNotNull(sl.removeByValue(test));
        assertEquals(1, sl.size());
    }

    /**
     * Test remove() with invalid inputs
     */
    public void testRemoveInvalid() {
        assertNull(sl.remove("c"));
        Rectangle invalidRect = new Rectangle(-1, -1, 2, 4);
        assertNull(sl.removeByValue(invalidRect));
    }


    /**
     * Test iterator on an empty SkipList.
     */
    public void testEmptyIterator() {
        Iterator<KVPair<String, Rectangle>> iterator = sl.iterator();
        assertFalse("Iterator should not have next on an empty list", iterator
            .hasNext());
    }


    /**
     * Test iterator with a single element.
     */
    public void testSingleElementIterator() {
        sl.insert(new KVPair<>("A", new Rectangle(1, 1, 2, 2)));
        Iterator<KVPair<String, Rectangle>> iterator = sl.iterator();

        assertTrue("Iterator should have next element", iterator.hasNext());
        KVPair<String, Rectangle> pair = iterator.next();
        assertEquals("A", pair.getKey());
        assertFalse("Iterator should not have more elements", iterator
            .hasNext());
    }


    /**
     * Test iterator with multiple elements.
     */
    public void testMultipleElementsIterator() {
        sl.insert(new KVPair<>("A", new Rectangle(1, 1, 2, 2)));
        sl.insert(new KVPair<>("B", new Rectangle(2, 2, 3, 3)));
        sl.insert(new KVPair<>("C", new Rectangle(3, 3, 4, 4)));

        Iterator<KVPair<String, Rectangle>> iterator = sl.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next().getKey());

        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next().getKey());

        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next().getKey());

        assertFalse("Iterator should be empty now", iterator.hasNext());
    }


    /**
     * Test `next()` on an empty iterator (should throw NoSuchElementException).
     */
    public void testNextOnEmptyIterator() {
        Iterator<KVPair<String, Rectangle>> iterator = sl.iterator();
        try {
            iterator.next();
            fail("Expected NoSuchElementException");
        }
        catch (NoSuchElementException e) {
            // Expected exception
        }
    }


    /**
     * Test `next()` when called more times than elements present.
     */
    public void testNextBeyondEnd() {
        sl.insert(new KVPair<>("X", new Rectangle(5, 5, 6, 6)));
        Iterator<KVPair<String, Rectangle>> iterator = sl.iterator();

        assertEquals("X", iterator.next().getKey());
        try {
            iterator.next(); // Should throw NoSuchElementException
            fail("Expected NoSuchElementException");
        }
        catch (NoSuchElementException e) {
            // Expected exception
        }
    }

}
