// 212340442 Miriam Beinhorn
package interfaces;

import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import movement.Velocity;

/**
 * The interfaces.Collidable interface represents objects that can be collided with.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return The collision rectangle of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the specified point with the given velocity.
     * Returns the new velocity expected after the hit.
     *
     * @param hitter          The ball that collided the block.
     * @param collisionPoint  The point at which the collision occurred.
     * @param currentVelocity The current velocity of the object before the collision.
     * @return The new velocity expected after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
