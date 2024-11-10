// 212340442 Miriam Beinhorn
package collidables;

import biuoop.DrawSurface;
import gameManagement.Game;
import generalHelpers.Methods;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import movement.Velocity;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

/**
 * The collidables.Block class represents a block in the game, which can collide with other objects
 * and be drawn on the screen.
 * It implements the interfaces.Collidable and interfaces.Sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private List<HitListener> hitListeners;

    /**
     * Constructs a collidables.Block with the specified rectangle.
     *
     * @param rectangle The rectangle representing the block.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = new Rectangle(rectangle);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructs a collidables.Block with the specified details of its rectangle.
     *
     * @param upperLeft The upper-left point of the block.
     * @param width     The width of the block.
     * @param height    The height of the block.
     * @param color     The color of the block.
     */
    public Block(Point upperLeft, int width, int height, Color color) {
        this.rectangle = new Rectangle(upperLeft, width, height, color);
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Sets the upper-left point of the block's rectangle.
     *
     * @param upperLeft The new upper-left point.
     */
    public void setUpperLeft(Point upperLeft) {
        this.rectangle.setUpperLeft(upperLeft);
    }

    /**
     * Gets the upper-left point of the block's rectangle.
     *
     * @return The upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.rectangle.getUpperLeft();
    }

    /**
     * Gets the color of the block's rectangle.
     *
     * @return The color of the rectangle.
     */
    public Color getColor() {
        return this.rectangle.getColor();
    }

    /**
     * Draws the block on the specified surface.
     *
     * @param surface The surface to draw the block on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        this.rectangle.drawOn(surface);
    }

    /**
     * Returns the blocks rectangle.
     *
     * @return The rectangle of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rectangle);
    }


    /**
     * Handles the hit event when the block is hit by a ball.
     * Changes the velocity based on the collision point.
     *
     * @param collisionPoint  The point where the collision occurred.
     * @param currentVelocity The current velocity of the ball.
     * @return The new velocity after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        Rectangle rect = this.getCollisionRectangle();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Point[] edges = rect.getEdges();
        for (Point edge : edges) {
            if (edge.equals(collisionPoint)) {
                return new Velocity(-1 * dx, -1 * dy);
            }
        }
        if (Methods.areEqual(collisionPoint.getX(), rect.getUpperLeft().getX())
                || Methods.areEqual(collisionPoint.getX(), rect.getUpperLeft().getX() + rect.getWidth())) {
            return new Velocity(-1 * dx, dy);
        }
        if (Methods.areEqual(collisionPoint.getY(), rect.getUpperLeft().getY())
                || Methods.areEqual(collisionPoint.getY(), rect.getUpperLeft().getY() + rect.getHeight())) {
            return new Velocity(dx, -1 * dy);
        }
        return currentVelocity;
    }

    /**
     * Updates the block's state as time passes.
     * Currently, does nothing.
     */
    @Override
    public void timePassed() {
        // No action is required for this implementation yet
    }

    /**
     * Adds the block to the specified game as both a collidable object and a sprite.
     *
     * @param g The game to which this block should be added to.
     */
    @Override
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Check if the blocks color is the same as a given balls color.
     *
     * @param ball the ball we compare colors with.
     * @return true if they have the same color, and false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(this.rectangle.getColor());
    }

    /**
     * Removes the block from the specified game as both a collidable object and a sprite.
     *
     * @param game The game which this block should be removed from.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all HitListeners about a hit .
     *
     * @param hitter The ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
