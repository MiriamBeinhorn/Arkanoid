//212340442 Miriam Beinhorn
package geometry;

import generalHelpers.Methods;

/**
 * The geometry.Point class represents a point in a 2D Cartesian coordinate system.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a geometry.Point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a geometry.Point that is a copy of the specified point.
     *
     * @param p the point to copy
     */
    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * Returns the distance between this point and another point.
     *
     * @param other the point to which the distance from is calculated
     * @return the distance between this point and the other point, or -1 if the other point is null
     */
    public double distance(Point other) {
        if (other == null) {
            return -1;
        }
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Indicates whether some other point is "equal to" this one.
     *
     * @param other the point to compare with
     * @return true if this point is equal to the specified point, and false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (Methods.areEqual(this.x, other.x) && Methods.areEqual(this.y, other.y));
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the x value of the point into a new one.
     *
     * @param x the new x value of the point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y value of the point into a new one.
     *
     * @param y the new y value of the point
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Checks if the point is on the left of another point.
     *
     * @param other the point to compare with
     * @return true if this point is on the left of the other point, and false otherwise
     */
    public boolean isLeftTo(Point other) {
        return this.getX() < other.getX();
    }

    /**
     * Checks if the point is on the right of another point.
     *
     * @param other the point to compare with
     * @return true if this point is on the right of the other point, and false otherwise
     */
    public boolean isRightTo(Point other) {
        return this.getX() > other.getX();
    }

    /**
     * Checks if the point is higher than of another point.
     *
     * @param other the point to compare with
     * @return true if this point is higher the other point, and false otherwise
     */
    public boolean isHigherThan(Point other) {
        return this.getY() > other.getY();
    }

    /**
     * Checks if the point is lower than of another point.
     *
     * @param other the point to compare with
     * @return true if this point is lower the other point, and false otherwise
     */
    public boolean isLowerThan(Point other) {
        return this.getY() < other.getY();
    }


    /**
     * Returns a string representation of this point.
     *
     * @return a string representation of this point
     */
    @Override
    public String toString() {
        return "(" + x + " , " + y + ")";
    }
}