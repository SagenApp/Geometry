package app.sagen.geometry;

/**
 * This class is a representation of a collidable rectangle.
 *
 * @author Sagen
 */
public class AABB2D {

    /*
     * The x location
     */
    private int x;

    /*
     * The y location
     */
    private int y;

    /*
     * The width of the AABB
     */
    private int w;

    /*
     * The height of the AABB
     */
    private int h;

    /**
     * Creates a new instance of the class
     *
     * @param x The position x
     * @param y The position y
     * @param w The width
     * @param h The height
     */
    public AABB2D(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * Updates the size and position with new values
     *
     * @param x The position x
     * @param y The position y
     * @param w The width
     * @param h The height
     */
    public void setSizeAndPosition(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * Updates the position
     *
     * @param x The position x
     * @param y The position y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Updates the size
     *
     * @param w The width
     * @param h The height
     */
    public void setSize(int w, int h) {
        this.w = w;
        this.h = h;
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
     * Calculates if the tho AABB's are intersecting
     *
     * @param other The other AABB
     * @return True if they intersect, false if not.
     */
    public boolean isIntersecting(AABB2D other) {
        // check if size is less then or equal to 0
        if (other.w <= 0 || other.h <= 0 || this.w <= 0 || this.h <= 0) return false;

        // calculate maximum x and y
        int otherMaxX = other.w + other.x;
        int otherMaxY = other.h + other.y;
        int thisMaxX = this.w + this.x;
        int thisMaxY = this.h + this.y;

        // test for intersection
        return ((otherMaxX < other.x || otherMaxX > this.x) &&
                (otherMaxY < other.y || otherMaxY > this.y) &&
                (thisMaxX < this.x || thisMaxX > other.x) &&
                (thisMaxY < this.y || thisMaxY > other.y));
    }

    /**
     * Calculates the intersection between two AABB's
     *
     * @param other The other AABB
     * @return The intersection
     */
    public AABB2D getIntersection(AABB2D other) {
        // define a rectangle defined by this AABB
        int thisMinX = this.x;
        int thisMinY = this.y;
        int thisMaxX = thisMinX + this.w;
        int thisMaxY = thisMinY + this.h;

        // define the maximum values for the other AABB
        int otherMaxX = other.x + other.w;
        int otherMaxY = other.y + other.h;

        // calculate the intersection by finding the biggest minimum and smallest maximum
        if (thisMinX < other.x) thisMinX = other.x;
        if (thisMinY < other.y) thisMinY = other.y;
        if (thisMaxX > otherMaxX) thisMaxX = otherMaxX;
        if (thisMaxY > otherMaxY) thisMaxY = otherMaxY;

        // subrtact the position
        thisMaxX -= thisMinX;
        thisMaxY -= thisMinY;

        // return a new AABB representing the overlap.
        return new AABB2D(thisMinX, thisMinY, thisMaxX, thisMaxY);
    }

    /**
     * Creates a string representation of this class
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return "AABB2D[x=" + x + ", y=" + y + ", width=" + w + ", height=" + h + "]";
    }
}
