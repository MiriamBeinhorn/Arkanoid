// 212340442 Miriam Beinhorn
package collidables;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameManagement.Game;
import generalHelpers.Methods;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import movement.Velocity;

import java.awt.Color;

/**
 * The collidables.Paddle class represents the paddle in the game.
 * The paddle is a rectangle controlled by the keyboard, and it can move left and right.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block block;

    /**
     * Constructs a collidables.Paddle object with the specified keyboard sensor,
     * and initialize it with specific location, size, and color.
     *
     * @param keyboard The keyboard sensor to control the paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.block = new Block(new Point(255, 560), 150, 20, Color.white);
    }

    /**
     * Sets the paddle's X position.
     *
     * @param n The amount to move the paddle along the X axis.
     */
    private void setX(double n) {
        double x = this.block.getUpperLeft().getX();
        double y = this.block.getUpperLeft().getY();
        this.block.setUpperLeft(new Point(x + n, y));
    }

    /**
     * Moves the paddle to the left if the left key is pressed.
     * Wraps around to the right side if it moves past the left boundary.
     */
    public void moveLeft() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.setX(-10);
        }
        if (Methods.smallerOrEqual(this.block.getUpperLeft().getX(), -130)) {
            this.setX(910);
        }
    }

    /**
     * Moves the paddle to the right if the right key is pressed.
     * Wraps around to the left side if it moves past the right boundary.
     */
    public void moveRight() {
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.setX(10);
        }
        if (Methods.biggerOrEqual(this.block.getUpperLeft().getX(), 780)) {
            this.setX(-910);
        }
    }

    /**
     * Notifies the paddle that a unit of time has passed, moving it left or right if the respective keys are pressed.
     */
    @Override
    public void timePassed() {
        this.moveLeft();
        this.moveRight();
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d The DrawSurface to draw the paddle on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return The collision rectangle of the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    /**
     * Handles the collision of the paddle with a ball.
     * Divides the paddle into 5 regions, each changing the ball's velocity angle in a different way.
     *
     * @param collisionPoint  The point at which the collision occurs.
     * @param currentVelocity The current velocity of the ball.
     * @return The new velocity after the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (Methods.isBetween(collisionPoint.getX(), this.block.getUpperLeft().getX(),
                this.block.getUpperLeft().getX() + 30)) { //region 1
            return new Velocity(Velocity.fromAngleAndSpeed(210, 5));
        }
        if (Methods.isBetween(collisionPoint.getX(), this.block.getUpperLeft().getX() + 30,
                this.block.getUpperLeft().getX() + 60)) { //region 2
            return new Velocity(Velocity.fromAngleAndSpeed(240, 5));
        }
        if (Methods.isBetween(collisionPoint.getX(), this.block.getUpperLeft().getX() + 60,
                this.block.getUpperLeft().getX() + 90)) { //region 3
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        if (Methods.isBetween(collisionPoint.getX(), this.block.getUpperLeft().getX() + 90,
                this.block.getUpperLeft().getX() + 120)) { //region 4
            return new Velocity(Velocity.fromAngleAndSpeed(300, 5));
        }
        if (Methods.isBetween(collisionPoint.getX(), this.block.getUpperLeft().getX() + 120,
                this.block.getUpperLeft().getX() + 150)) { //region 5
            return new Velocity(Velocity.fromAngleAndSpeed(330, 5));
        }
        return currentVelocity;
    }

    /**
     * Adds this paddle to the game.
     *
     * @param g The game to add the paddle to.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
