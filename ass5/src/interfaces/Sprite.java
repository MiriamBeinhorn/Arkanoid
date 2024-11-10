// 212340442 Miriam Beinhorn
package interfaces;

import biuoop.DrawSurface;
import gameManagement.Game;

/**
 * The interfaces.Sprite interface represents objects that can be drawn on the screen and updated over time.
 */
public interface Sprite {
    /**
     * Draws the sprite on the specified DrawSurface.
     *
     * @param d The DrawSurface on which to draw the sprite.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is typically used for updating the sprite's state.
     */
    void timePassed();

    /**
     * Adds the sprite to the specified game.
     *
     * @param g The game to which the sprite should be added.
     */
    void addToGame(Game g);
}
