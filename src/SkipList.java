import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import student.TestableRandom;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author CS Staff
 * 
 * @version 2024-01-22
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element (Sentinel Node)
    private int size; // number of entries in the Skip List
    private Random rng;

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
        this.rng = new TestableRandom();
    }


    /**
     * returns a random level (using geometric distribution), minimum of 1
     * 
     * @return returns an int
     */
    // TODO Ideally, you should call this method inside other methods
    // keep this method private. Since, we do not have any methods to call
    // this method at this time, we keep this publicly accessible and testable.

    public int randomLevel() {
        int lev;
        for (lev = 0; Math.abs(rng.nextInt()) % 2 == 0; lev++) {
            // ran is random
        }
        return lev;
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @return returns an arraylist of KVpairs
     * @param key
     *            key to be searched for
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        ArrayList<KVPair<K, V>> results = new ArrayList<>();
        SkipNode x = head; // Dummy header node
        for (int i = x.level; i >= 0; i--) // For each level...
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(key) < 0)) // go forward
                x = x.forward[i]; // Go one last step
        x = x.forward[0]; // Move to actual record, if it exists

        while ((x != null) && (x.element().getKey().compareTo(key) == 0)) {
            results.add(x.element());
            x = x.forward[0];
        }
        return results;
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        K key = it.getKey();
        int newLevel = randomLevel(); // New node's level
        if (newLevel > head.level) // If new node is deeper
            adjustHead(newLevel); // adjust the header
        // Track end of level
        SkipNode[] update = (SkipNode[])Array.newInstance(SkipNode.class,
            head.level + 1);
        SkipNode x = head; // Start at header node
        for (int i = head.level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(key) < 0))
                x = x.forward[i];
            update[i] = x; // Track previous node at this level
        }
        SkipNode newN = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            newN.forward[i] = update[i].forward[i]; // Who newN points to
            update[i].forward[i] = newN; // Who points to newN
        }
        size++; // Increment dictionary size
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    public void adjustHead(int newLevel) {

        SkipNode temp = new SkipNode(null, newLevel);
        for (int i = 0; i <= head.level; i++)
            head.forward[i] = temp.forward[i];
        head = temp;

    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * <<<<<<< HEAD
     * 
     * @param key
     *            the Key to remove
     *            =======
     * @param key
     *            the Key to remove
     *            >>>>>>> main
     * @return returns the removed pair if the pair was valid and null if not
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        SkipNode[] update = (SkipNode[])Array.newInstance(SkipNode.class,
            head.level + 1); // Track nodes to update
        SkipNode x = head;

        for (int i = head.level; i >= 0; i--) { // Traverse to find the node and
                                                // track updates
            while ((x.forward[i] != null) && (x.forward[i].element().getKey()
                .compareTo(key) < 0)) {
                x = x.forward[i];
            }
            update[i] = x;
        }

        x = x.forward[0]; // Move to the target node
        // check to make sure x is not null before accessing otherwise might get
        // a null ptr execept
        if (x == null || x.element().getKey().compareTo(key) != 0) {
            return null; // Key not found
        }
        // If the
        // key is
        // found
        for (int i = 0; i <= head.level; i++) { // Remove references to the
                                                // node
            if (update[i].forward[i] != x) {
                break;
            }
            update[i].forward[i] = x.forward[i]; // Pass the node
        }
        // Decrease
        // levels
        // if
        // necessary
        while (head.level > 0 && head.forward[head.level] == null) {
            head.level--;
        }

        size--;
        return x.element(); // Return the removed element
    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    public KVPair<K, V> removeByValue(V val) {
        SkipNode[] update = (SkipNode[])Array.newInstance(SkipNode.class,
            head.level + 1); // Track nodes to update
        for (int i = 0; i <= head.level; i++)
            update[i] = null;

        SkipNode x = head;
        for (int i = head.level; i >= 0; i--) { // Traverse to find the node and
                                                // track updates
            while ((x.forward[i] != null) && (x.forward[i].element()
                .getValue() == null || x.forward[i].element().getValue().equals(
                    val) == false)) {
                x = x.forward[i];
            }
            update[i] = x;
        }
        x = x.forward[0]; // Move to the target node
        // If the
        // key is
        // found
        if (x != null && x.element().getValue().equals(val)) {
            for (int i = 0; i <= head.level; i++) { // Remove references to the
                                                    // node
                if (update[i].forward[i] != x) {
                    break;
                }
                update[i].forward[i] = x.forward[i]; // Pass the node
            }
            // Decrease
            // levels
            // if
            // necessary
            while (head.level > 0 && head.forward[head.level] == null)
                head.level--;

            size--;
            return x.element(); // Return the removed element
        }
        return null; // If the key is not found
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        SkipNode curr = head; // Start at the first node
        System.out.println("SkipList dump: ");    
        System.out.println("Node with depth " + curr.forward.length +" value null");
        curr = curr.forward[0];
        while (curr != null) {
            // Print the key, value, and the number of pointers
            System.out.println("Node with depth " + curr.forward.length +" value " + curr.element().getValue().toString());

            curr = curr.forward[0]; // Next node
        }
        System.out.println("Skiplist size is " + size);
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // An array of pointers to subsequent nodes
        private SkipNode[] forward;
        // the level of the node
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }
        

    }


    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        public SkipListIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {
            return current.forward[0] != null;
        }


        @Override
        public KVPair<K, V> next() {
            if (!hasNext())
                throw new NoSuchElementException();
            else
                current = current.forward[0];
            return current.element();
        }

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        // TODO Auto-generated method stub
        return new SkipListIterator();
    }

}
