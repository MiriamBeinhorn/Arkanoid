// 212340442 Miriam Beinhorn
package geometry;

import generalHelpers.Methods;

import java.util.List;

/**
 * The geometry.Line class represents a line segment in a 2D Cartesian coordinate system.
 * A line segment is defined by two Points, start and end.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructs a geometry.Line with the specified start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Constructs a geometry.Line with the specified coordinates for the start and end points.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the midpoint of the line segment.
     *
     * @return the midpoint of the line segment
     */
    public Point middle() {
        return new Point((end.getX() + start.getX()) / 2, (end.getY() + start.getY()) / 2);
    }

    /**
     * Returns the start point of the line segment.
     *
     * @return the start point of the line segment
     */
    public Point start() {
        return new Point(start.getX(), start.getY());
    }

    /**
     * Returns the end point of the line segment.
     *
     * @return the end point of the line segment
     */
    public Point end() {
        return new Point(end.getX(), end.getY());
    }

    /**
     * Returns a string representation of the line segment.
     *
     * @return a string representation of the line segment
     */
    @Override
    public String toString() {
        return start.toString() + " - " + end.toString();
    }

    /**
     * Sets the start point of the line segment.
     *
     * @param start the new start point of the line segment
     */
    public void setStart(Point start) {
        this.start = new Point(start.getX(), start.getY());
    }

    /**
     * Sets the end point of the line segment.
     *
     * @param end the new end point of the line segment
     */
    public void setEnd(Point end) {
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Determines whether this line segment intersects with another line segment.
     *
     * @param other the other line segment to check for intersection
     * @return true if the line segments intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }
        if (start.equals(other.start()) || start.equals(other.end())
                || end.equals(other.start()) || end.equals(other.end())) {
            return true; // if the lines share an edge point.
        }
        double slop1 = this.getSlop(); // find the slops of the lines
        double slop2 = other.getSlop();
        double const1 = this.end.getY() - slop1 * this.end.getX(); //find the functions constants
        double const2 = other.end.getY() - slop2 * other.end.getX();
        //If a line is parallel to the Y-axis:
        if ((slop1 == Double.POSITIVE_INFINITY) && (slop2 == Double.POSITIVE_INFINITY)) {
            if (this.start.getX() != other.start.getX()) { // if their x's are different
                return false;
            }
            // now we check if 1 of their edges is on the other line
            if (Methods.isInside(this.start.getY(), other.start.getY(), other.end.getY())) {
                return true;
            }
            if (Methods.isInside(this.end.getY(), other.start.getY(), other.end.getY())) {
                return true;
            }
            if (Methods.isInside(other.start.getY(), this.start.getY(), this.end.getY())) {
                return true;
            }
            return Methods.isInside(other.end.getY(), this.start.getY(), this.end.getY());
        }
        if (Methods.areEqual(slop1, slop2)) { // if the lines are parallel
            if (!Methods.areEqual(const1, const2)) { //if their constants are different they will never meet
                return false;
            }
            //if one of the edges points is on the other line
            if (Methods.isInside(this.start.getX(), other.start.getX(), other.end.getX())) {
                return true;
            }
            if (Methods.isInside(this.end.getX(), other.start.getX(), other.end.getX())) {
                return true;
            }
            if (Methods.isInside(other.start.getX(), this.start.getX(), this.end.getX())) {
                return true;
            }
            return Methods.isInside(other.end.getX(), this.start.getX(), this.end.getX());
        }
        if (slop1 == Double.POSITIVE_INFINITY) { // if the first line is parallel to the Y-axis
            double yPoint = (slop2 * this.start.getX()) + const2;
            return Methods.isBetween(yPoint, this.start.getY(), this.end.getY())
                    && Methods.isBetween(this.start.getX(), other.start.getX(), other.end.getX());
        }
        if (slop2 == Double.POSITIVE_INFINITY) { // if the second line is parallel to the Y-axis
            double yPoint = (slop1 * other.start.getX()) + const1;
            return Methods.isBetween(yPoint, other.start.getY(), other.end.getY())
                    && Methods.isBetween(other.start.getX(), this.start.getX(), this.end.getX());
        }
        //if none of them is parallel to the Y-axis or to each other
        double xPoint = (const2 - const1) / (slop1 - slop2);
        return (Methods.isBetween(xPoint, this.start.getX(), this.end.getX()))
                && (Methods.isBetween(xPoint, other.start.getX(), other.end.getX()));
    }

    /**
     * Determines whether this line segment intersects with two other line segments.
     *
     * @param other1 the first line segment to check for intersection
     * @param other2 the second line segment to check for intersection
     * @return true if this line segment intersects with both other line segments, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return (this.isIntersecting(other1) && this.isIntersecting(other2));
    }

    /**
     * Returns the intersection point of this line segment with another line segment, if they intersect.
     * If the line segments do not intersect, or having infinity intersection points, returns null.
     *
     * @param other the other line segment to check for intersection
     * @return the intersection point if the line segments intersect in exactly 1 point, null otherwise
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        // now we know for sure that the lines are intersecting:
        if (this.start.equals(this.end)) { //if the first line is a single point
            return new Point(this.start.getX(), this.start.getY());
        }
        if (other.start.equals(other.end)) { //if the second line is a single point
            return new Point(other.start.getX(), other.start.getY());
        }
        if (this.equals(other)) { //if the lines are equals they have infinity intersection points
            return null;
        }
        double slop1 = this.getSlop(); //get the slops of the lines
        double slop2 = other.getSlop();
        double const1 = this.end.getY() - slop1 * this.end.getX(); //find the functions constants
        double const2 = other.end.getY() - slop2 * other.end.getX();
        // if both lines are parallel to the Y-axis:
        if (slop1 == Double.POSITIVE_INFINITY && slop2 == Double.POSITIVE_INFINITY) {
            //check if they have infinity intersection points:
            if (Methods.isInside(this.start.getY(), other.start.getY(), other.end.getY())) {
                return null;
            }
            if (Methods.isInside(this.end.getY(), other.start.getY(), other.end.getY())) {
                return null;
            }
            if (Methods.isInside(other.start.getY(), this.start.getY(), this.end.getY())) {
                return null;
            }
            if (Methods.isInside(other.end.getY(), this.start.getY(), this.end.getY())) {
                return null;
            }
            //if they have a single intersection point, it must be one of the edges:
            if (this.start.equals(other.start) || this.start.equals(other.end)) {
                return new Point(this.start.getX(), this.start.getY());
            }
            return new Point(this.end.getX(), this.end.getY());
        }
        if (Methods.areEqual(slop1, slop2)) { //if the lines have the same slop
            //check if they have infinity intersection points:
            if (Methods.isInside(this.start.getX(), other.start.getX(), other.end.getX())) {
                return null;
            }
            if (Methods.isInside(this.end.getX(), other.start.getX(), other.end.getX())) {
                return null;
            }
            if (Methods.isInside(other.start.getX(), this.start.getX(), this.end.getX())) {
                return null;
            }
            if (Methods.isInside(other.end.getX(), this.start.getX(), this.end.getX())) {
                return null;
            }
            //if they have a single intersection point, it must be one of the edges:
            if (this.start.equals(other.start) || this.start.equals(other.end)) {
                return new Point(this.start.getX(), this.start.getY());
            }
            return new Point(this.end.getX(), this.end.getY());
        }
        if (slop1 == Double.POSITIVE_INFINITY) { //if the first line is parallel to the Y-axis
            double xPoint = this.start.getX();
            double yPoint = (slop2 * xPoint) + const2;
            return new Point(xPoint, yPoint);
        }
        if (slop2 == Double.POSITIVE_INFINITY) { //if the second line is parallel to the Y-axis
            double xPoint = other.start.getX();
            double yPoint = (slop1 * other.start.getX()) + const1;
            return new Point(xPoint, yPoint);
        }
        //in every other case:
        double xPoint = (const2 - const1) / (slop1 - slop2);
        double yPoint = slop1 * xPoint + const1;
        return new Point(xPoint, yPoint);
    }

    /**
     * Indicates whether some other line is exactly same to this one.
     *
     * @param other the line segment to compare with
     * @return true if this line segment is equal to the specified line segment, false otherwise
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        return this.start.equals(other.end) && this.end.equals(other.start);
    }

    /**
     * Returns the slop of the line segment.
     * If the line segment is vertical, returns Double.POSITIVE_INFINITY.
     *
     * @return the slope of the line segment
     */
    public double getSlop() {
        if (Methods.areEqual(this.end.getX(), this.start.getX())) { //if the line is parallel to the Y-axis
            return Double.POSITIVE_INFINITY;
        }
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * Finds the closest intersection point between this line and the given rectangle.
     * If there are no intersection points, returns null. Otherwise, returns the intersection
     * point that is closest to the start of this line.
     *
     * @param rect The rectangle to check for intersections with this line.
     * @return The closest intersection point to the start of this line, or null if no intersections occur.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point point = new Point(intersectionPoints.get(0));
        for (Point p : intersectionPoints) {
            if (p.distance(this.start) < point.distance(this.start)) {
                point = new Point(p);
            }
        }
        return point;
    }
}