import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author CS Staff
 * 
 * @version 2024-01-22
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Rectangle> list;

    // This is an Iterator object over the SkipList to loop through it from
    // outside the class.
    // You will need to define an extra Iterator for the intersections method.
    private Iterator<KVPair<String, Rectangle>> itr1;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * add the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {
        // Delegates the decision mostly to SkipList, only
        // writing the correct message to the console from
        // that
        if (!pair.getValue().isInvalid()) {
            list.insert(pair);
            System.out.println("Rectangle inserted: (" + pair.getKey() + ", "
                + pair.getValue().toString() + ")");
        }
        else {
            System.out.println("Rectangle rejected: (" + pair.getKey() + ", "
                + pair.getValue().toString() + ")");
        }

    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        // remove by key, the key is name
        if (list.remove(name) == null) {
            System.out.println("Rectangle with name " + name
                + " does not exist");
        }
    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {
        Rectangle rec = new Rectangle(x, y, w, h);
        if (list.removeByValue(rec) == null) {
            System.out.println("Rectangle with values x: " + x + " y:" + y
                + " w: " + w + " h:" + h + " does not exist");
        }
    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region.
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        Rectangle rec = new Rectangle(x, y, w, h);
        if (rec.isInvalid()) {
            System.out.println("Rectangle rejected: (" + rec.toString() + ")");
            return;
        }
        Iterator<KVPair<String, Rectangle>> it = list.iterator();
        System.out.println("Rectangles intersecting region (" + rec.toString()
            + "):");
        while (it.hasNext()) {
            KVPair<String, Rectangle> check = it.next(); // returns the
                                                         // rectangle
            if (check.getValue().intersect(rec)) {
                System.out.println("(" + check.getKey() + ", " + check
                    .getValue().toString() + ")");
            }
        }
    }


    /**
     * Prints out all the rectangles that intersect each other. Note that
     * it is better not to implement an intersections method in the SkipList
     * class
     * as the SkipList needs to be agnostic about the fact that it is storing
     * Rectangles.
     */
    public void intersections() {
        // an array list of rectangles
        System.out.println("Intersection pairs: ");
        ArrayList<KVPair<String, Rectangle>> rectangles = new ArrayList<>();

        // add all the rectangles to the original list
        Iterator<KVPair<String, Rectangle>> it = list.iterator();
        while (it.hasNext()) {
            rectangles.add(it.next());
        }

        for (int a = 0; a < rectangles.size(); a++) {
            KVPair<String, Rectangle> outerRectPair = rectangles.get(a);
            Rectangle outerRect = outerRectPair.getValue();

            for (int b = a + 1; b < rectangles.size(); b++) {
                KVPair<String, Rectangle> innerRectPair = rectangles.get(b);
                Rectangle innerRect = innerRectPair.getValue();

                if (innerRect.intersect(outerRect)) {
                    System.out.println(String.format(
                        "(%s, %d, %d, %d, %d | %s, %d, %d, %d, %d)",
                        innerRectPair.getKey(), innerRect.getxCoordinate(),
                        innerRect.getyCoordinate(), innerRect.getWidth(),
                        innerRect.getHeight(), outerRectPair.getKey(), outerRect
                            .getxCoordinate(), outerRect.getyCoordinate(),
                        outerRect.getWidth(), outerRect.getHeight()));

                }
            }
        }
    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        list.search(name);
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        list.dump();
    }

}
