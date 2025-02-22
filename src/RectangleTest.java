
import student.TestCase;

/**
 * This class tests the methods of Rectangle class,
 * ensuring that they work as they should.
 * 
 * @author <your_name>
 * @version <version_no>
 */
public class RectangleTest extends TestCase {
    private Rectangle rec;

    /**
     * Initializes a rectangle object to be used for the tests.
     */
    public void setUp() {
        rec = new Rectangle(0, 0, 5, 5);
    }


    /**
     * Test the get x coord method
     */
    public void testGetXCoordinate() {
        assertEquals(0, rec.getxCoordinate());
    }


    /**
     * Tests the gety method
     */
    public void testGetYCoordinate() {
        assertEquals(0, rec.getyCoordinate());
    }


    /**
     * tests get width method
     */
    public void testGetWidth() {
        assertEquals(5, rec.getWidth());
    }


    /**
     * tests the get height method
     */
    public void testGetHeight() {
        assertEquals(5, rec.getHeight());
    }


    /**
     * tests all 16 conditions of the intersects method
     */
    public void testIntersect() {
        Rectangle r1 = new Rectangle(2, 2, 3, 3); // Overlaps fully
        Rectangle r2 = new Rectangle(6, 2, 3, 3); // Left fails
        Rectangle r3 = new Rectangle(-6, 2, 3, 3); // Right fails
        Rectangle r4 = new Rectangle(2, 6, 3, 3); // Top fails
        Rectangle r5 = new Rectangle(2, -6, 3, 3); // Bottom fails
        Rectangle r6 = new Rectangle(6, 6, 3, 3); // Left & Right fail
        Rectangle r7 = new Rectangle(-6, 6, 3, 3); // Right & Top fail
        Rectangle r8 = new Rectangle(-6, -6, 3, 3); // Right & Bottom fail
        Rectangle r9 = new Rectangle(6, -6, 3, 3); // Left & Top fail
        Rectangle r10 = new Rectangle(6, 2, 3, 3); // Left & Bottom fail
        Rectangle r11 = new Rectangle(2, -6, 3, 3); // Top & Bottom fail
        Rectangle r12 = new Rectangle(6, 6, 3, 3); // Left, Right, Top fail
        Rectangle r13 = new Rectangle(6, 6, 3, 3); // Left, Right, Bottom fail
        Rectangle r14 = new Rectangle(6, -6, 3, 3); // Left, Top, Bottom fail
        Rectangle r15 = new Rectangle(-6, -6, 3, 3); // Right, Top, Bottom fail
        Rectangle r16 = new Rectangle(6, 6, 3, 3); // No intersection at all

        assertEquals(true, rec.intersect(r1));
        assertEquals(false, rec.intersect(r2));
        assertEquals(false, rec.intersect(r3));
        assertEquals(false, rec.intersect(r4));
        assertEquals(false, rec.intersect(r5));
        assertEquals(false, rec.intersect(r6));
        assertEquals(false, rec.intersect(r7));
        assertEquals(false, rec.intersect(r8));
        assertEquals(false, rec.intersect(r9));
        assertEquals(false, rec.intersect(r10));
        assertEquals(false, rec.intersect(r11));
        assertEquals(false, rec.intersect(r12));
        assertEquals(false, rec.intersect(r13));
        assertEquals(false, rec.intersect(r14));
        assertEquals(false, rec.intersect(r15));
        assertEquals(false, rec.intersect(r16));

        Rectangle r17 = new Rectangle(5, 5, 10, 10); // Intersects a corner,
                                                     // should be false
        assertEquals(false, rec.intersect(r17));

        Rectangle r18 = new Rectangle(4, 4, 10, 10); // Intersects a bit more so
                                                     // should be true
        assertEquals(true, rec.intersect(r18));
    }


