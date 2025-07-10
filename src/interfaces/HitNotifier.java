//212340442 Miriam Beinhorn
package interfaces;

/**
 * The interfaces.HitNotifier interface should be implemented by any class that can have hit listeners.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the interfaces.HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the interfaces.HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}
