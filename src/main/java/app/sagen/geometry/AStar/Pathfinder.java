package app.sagen.geometry.AStar;

import app.sagen.geometry.MutableVector2D;

import java.util.ArrayList;

/**
 * The pathfinder class uses the A* algorithm to find the shortest path between two tiles.<br>
 * See https://en.wikipedia.org/wiki/A*_search_algorithm
 *
 * @author Sagen
 */
public class Pathfinder {

    /**
     * The closed set as described on wikipedia<br>
     * See https://en.wikipedia.org/wiki/A*_search_algorithm
     */
    private ArrayList<Node> closed = new ArrayList<>();

    /**
     * The open set as described on wikipedia<br>
     * See https://en.wikipedia.org/wiki/A*_search_algorithm
     */
    private NodeList open = new NodeList();

    /**
     * The TileMap used
     */
    private TileMap map;

    /**
     * The map of nodes
     */
    private Node[][] nodes;

    /**
     * Creates a new instance of the class
     *
     * @param map The TileMap to use
     */
    public Pathfinder(TileMap map) {
        this.map = map;

        // Create a new nodeMap with nodes
        nodes = new Node[map.getWidth()][map.getHeight()];
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                nodes[x][y] = new Node(x, y);
            }
        }
    }

    /**
     * Calculates a path between two entities
     *
     * @param entity            The entity source
     * @param toEntity          The entity destination
     * @param maxSearchDistance The max distance to search for a path
     * @return The path
     */
    public ArrayList<MutableVector2D> findPath(TileEntity entity, TileEntity toEntity, int maxSearchDistance) {
        return findPath(entity, entity.getTileX(), entity.getTileY(), toEntity.getTileX(), toEntity.getTileY(), maxSearchDistance);
    }

    /**
     * Calculates a path between two positions for an entity
     *
     * @param entity            The entity to find a path for
     * @param sx                The source x
     * @param sy                The source y
     * @param tx                The target x
     * @param ty                The target y
     * @param maxSearchDistance The max distance to search for a path
     * @return The path
     */
    public ArrayList<MutableVector2D> findPath(TileEntity entity, int sx, int sy, int tx, int ty, int maxSearchDistance) {
        // Ignore sources outside the tileMap
        if (sx < 0 || sy < 0 || sx >= nodes.length || sy >= nodes[0].length) {
            return null;
        }

        // Ignore targets outside the tileMap
        if (tx < 0 || ty < 0 || tx >= nodes.length || ty >= nodes[0].length) {
            return null;
        }

        // Ignore if target is solid
        if (!map.isTraversable(entity, tx, ty)) {
            return null;
        }

        // Initialize the source node
        nodes[sx][sy].g = 0;
        nodes[sx][sy].depth = 0;

        // Initialize the open and closed set
        closed.clear();
        open.clear();
        open.add(nodes[sx][sy]);

        // Initialize the target node
        nodes[tx][ty].parent = null;

        int maxDepth = 0;
        while ((maxDepth < maxSearchDistance) && (open.size() != 0)) {
            Node current = (Node) open.first();

            // Found our target, break
            if (current == nodes[tx][ty]) {
                break;
            }

            // Move the node to the closed set
            open.remove(current);
            closed.add(current);

            // loop through every nearby tile
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {

                    // Ignore if the same node
                    if ((x == 0) && (y == 0)) {
                        continue;
                    }

                    // Ignore if diagonal
                    if ((x != 0) && (y != 0)) {
                        continue;
                    }

                    // Calculate coordinates of the next node
                    int xp = x + current.x;
                    int yp = y + current.y;

                    // Ignore if the next node is outside of the tileMap
                    if ((xp < 0) || (yp < 0) || (xp >= map.getWidth()) || (yp >= map.getHeight()))
                        continue;

                    // Ignore if the next node is solid
                    if (!map.isTraversable(entity, xp, yp)) continue;

                    // Calculate the cost of the next node
                    float g = current.g + map.getMovementCost(entity, current.x, current.y, xp, yp);

                    Node neighbour = nodes[xp][yp];

                    // If the newly calculated cost of this node is less than the old cost
                    // Remove the node from both sets
                    if (g < neighbour.g) {
                        open.remove(neighbour);
                        closed.remove(neighbour);
                    }

                    // If the next node is not in either of the sets
                    // Calculate new values for it and add it to the open set
                    if (!open.contains(neighbour) && !(closed.contains(neighbour))) {
                        // Set the cost of the node to the newly calculated cost
                        neighbour.g = g;

                        // Heuristics calculated by finding the shortest possible path to the target from the node
                        // h = sqrt(dx^2 + dy^2)
                        neighbour.h = (float) (Math.sqrt(Math.pow(tx - xp, 2) + Math.pow(ty - yp, 2)));

                        // Calculate new maxDepth and set the parent of the next node
                        maxDepth = Math.max(maxDepth, neighbour.setParent(current));

                        // Add to the open set
                        open.add(neighbour);
                    }
                }
            }
        }

        // If the target node has no parent, the algorithm could not find a path
        if (nodes[tx][ty].parent == null) {
            return null;
        }

        // Add the nodes to an arrayList
        ArrayList<MutableVector2D> tilePath = new ArrayList<>();
        Node target = nodes[tx][ty];
        while (target != nodes[sx][sy]) {
            tilePath.add(new MutableVector2D(target.x, target.y));
            target = target.parent;
        }
        tilePath.add(new MutableVector2D(sx, sy));

        // Calculate point between every node for a smoother path
        ArrayList<MutableVector2D> path = new ArrayList<>();

        for (int i = 0; i < tilePath.size() - 1; i++) {
            MutableVector2D current = tilePath.get(i);
            MutableVector2D next = tilePath.get(i + 1);
            MutableVector2D mid = current.clone().sub(current.clone().sub(next).div(2)).add(.5, .5);
            path.add(mid);
        }

        // Return the path
        return path;
    }
}
