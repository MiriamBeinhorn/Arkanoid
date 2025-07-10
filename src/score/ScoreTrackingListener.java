//212340442 Miriam Beinhorn
package score;

import collidables.Block;
import generalHelpers.Counter;
import geometry.Ball;
import interfaces.HitListener;

/**
 * The score.ScoreTrackingListener class is responsible for tracking the score in the game.
 * It increases the score whenever a block is hit by a ball.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter the generalHelpers.Counter object that holds the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It increases the score by 5 points.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that hits the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
