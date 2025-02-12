import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;
import student.TestableRandom;

/**
 * 
 * This class tests the CommandProcessor class.
 * Test each possible command on its bounds,
 * if applicable to ensure they work properly.
 * Also test passing improper command to ensure
 * all class functionalities work as intended.
 * 
 * @author <Gabriel Gonzalez, Danial Mahmoudi>
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
     * tests inputting invalid arguments into the processor
     */
    public void testInvalidArguments() {
        // create an instance of the main class and test it
        String[] args = { "" };
        SkipListProject.main(args);
        String output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Invalid file\r\n", output);
        // no argugments
        String[] args2 = {};
        SkipListProject.main(args2);
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Invalid file. No filename in command line arguments\r\n",
            output);

        // insert file name
        String[] args3 = { "P1test1.txt" };
        SkipListProject.main(args3);
        output = systemOut().getHistory();
        systemOut().clearHistory();
        // split and grab the first line based on the new line characters
        String firstline = output.split("\n")[0];
        assertEquals("SkipList dump: ", firstline);

        // split and grab the last line based on the new line characters
        String lastline = output.split("\n")[output.split("\n").length - 1];
        assertEquals("(r4, 20, 25, 7, 9)", lastline);

        // test empty string
        proc.processor("");
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Unrecognized command.\r\n", output);
    }


    /**
     * Tests the parsing of the insert command
     * uses invalid insertions to ensure the rectangle
     * class is created correctly
     */
    public void testInsertCom() {
        TestableRandom.setNextInts(1);

        String arg1 = "insert r2 15 15 5 5";
        proc.processor(arg1);
        proc.processor("dump");

        String output = systemOut().getHistory();
        systemOut().clearHistory();

        assertEquals("Rectangle inserted: (r2, 15, 15, 5, 5)\r\n"
            + "SkipList dump: \r\n" + "Node with depth 1, value null\r\n"
            + "Node with depth 1, value (r2, 15, 15, 5, 5)\r\n"
            + "Skiplist size is: 1\r\n", output);

        // test insert with improper args

        String arg2 = "insert r2 15 15 5";
        proc.processor(arg2);

        output = systemOut().getHistory();
        systemOut().clearHistory();

        assertEquals("Rectangle rejected: \r\n", output);

        // test invalid insert
        String arg3 = "insert r2 15 15 5 -4";
        proc.processor(arg3);

        output = systemOut().getHistory();
        systemOut().clearHistory();

        assertEquals("Rectangle rejected: (r2, 15, 15, 5, -4)\r\n", output);
    }


    /**
     * Tests the remove command
     * using just name and also the rectangle
     * coordinates
     */
    public void testRemoveCom() {
        String arg1 = "insert r2 15 15 5 5";
        proc.processor(arg1);
        systemOut().clearHistory();

        // test remove by name first
        proc.processor("remove r2");

        String output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Rectangle removed: (r2, 15, 15, 5, 5)\r\n", output);
        // remove by name but not found
        proc.processor("remove r2");
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Rectangle not removed: r2\r\n", output);

        // test remove by value
        proc.processor(arg1); // add the rectangle back
        systemOut().clearHistory();
        proc.processor("remove 15 15 5 5");
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Rectangle removed: (r2, 15, 15, 5, 5)\r\n", output);

        // remove by value non existent
        proc.processor("remove 15 15 5 5");
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Rectangle rejected: 15, 15, 5, 5\r\n", output);

        // test invalid args
        proc.processor("remove 15 15 5 5 5 3");
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("", output);
    }


    /**
     * test the region search method using
     * the rectangle coordinats
     */
    public void testRegionSearch() {
        // test invalid arg
        String arg1 = "insert r2 15 15 5 5";
        proc.processor(arg1);
        systemOut().clearHistory();
        proc.processor("regionsearch");
        String output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("", output);

        proc.processor("regionsearch 0 0 100 100"); // test find it
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Rectangles intersecting region (0, 0, 100, 100):\r\n"
            + "(r2, 15, 15, 5, 5)\r\n", output);

        proc.processor("regionsearch 0 0 1 1"); // test not find it
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Rectangles intersecting region (0, 0, 1, 1):\r\n",
            output);

        // test invalid region
        proc.processor("regionsearch 0 0 -1 -1"); // test not find it
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Rectangle rejected: (0, 0, -1, -1)\r\n", output);

        proc.processor("regionsearch -50 0 10 10"); // test not find it
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Rectangle rejected: (-50, 0, 10, 10)\r\n", output);
    }


    /**
     * Tests the instersections command
     * this will call the database intersections
     */
    public void testIntersectionsCom() {
        // test invalid command
        proc.processor("intersecs");
        String output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Unrecognized command.\r\n", output);

        // test with empty database

        proc.processor("intersections");
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Intersection pairs: \r\n", output);

        String arg1 = "insert r2 15 15 5 5";
        proc.processor(arg1);
        systemOut().clearHistory();

        // test basic command with no intersection
        proc.processor("intersections");
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Intersection pairs: \r\n", output);

        // add intersection
        proc.processor(arg1);
        systemOut().clearHistory();

        proc.processor("intersections");
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Intersection pairs: \r\n"
            + "(r2, 15, 15, 5, 5) | (r2, 15, 15, 5, 5)\r\n", output);

        // test double intersection pairs
        proc.processor("insert r2 0 0 100 100");
        systemOut().clearHistory();

        proc.processor("intersections");
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Intersection pairs: \r\n"
            + "(r2, 0, 0, 100, 100) | (r2, 15, 15, 5, 5)\r\n"
            + "(r2, 0, 0, 100, 100) | (r2, 15, 15, 5, 5)\r\n"
            + "(r2, 15, 15, 5, 5) | (r2, 15, 15, 5, 5)\r\n", output);

        // test with non intersecting rectangles

        proc.processor("insert r2 1000 1000 10 10");
        systemOut().clearHistory();

        proc.processor("intersections");
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Intersection pairs: \r\n"
            + "(r2, 0, 0, 100, 100) | (r2, 15, 15, 5, 5)\r\n"
            + "(r2, 0, 0, 100, 100) | (r2, 15, 15, 5, 5)\r\n"
            + "(r2, 15, 15, 5, 5) | (r2, 15, 15, 5, 5)\r\n", output);
    }


    /**
     * Tests the search method using the name of the
     * rectangle
     */
    public void testSearch() {
        // search for nothing
        proc.processor("search r1");
        String output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Rectangle not found: (r1)\r\n", output);

        // search and find
        proc.processor("insert r1 0 0 5 5");
        systemOut().clearHistory();

        proc.processor("search r1");
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Rectangles found: \r\n" + "(r1, 0, 0, 5, 5)\r\n", output);

        // there is a rectangle but we search for the wrong one
        proc.processor("search r2");
        output = systemOut().getHistory();
        systemOut().clearHistory();
        assertEquals("Rectangle not found: (r2)\r\n", output);

    }

}
