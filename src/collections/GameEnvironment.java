// 212340442 Miriam Beinhorn
package collections;

import geometry.Line;
import geometry.Point;
import interfaces.Collidable;
import movement.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * The collections.GameEnvironment class manages a collection of collidable objects.
 * It provides methods to add collidables and to find the closest collision
 * that an object moving along a trajectory will encounter.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructs an empty collections.GameEnvironment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Constructs a collections.GameEnvironment with a given list of collidables.
     *
     * @param blocks The list of collidables to initialize the environment with.
     */
    public GameEnvironment(List<Collidable> blocks) {
        this.collidables = blocks;
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c The collidable to add.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes the given collidable from the environment.
     *
     * @param c The collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Assumes an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, returns null.
     * Otherwise, returns the information about the closest collision that is going to occur.
     *
     * @param trajectory The trajectory line of the moving object.
     * @return The information about the closest collision, or null if no collision occurs.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point p = null;
        Collidable c = null;
        for (Collidable col : this.collidables) {
            if (trajectory.closestIntersectionToStartOfLine(col.getCollisionRectangle()) != null) {
                if (p == null) { //if it's the first collision point yet
                    p = trajectory.closestIntersectionToStartOfLine(col.getCollisionRectangle());
                    c = col;
                } else {
                    if (trajectory.closestIntersectionToStartOfLine(col.getCollisionRectangle())
                            .distance(trajectory.start())
                            < p.distance(trajectory.start())) {
                        //if the new collision point is closer than the current one
                        p = trajectory.closestIntersectionToStartOfLine(col.getCollisionRectangle());
                        c = col;
                    }
                }
            }
        }
        if (c == null) {
            return null;
        }
        return new CollisionInfo(p, c);
    }
}