    /**
     * tests all 16 conditions of the equals method
     */
    public void testEquals() {
        Object obj = new Object();
        assertFalse(rec.equals(obj));
        Rectangle r1 = new Rectangle(0, 0, 5, 5); // All conditions true
        Rectangle r2 = new Rectangle(1, 0, 5, 5); // x false, others true
        Rectangle r3 = new Rectangle(0, 1, 5, 5); // y false, others true
        Rectangle r4 = new Rectangle(0, 0, 6, 5); // width false, others true
        Rectangle r5 = new Rectangle(0, 0, 5, 6); // height false, others true
        Rectangle r6 = new Rectangle(1, 1, 5, 5); // x and y false, others true
        Rectangle r7 = new Rectangle(1, 0, 6, 5); // x and width false, others
                                                  // true
        Rectangle r8 = new Rectangle(1, 0, 5, 6); // x and height false, others
                                                  // true
        Rectangle r9 = new Rectangle(0, 1, 6, 5); // y and width false, others
                                                  // true
        Rectangle r10 = new Rectangle(0, 1, 5, 6); // y and height false, others
                                                   // true
        Rectangle r11 = new Rectangle(0, 0, 6, 6); // width and height false,
                                                   // others true
        Rectangle r12 = new Rectangle(1, 1, 6, 5); // x, y, and width false
        Rectangle r13 = new Rectangle(1, 1, 5, 6); // x, y, and height false
        Rectangle r14 = new Rectangle(1, 0, 6, 6); // x, width, and height false
        Rectangle r15 = new Rectangle(0, 1, 6, 6); // y, width, and height false
        Rectangle r16 = new Rectangle(1, 1, 6, 6); // All conditions false
        assertTrue(rec.equals(r1));
        assertFalse(rec.equals(r2));
        assertFalse(rec.equals(r3));
        assertFalse(rec.equals(r4));
        assertFalse(rec.equals(r5));
        assertFalse(rec.equals(r6));
        assertFalse(rec.equals(r7));
        assertFalse(rec.equals(r8));
        assertFalse(rec.equals(r9));
        assertFalse(rec.equals(r10));
        assertFalse(rec.equals(r11));
        assertFalse(rec.equals(r12));
        assertFalse(rec.equals(r13));
        assertFalse(rec.equals(r14));
        assertFalse(rec.equals(r15));
        assertFalse(rec.equals(r16));
    }


    /**
     * Tests the to string method
     */
    public void testToString() {
        String expected = "0, 0, 5, 5";
        assertEquals(expected, rec.toString());
    }


    /**
     * tests all 16 conditions of isInvalid
     */
    public void testIsInvalid() {
        Rectangle r1 = new Rectangle(1100, 1100, 50, 50); // All conditions true
        Rectangle r2 = new Rectangle(512, 1100, 600, 50); // x valid, y invalid,
                                                          // x+width invalid,
                                                          // y+height invalid
        Rectangle r3 = new Rectangle(-100, 512, 600, 50); // x invalid, y valid,
                                                          // x+width invalid,
                                                          // y+height invalid
        Rectangle r4 = new Rectangle(1100, 1100, 50, 10); // x invalid, y
                                                          // invalid, x+width
                                                          // valid, y+height
                                                          // invalid
        Rectangle r5 = new Rectangle(1100, 1100, 10, 50); // x invalid, y
                                                          // invalid, x+width
                                                          // invalid, y+height
                                                          // valid
        Rectangle r6 = new Rectangle(512, 512, 600, 600); // x valid, y valid,
                                                          // x+width invalid,
                                                          // y+height invalid
        Rectangle r7 = new Rectangle(-100, 512, 50, 600); // x invalid, y valid,
                                                          // x+width valid,
                                                          // y+height invalid
        Rectangle r8 = new Rectangle(-100, 512, 600, 50); // x invalid, y valid,
                                                          // x+width invalid,
                                                          // y+height valid
        Rectangle r9 = new Rectangle(512, 1100, 50, 600); // x valid, y invalid,
                                                          // x+width valid,
                                                          // y+height invalid
        Rectangle r10 = new Rectangle(512, 1100, 600, 50); // x valid, y
                                                           // invalid, x+width
                                                           // invalid, y+height
                                                           // valid
        Rectangle r11 = new Rectangle(512, 512, 50, 600); // x valid, y valid,
                                                          // x+width valid,
                                                          // y+height invalid
        Rectangle r12 = new Rectangle(512, 512, 600, 50); // x valid, y valid,
                                                          // x+width invalid,
                                                          // y+height valid
        Rectangle r13 = new Rectangle(512, 1100, 50, 50); // x valid, y invalid,
                                                          // x+width valid,
                                                          // y+height valid
        Rectangle r14 = new Rectangle(-100, 512, 50, 50); // x invalid, y valid,
                                                          // x+width valid,
                                                          // y+height valid
        Rectangle r15 = new Rectangle(1100, 1100, 50, 50); // x invalid, y
                                                           // invalid, x+width
                                                           // valid, y+height
                                                           // valid
        Rectangle r16 = new Rectangle(512, 512, 100, 100); // All conditions
                                                           // false (Valid
                                                           // Rectangle)

        assertTrue(r1.isInvalid());
        assertTrue(r2.isInvalid());
        assertTrue(r3.isInvalid());
        assertTrue(r4.isInvalid());
        assertTrue(r5.isInvalid());
        assertTrue(r6.isInvalid());
        assertTrue(r7.isInvalid());
        assertTrue(r8.isInvalid());
        assertTrue(r9.isInvalid());
        assertTrue(r10.isInvalid());
        assertTrue(r11.isInvalid());
        assertTrue(r12.isInvalid());
        assertTrue(r13.isInvalid());
        assertTrue(r14.isInvalid());
        assertTrue(r15.isInvalid());
        assertFalse(r16.isInvalid());

        // add 4 more rectangles to test widths of 0 and negative widths and
        // heights
        Rectangle r17 = new Rectangle(0, 0, 0, 5);
        Rectangle r18 = new Rectangle(0, 0, 1, 0);
        Rectangle r19 = new Rectangle(0, 0, -1, 5);
        Rectangle r20 = new Rectangle(0, 0, 5, -1);

        assertTrue(r17.isInvalid());
        assertTrue(r18.isInvalid());
        assertTrue(r19.isInvalid());
        assertTrue(r20.isInvalid());

        // some more tests to try and pass the mutati
        // valid x coord invalid y
        Rectangle r21 = new Rectangle(0, 1025, 0, 5);
        Rectangle r22 = new Rectangle(0, -1, 0, 5);
        assertTrue(r21.isInvalid());
        assertTrue(r22.isInvalid());

        // invalid x in both ways
        Rectangle r23 = new Rectangle(-1, 1000, 0, 5);
        Rectangle r24 = new Rectangle(1025, 1000, 0, 5);
        assertTrue(r23.isInvalid());
        assertTrue(r24.isInvalid());
    }


