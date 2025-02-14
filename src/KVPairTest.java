
import student.TestCase;

/**
 * This class tests the KVPair class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author CS Staff
 * 
 * @version 2024.1
 */
public class KVPairTest extends TestCase {
    // the private kvpair key for testing
    private KVPair<String, Integer> test;

    /**
     * Sets up the tests for this class
     */
    public void setUp() {
        test = new KVPair<String, Integer>("A", 10);
    }


    /**
     * Test the get key method
     */
    public void testGetKey() {
        assertEquals(test.getKey(), "A");
    }


    /**
     * Test get value method
     */
    public void testGetValue() {
        assertEquals(test.getValue().intValue(), 10);
    }


    /**
     * tests the to string method
     */
    public void testToString() {
        assertEquals(test.toString(), "(A, 10)");
    }

}
