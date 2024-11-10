// 212340442 Miriam Beinhorn
package movement;

import geometry.Point;

/**
 * The movement.Velocity class represents the change in position on the `x` and `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a movement.Velocity with the specified change in x (`dx`) and change in y (`dy`).
     *
     * @param dx the change in x
     * @param dy the change in y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructs a movement.Velocity that is a copy of the specified movement.Velocity.
     *
     * @param v the movement.Velocity to copy
     */
    public Velocity(Velocity v) {
        this.dx = v.dx;
        this.dy = v.dy;
    }

    /**
     * Returns a new movement.Velocity object with the same `dx` and `dy`.
     *
     * @return a new movement.Velocity object
     */
    public Velocity getVelocity() {
        return new Velocity(this.dx, this.dy);
    }

    /**
     * Returns the change in x (`dx`).
     *
     * @return the change in x
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the change in y (`dy`).
     *
     * @return the change in y
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets the change in x (`dx`) and change in y (`dy`).
     *
     * @param dx the new change in x
     * @param dy the new change in y
     */
    public void setVelocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Resets the velocity.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.dx = v.dx;
        this.dy = v.dy;
    }

    /**
     * Takes a point with position `(x, y)` and returns a new point with position `(x + dx, y + dy)`.
     *
     * @param p the point to which the velocity is applied
     * @return a new point with updated position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Returns a string representation of the movement.Velocity.
     *
     * @return a string representation of the movement.Velocity
     */
    @Override
    public String toString() {
        return "dx=" + dx + ", dy=" + dy;
    }

    /**
     * Creates a movement.Velocity from the specified angle and speed.
     *
     * @param angle the angle in degrees
     * @param speed the speed
     * @return a new movement.Velocity object representing the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle));
        double dy = speed * Math.sin(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}