    /**
     * Tests the is valid region command checking to make sure
     * all conditions are tested
     */
    public void testIsValidRegion() {
        // Test valid rectangle
        Rectangle r1 = new Rectangle(100, 100, 50, 50); // Valid region
        // Test invalid because xCoordinate is greater than 1024
        Rectangle r2 = new Rectangle(1100, 100, 50, 50); // Invalid due to
                                                         // xCoordinate > 1024
        // Test invalid because yCoordinate is greater than 1024
        Rectangle r3 = new Rectangle(100, 1100, 50, 50); // Invalid due to
                                                         // yCoordinate > 1024
        // Test invalid because width is less than or equal to 0
        Rectangle r4 = new Rectangle(100, 100, -50, 50); // Invalid due to width
                                                         // <= 0
        // Test invalid because height is less than or equal to 0
        Rectangle r5 = new Rectangle(100, 100, 50, -50); // Invalid due to
                                                         // height <= 0
        // Test invalid because xCoordinate + width is less than or equal to 0
        Rectangle r6 = new Rectangle(-100, 100, 50, 50); // Invalid due to
                                                         // xCoordinate + width
                                                         // <= 0
        // Test invalid because yCoordinate + height is less than or equal to 0
        Rectangle r7 = new Rectangle(100, -100, 50, 50); // Invalid due to
                                                         // yCoordinate + height
                                                         // <= 0
        // Test invalid because xCoordinate + width is exactly 0
        Rectangle r8 = new Rectangle(-50, 100, 50, 50); // Invalid due to
                                                        // xCoordinate + width
                                                        // == 0
        // Test invalid because yCoordinate + height is exactly 0
        Rectangle r9 = new Rectangle(100, -50, 50, 50); // Invalid due to
                                                        // yCoordinate + height
                                                        // == 0
        // Test invalid because both width and height are less than or equal to
        // 0
        Rectangle r10 = new Rectangle(100, 100, 0, 0); // Invalid due to width
                                                       // and height <= 0
        // Test invalid due to negative width and height
        Rectangle r11 = new Rectangle(100, 100, -1, -1); // Invalid due to
                                                         // negative width and
                                                         // height
        // Test invalid because xCoordinate and width are valid but yCoordinate
        // + height is invalid
        Rectangle r12 = new Rectangle(100, 1025, 50, 50); // Invalid due to
                                                          // yCoordinate > 1024

        // Test all invalid and valid rectangles
        assertTrue(r1.isValidRegion()); // Valid rectangle
        assertFalse(r2.isValidRegion()); // Invalid due to xCoordinate > 1024
        assertFalse(r3.isValidRegion()); // Invalid due to yCoordinate > 1024
        assertFalse(r4.isValidRegion()); // Invalid due to width <= 0
        assertFalse(r5.isValidRegion()); // Invalid due to height <= 0
        assertFalse(r6.isValidRegion()); // Invalid due to xCoordinate + width
                                         // <= 0
        assertFalse(r7.isValidRegion()); // Invalid due to yCoordinate + height
                                         // <= 0
        assertFalse(r8.isValidRegion()); // Invalid due to xCoordinate + width
                                         // == 0
        assertFalse(r9.isValidRegion()); // Invalid due to yCoordinate + height
                                         // == 0
        assertFalse(r10.isValidRegion()); // Invalid due to width and height <=
                                          // 0
        assertFalse(r11.isValidRegion()); // Invalid due to negative width and
                                          // height
        assertFalse(r12.isValidRegion()); // Invalid due to yCoordinate > 1024
    }

}
