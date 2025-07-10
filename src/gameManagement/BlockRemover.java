//212340442 Miriam Beinhorn
package gameManagement;

import collidables.Block;
import generalHelpers.Counter;
import geometry.Ball;
import interfaces.HitListener;

/**
 * The gameManagement.BlockRemover class is responsible for removing blocks from the game
 * and updating the count of remaining blocks when a block is hit.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game            the game instance from which blocks will be removed
     * @param remainingBlocks the counter of the remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    /**
     * This method is called whenever the beingHit object is hit.
     * It sets the color of the ball, removes the block from the game and updates the remaining blocks counter.
     *
     * @param beingHit the block that is being hit
     * @param hitter the ball that hits the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getColor());
        this.game.removeCollidable(beingHit);
        this.game.removeSprite(beingHit);
        this.remainingBlocks.decrease(1);
    }
}
