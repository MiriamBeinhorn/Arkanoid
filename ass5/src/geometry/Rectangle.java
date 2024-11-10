// 212340442 Miriam Beinhorn
package geometry;

import biuoop.DrawSurface;
import generalHelpers.Methods;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rectangle defined by its bottom-left corner, width, height, and color.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * Constructs a new geometry.Rectangle with the specified bottom-left corner, width, height, and color.
     *
     * @param upperLeft the bottom-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param color     the color of the rectangle
     */
    public Rectangle(Point upperLeft, int width, int height, Color color) {
        this.upperLeft = new Point(upperLeft);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Constructs a new geometry.Rectangle that is identical to an existing rectangle.
     *
     * @param r the rectangle we copy
     */
    public Rectangle(Rectangle r) {
        this.upperLeft = r.getUpperLeft();
        this.width = r.getWidth();
        this.height = r.getHeight();
        this.color = r.getColor();
    }

    /**
     * Sets the bottom-left corner of the rectangle.
     *
     * @param upperLeft the new bottom-left point
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = new Point(upperLeft);
    }

    /**
     * Sets the width of the rectangle.
     *
     * @param width the new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the height of the rectangle.
     *
     * @param height the new height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets the color of the rectangle.
     *
     * @param color the new color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Returns the bottom-left corner of the rectangle.
     *
     * @return the bottom-left corner of the rectangle
     */
    public Point getUpperLeft() {
        return new Point(upperLeft);
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the color of the rectangle.
     *
     * @return the color of the rectangle
     */
    public Color getColor() {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    /**
     * Draws the rectangle on the specified drawing surface.
     *
     * @param surface the drawing surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) Math.round(upperLeft.getX()), (int) Math.round(upperLeft.getY()),
                (int) width, (int) height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) Math.round(upperLeft.getX()), (int) Math.round(upperLeft.getY()),
                (int) width, (int) height);
    }

    /**
     * Returns an array of points representing the four corners of the rectangle.
     *
     * @return an array of points representing the edges of the rectangle
     */
    public Point[] getEdges() {
        Point[] edges = new Point[4];
        edges[0] = new Point(upperLeft.getX(), upperLeft.getY());
        edges[1] = new Point(upperLeft.getX() + width, upperLeft.getY());
        edges[2] = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        edges[3] = new Point(upperLeft.getX(), upperLeft.getY() + height);
        return edges;
    }

    /**
     * Checks if the rectangle contains the given point.
     *
     * @param point the point to check
     * @return true if the rectangle contains the point, false otherwise
     */
    public boolean contains(Point point) {
        return Methods.isBetween(point.getX(), upperLeft.getX(), upperLeft.getX() + width)
                && Methods.isBetween(point.getY(), upperLeft.getY(), upperLeft.getY() + height);
    }

    /**
     * Calculates the intersection points between the given line and the rectangle.
     *
     * @param line The line to check for intersections with the rectangle.
     * @return A list of points where the given line intersects with the sides of the rectangle.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        lines.add(new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY()));
        lines.add(new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height));
        lines.add(new Line(upperLeft.getX() + width, upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY()));
        lines.add(new Line(upperLeft.getX() + width, upperLeft.getY() + height,
                upperLeft.getX(), upperLeft.getY() + height));
        for (Line l : lines) {
            if (l.intersectionWith(line) != null) {
                intersectionPoints.add(l.intersectionWith(line));
            }
        }
        return intersectionPoints;
    }
}