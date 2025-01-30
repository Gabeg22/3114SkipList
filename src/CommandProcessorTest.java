import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the CommandProcessor class.
 * Test each possible command on its bounds,
 * if applicable to ensure they work properly.
 * Also test passing improper command to ensure
 * all class functionalities work as intended.
 * 
 * @author <Gabriel Gonzalez, Danal Mahmoudi>
 * @version <version_1>
 */
public class CommandProcessorTest extends TestCase {

    private CommandProcessor proc;

    /**
     * The setUp() method will be called automatically before
     * each test and reset whatever the test modified. For this
     * test class, only a new database object is needed, so
     * create a database here for use in each test case.
     */
    public void setUp() {
        proc = new CommandProcessor();
    }


    /**
     * Tests the parsing of the insert command
     * uses invalid insertions to ensure the rectangle
     * class is created correctly
     */
    public void testInsertCom() {
        // String arg1 = "insert r2 15 15 5 5";
    }


    /**
     * Tests the remove command
     * using just name and also the rectangle
     * coordinates
     */
    public void testRemoveCom() {

    }


    /**
     * test the region search method using
     * the rectangle coordinats
     */
    public void testRegionSearch() {

    }


    /**
     * Tests the instersections command
     * this will call the database intersections
     */
    public void testIntersectionsCom() {

    }


    /**
     * Tests the search method using the name of the
     * rectangle
     */
    public void testSearch() {

    }


}
