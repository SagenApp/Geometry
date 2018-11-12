package app.sagen.geometry;

/**
 * This class is a representation of a collidable rectangle.
 *
 * @author Sagen
 */
public class AABB3D {

    /*
     * The x location
     */
    private int x;

    /*
     * The y location
     */
    private int y;

    /*
     * The z location
     */
    private int z;

    /*
     * The width of the AABB
     */
    private int w;

    /*
     * The height of the AABB
     */
    private int h;

    /*
     * The depth of the AABB
     */
    private int d;

    /**
     * Creates a new instance of the class
     *
     * @param x The position x
     * @param y The position y
     * @param z The position z
     * @param w The width
     * @param h The height
     * @param d The depth
     */
    public AABB3D(int x, int y, int z, int w, int h, int d) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.d = d;
    }

    /**
     * Updates the size and position with new values
     *
     * @param x The position x
     * @param y The position y
     * @param z The position z
     * @param w The width
     * @param h The height
     * @param d The depth
     */
    public void setSizeAndPosition(int x, int y, int z, int w, int h, int d) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.d = d;
    }

    /**
     * Updates the position
     *
     * @param x The position x
     * @param y The position y
     * @param z The position z
     */
    public void setPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Updates the size
     *
     * @param w The width
     * @param h The height
     * @param d The depth
     */
    public void setSize(int w, int h, int d) {
        this.w = w;
        this.h = h;
        this.d = d;
    }

    /**
     * Gets the position x
     *
     * @return The x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the position y
     *
     * @return The y
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the position z
     *
     * @return The z
     */
    public int getZ() {
        return z;
    }

    /**
     * Gets the width
     *
     * @return The width
     */
    public int getWidth() {
        return w;
    }

    /**
     * Gets the height
     *
     * @return The height
     */
    public int getHeight() {
        return h;
    }

    /**
     * Gets the depth
     *
     * @return The depth
     */
    public int getDepth() {
        return d;
    }

    /**
     * Calculates if the tho AABB's are intersecting
     *
     * @param other The other AABB
     * @return True if they intersect, false if not.
     */
    public boolean isIntersecting(AABB3D other) {
        // check if size is less then or equal to 0
        if (other.w <= 0 || other.h <= 0 || other.d <= 0 || this.w <= 0 || this.h <= 0 || this.d <= 0) return false;

        // calculate maximum x and y
        int otherMaxX = other.w + other.x;
        int otherMaxY = other.h + other.y;
        int otherMaxZ = other.d + other.z;
        int thisMaxX = this.w + this.x;
        int thisMaxY = this.h + this.y;
        int thisMaxZ = this.d + this.z;

        // test for intersection
        return ((otherMaxX < other.x || otherMaxX > this.x) &&
                (otherMaxY < other.y || otherMaxY > this.y) &&
                (otherMaxZ < other.z || otherMaxZ > this.z) &&
                (thisMaxX < this.x || thisMaxX > other.x) &&
                (thisMaxY < this.y || thisMaxY > other.y) &&
                (thisMaxZ < this.z || thisMaxZ > other.z));
    }

    /**
     * Calculates the intersection between two AABB's
     *
     * @param other The other AABB
     * @return The intersection
     */
    public AABB3D getIntersection(AABB3D other) {
        // define a rectangle defined by this AABB
        int thisMinX = this.x;
        int thisMinY = this.y;
        int thisMinZ = this.z;
        int thisMaxX = thisMinX + this.w;
        int thisMaxY = thisMinY + this.h;
        int thisMaxZ = thisMinZ + this.d;

        // define the maximum values for the other AABB
        int otherMaxX = other.x + other.w;
        int otherMaxY = other.y + other.h;
        int otherMaxZ = other.z + other.d;

        // calculate the intersection by finding the biggest minimum and smallest maximum
        if (thisMinX < other.x) thisMinX = other.x;
        if (thisMinY < other.y) thisMinY = other.y;
        if (thisMinZ < other.z) thisMinZ = other.z;
        if (thisMaxX > otherMaxX) thisMaxX = otherMaxX;
        if (thisMaxY > otherMaxY) thisMaxY = otherMaxY;
        if (thisMaxZ > otherMaxZ) thisMaxZ = otherMaxZ;

        // subrtact the position
        thisMaxX -= thisMinX;
        thisMaxY -= thisMinY;
        thisMaxZ -= thisMinZ;

        // return a new AABB representing the overlap.
        return new AABB3D(thisMinX, thisMinY, thisMinZ, thisMaxX, thisMaxY, thisMaxZ);
    }

    /**
     * Creates a string representation of this class
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return "AABB3D[x=" + x + ", y=" + y + ", z=" + z + ", width=" + w + ", height=" + h + ", depth=" + d + "]";
    }
}
