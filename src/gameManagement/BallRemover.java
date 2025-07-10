//212340442 Miriam Beinhorn
package gameManagement;

import collidables.Block;
import generalHelpers.Counter;
import geometry.Ball;
import interfaces.HitListener;

/**
 * The gameManagement.BallRemover class is responsible for removing balls from the game
 * and updating the count of remaining balls when a ball hits a block.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a gameManagement.BallRemover.
     *
     * @param game  the game instance from which balls will be removed
     * @param balls the counter of the remaining balls
     */
    public BallRemover(Game game, Counter balls) {
        this.game = game;
        this.remainingBalls = balls;

    }

    /**
     * This method is called whenever the block hidden under the games frame is hit.
     * It removes the hitter ball from the game and decreases the ball counter.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that hits the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}