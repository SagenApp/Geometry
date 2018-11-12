package app.sagen.geometry.AStar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple implementation of a sorted map. Calls <code>Collections#sort</code> after every insertion
 */
public class NodeList {

    /*
     * The list of nodes to be sorted
     */
    private ArrayList<Node> list = new ArrayList<>();

    /**
     * The first node in this list
     *
     * @return The first element
     */
    public Node first() {
        return list.get(0);
    }

    /**
     * Clears the list
     */
    public void clear() {
        list.clear();
    }

    /**
     * Adds a node to the list
     *
     * @param o The node to add
     */
    public void add(Node o) {
        list.add(o);
        Collections.sort(list);
    }

    /**
     * Removes a node from the list
     *
     * @param o The node to remove
     */
    public void remove(Node o) {
        list.remove(o);
    }

    /**
     * Calculates the size of the list
     *
     * @return The size
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns true if the list contains the node, false if not.
     *
     * @param o The node to check for
     * @return True if the list contains the node, false if not.
     */
    public boolean contains(Node o) {
        return list.contains(o);
    }
}
