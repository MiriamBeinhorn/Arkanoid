//212340442 Miriam Beinhorn
package score;

import biuoop.DrawSurface;
import gameManagement.Game;
import generalHelpers.Counter;
import interfaces.Sprite;


/**
 * The score.ScoreIndicator class is responsible for displaying the current score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    /**
     * Constructor.
     *
     * @param score the generalHelpers.Counter object that holds the current score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**
     * Draw the score on the given DrawSurface.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(350, 15, "Score: " + this.score.getValue(), 15);
    }
    /**
     * Notify the sprite that time has passed.
     * in this class, no implementation is needed for this method.
     */
    @Override
    public void timePassed() {
        // No implementation needed for this method.
    }
    /**
     * Add this score.ScoreIndicator to the game.
     *
     * @param g the game to add this score.ScoreIndicator to
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}