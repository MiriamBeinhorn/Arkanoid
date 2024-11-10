// 212340442 Miriam Beinhorn
package movement;

import geometry.Point;
import interfaces.Collidable;

/**
 * The movement.CollisionInfo class holds information about a collision event,
 * including the collision point and the collidable object involved.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructs a movement.CollisionInfo object with the specified collision point and collidable object.
     *
     * @param collisionPoint  The point at which the collision occurs.
     * @param collisionObject The collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = new Point(collisionPoint);
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return The collision point.
     */
    public Point collisionPoint() {
        return new Point(this.collisionPoint);
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return The collidable object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
