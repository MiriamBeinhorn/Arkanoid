//212340442 Miriam Beinhorn
package generalHelpers;
/**
 * The generalHelpers.Counter class is used for counting things like score, remaining balls, etc.
 * It provides methods to increase, decrease, and get the current count.
 */
public class Counter {
    private int count;

    /**
     * Constructor.
     *
     * @param num the initial value of the counter
     */
    public Counter(int num) {
        count = num;
    }

    /**
     * Add number to the current count.
     *
     * @param number the number to be added to the count
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * Subtract number from the current count.
     *
     * @param number the number to be subtracted from the count
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * Get the current count.
     *
     * @return the current count value
     */
    public int getValue() {
        return count;
    }
}