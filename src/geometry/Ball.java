//212340442 Miriam Beinhorn
package geometry;

import biuoop.DrawSurface;
import collections.GameEnvironment;
import gameManagement.Game;
import interfaces.Sprite;
import movement.CollisionInfo;
import movement.Velocity;

/**
 * The geometry.Ball class represents a ball with a center point, radius, color, and velocity.
 * It can move within specified boundaries and bounce off those boundaries.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment game;

    /**
     * Constructs a ball with the specified center point, radius, and color.
     * it also sets the velocity at 0 and creates a new game environment.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.game = new GameEnvironment();
    }

    /**
     * Sets the game environment of the ball.
     *
     * @param game the new game environment
     */
    public void setGame(GameEnvironment game) {
        this.game = game;
    }

    /**
     * Sets the radius of the ball.
     *
     * @param r the new radius
     */
    public void setR(int r) {
        this.r = r;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the new color
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * Sets the center point of the ball.
     *
     * @param center the new center point
     */
    public void setCenter(Point center) {
        this.center = new Point(center);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the change in x direction
     * @param dy the change in y direction
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the x-coordinate of the ball's center.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y-coordinate of the ball's center.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the center point of the ball.
     *
     * @return a new point that is the same as the center of the ball
     */
    public Point getCenter() {
        return new Point(this.center.getX(), this.center.getY());
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */

    public Velocity getVelocity() {
        return new Velocity(this.velocity);
    }

    /**
     * Draws the ball on the specified DrawSurface.
     *
     * @param surface the DrawSurface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * Moves the ball one step, taking into account the current velocity and the closest collision (if exists).
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                this.center.getX() + this.getVelocity().getDx(), this.center.getY() + this.getVelocity().getDy());
        CollisionInfo ci = this.game.getClosestCollision(trajectory);
        if (ci != null) {
            this.setVelocity(ci.collisionObject().hit(this, ci.collisionPoint(), this.getVelocity()));
            this.center = new Line(this.center, ci.collisionPoint()).middle();
            //make sure the ball won't get stuck in the paddle when it's moving:
            if (ci.collisionObject().getCollisionRectangle().getUpperLeft().getY() == 560
                    && ci.collisionObject().getCollisionRectangle().contains(this.center)) {
                this.center = new Point(this.center.getX(), 559);
            }
            return;
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Returns a string representation of the ball segment.
     * The string representation consists of the center point, the radius, the color, and the velocity of the ball.
     *
     * @return a string representation of the ball segment
     */
    @Override
    public String toString() {
        return "center: " + center.toString() + ", radius: " + r + ", color: " + color.toString()
                + ", velocity: " + velocity.toString();
    }

    /**
     * This method is called to indicate that time has passed in the game.
     * It triggers the movement of the ball by one step.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Adds this ball to the specified game.
     *
     * @param g The game to which this ball should be added.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
    /**
     * Removes this ball from the specified game.
     *
     * @param g The game from which this ball should be removed.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}