//212340442 Miriam Beinhorn
package interfaces;

import collidables.Block;
import geometry.Ball;

/**
 * The interfaces.HitListener interface should be implemented by any class that wants to be notified of hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that hits the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
