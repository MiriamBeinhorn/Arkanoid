// 212340442 Miriam Beinhorn
package collections;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The collections.SpriteCollection class manages a collection of sprites.
 * It provides methods to add sprites, update their state over time, and draw them on a DrawSurface.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructs an empty collections.SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Constructs a collections.SpriteCollection with the specified list of sprites.
     *
     * @param sprite The list of sprites to initialize the collection with.
     */
    public SpriteCollection(List<Sprite> sprite) {
        this.sprites = sprite;
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s The sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Calls the timePassed() method on all sprites in the collection.
     * This method is typically used to update the state of the sprites over time.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesList = new ArrayList<>(this.sprites);
        for (Sprite s : spritesList) {
            s.timePassed();
        }
    }

    /**
     * Calls the drawOn(d) method on all sprites in the collection.
     * This method is used to draw all sprites on the specified DrawSurface.
     *
     * @param d The DrawSurface on which to draw the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}