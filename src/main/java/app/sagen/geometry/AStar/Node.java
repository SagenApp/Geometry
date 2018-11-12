package app.sagen.geometry.AStar;

/**
 * This class represents a node for the Pathfinder. Each node has a position, a parent, a cost and distance from source.
 *
 * @author Sagen
 */
public class Node implements Comparable<Node> {

    /**
     * The tile position x
     */
    public int x;

    /**
     * The tile position y
     */
    public int y;

    /**
     * The parent of this node
     */
    public Node parent;

    /**
     * The cost of this node
     */
    public float g;

    /**
     * The heuristics of this node
     */
    public float h;

    /**
     * The depth of this node
     * The depth should be equal to the number of parents.
     */
    public int depth;

    /**
     * Creates a new node with a given position
     *
     * @param x The pos x
     * @param y The pos y
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the parent of this node and calculates a new depth
     *
     * @param parent The new paren
     * @return The new depth
     */
    public int setParent(Node parent) {
        depth = parent.depth + 1;
        this.parent = parent;
        return depth;
    }

    /**
     * Compares this node with another node.
     * Calculates f = h + g for both nodes and compares the result.
     *
     * @param other The other node
     * @return -1, 0 or 1 as result of the comparison
     */
    @Override
    public int compareTo(Node other) {
        float f = h + g;
        float of = other.h + other.g;

        if (f < of) {
            return -1;
        } else if (f > of) {
            return 1;
        } else {
            return 0;
        }
    }
}
