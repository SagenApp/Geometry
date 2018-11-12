package app.sagen.geometry;


/**
 * A class representing a mutable vector.
 *
 * @author Sagen
 */
public class MutableVector2D {

    private double deltaX;
    private double deltaY;

    /**
     * Creates a new null-vector where x = 0, y = 0
     */
    public MutableVector2D() {
        deltaX = deltaY = 0.0;
    }

    /**
     * Creates a new vector instance
     *
     * @param x The x scalar
     * @param y The y scalar
     */
    public MutableVector2D(double x, double y) {
        this.deltaX = x;
        this.deltaY = y;
    }

    /**
     * Returns the length of this vector
     * Calculated with l = sqrt(x * x + y * y)
     *
     * @return The length of the vector
     */
    public double length() {
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Adds another vector to this vector
     *
     * @param other The vector to add
     * @return This vector instance
     */
    public MutableVector2D add(MutableVector2D other) {
        deltaX = deltaX + other.deltaX;
        deltaY = deltaY + other.deltaY;
        return this;
    }

    /**
     * Adds a number to the x and y scalar
     *
     * @param x The x to add
     * @param y The y to add
     * @return This vector instance
     */
    public MutableVector2D add(double x, double y) {
        deltaX = deltaX + x;
        deltaY = deltaY + y;
        return this;
    }

    /**
     * Subtracts another vector from this vector
     *
     * @param other the vector to subtract
     * @return This vector instance
     */
    public MutableVector2D sub(MutableVector2D other) {
        deltaX = deltaX - other.deltaX;
        deltaY = deltaY - other.deltaY;
        return this;
    }

    /**
     * Subtracts a number from the x and y scalar
     *
     * @param x The x to subtract
     * @param y The y to subtract
     * @return This vector instance
     */
    public MutableVector2D sub(double x, double y) {
        deltaX = deltaX - x;
        deltaY = deltaY - y;
        return this;
    }

    /**
     * Multiplies this vector by a number
     *
     * @param n The number to multiply with
     * @return This vector instance
     */
    public MutableVector2D mul(double n) {
        deltaX = deltaX * n;
        deltaY = deltaY * n;
        return this;
    }

    /**
     * Divides this vector by a number
     *
     * @param n The number to divide by
     * @return This vector instance
     */
    public MutableVector2D div(double n) {
        deltaX = deltaX / n;
        deltaY = deltaY / n;
        return this;
    }

    /**
     * Normalises this vector so that length == 1
     *
     * @return This vector instance
     */
    public MutableVector2D nor() {

        double length = length();
        if (length != 0) {
            deltaX = deltaX / length;
            deltaY = deltaY / length;
        }

        return this;
    }

    /**
     * Returns the dot product between this and an other vector
     * Calculated by A.x * B.x + A.y * B.y
     *
     * @param other The other vector
     * @return The dot product
     */
    public double dot(MutableVector2D other) {
        return this.deltaX * other.deltaX + this.deltaY * other.deltaY;
    }

    /**
     * Calculates the distance from another vector
     * This uses square root and uses a lot of resources, so prefer to use MutableVector2D.distSqr(MutableVector2D other)
     *
     * @param other The other vector
     * @return The distance between
     */
    public double dist(MutableVector2D other) {
        return Math.sqrt(Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getY() - other.getY(), 2));
    }

    /**
     * Calculates the distance squared from another vector
     * This is better than dist because it does not use the resource heavy square root.
     *
     * @param other The other vector
     * @return The distance between
     */
    public double distSqr(MutableVector2D other) {
        return Math.pow(this.getX() - other.getX(), 2) + Math.pow(this.getY() - other.getY(), 2);
    }

    /**
     * Returns the angle between one point an another
     *
     * @param other The other point
     * @return The angle between in degrees
     */
    public double getAngleInDegrees(MutableVector2D other) {
        return Math.atan2(other.getY() - this.getY(), other.getX() - this.getX()) * 180 / Math.PI;
    }

    /**
     * Returns the x
     *
     * @return The x
     */
    public double getX() {
        return deltaX;
    }

    /**
     * Sets the x
     *
     * @param x The new x
     * @return This vector instance
     */
    public MutableVector2D setX(double x) {
        this.deltaX = x;
        return this;
    }

    /**
     * Returns the y
     *
     * @return The y
     */
    public double getY() {
        return deltaY;
    }

    /**
     * Sets the y
     *
     * @param y The new y
     * @return This vector instance
     */
    public MutableVector2D setY(double y) {
        this.deltaY = y;
        return this;
    }

    /**
     * Sets the x and y
     *
     * @param x The new x
     * @param y The new y
     * @return This vector instance
     */
    public MutableVector2D set(double x, double y) {
        this.deltaX = x;
        this.deltaY = y;
        return this;
    }


    /**
     * Method for rotating a vector around its starting position.
     * @param degrees The degrees to rotate
     * @return This vector instance
     */
    public MutableVector2D rotate(double degrees) {
        double rad = degrees * Math.PI / 180;
        double newX = deltaX * Math.cos(rad) - deltaY * Math.sin(rad);
        double newY = deltaX * Math.sin(rad) + deltaY * Math.cos(rad);
        this.deltaX = newX;
        this.deltaY = newY;
        return this;
    }

    /**
     * Returns a string representation of this class
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return "MutableVector2D(" + deltaX + ", " + deltaY + ")";
    }

    /**
     * Returns true if the other object is equal to this object
     * Returns false if the objects are of different type, or deltaX og deltaY differs
     *
     * @param other The vector to compare with
     * @return True if they are equal
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MutableVector2D)) return false;
        return !(((MutableVector2D) other).deltaX != deltaX || ((MutableVector2D) other).deltaY != deltaY);
    }

    /**
     * Creates a deep copy of this instance
     *
     * @return The copy
     */
    @Override
    public MutableVector2D clone() {
        return new MutableVector2D(deltaX, deltaY);
    }
}
