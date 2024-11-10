//212340442 Miriam Beinhorn
package generalHelpers;

import java.awt.Color;

/**
 * The generalHelpers.Methods class includes general methods that are used in several different classes.
 */
public class Methods {

    /**
     * gets 2 numbers , and returns true if the numbers are close enough, and false otherwise.
     *
     * @param a the first number
     * @param b the second number
     * @return true if the numbers are practically equal, and false otherwise
     */
    public static boolean areEqual(double a, double b) {
        double epsilon = 0.0000001;
        return Math.abs(a - b) <= epsilon;
    }

    /**
     * Replace the use of '>=', and includes threshold.
     *
     * @param a the first number
     * @param b the second number
     * @return true if a>=b, and false otherwise
     */
    public static boolean biggerOrEqual(double a, double b) {
        return (a > b) || areEqual(a, b);
    }

    /**
     * Replace the use of '<=', and includes threshold.
     *
     * @param a the first number
     * @param b the second number
     * @return true if a<=b, and false otherwise
     */
    public static boolean smallerOrEqual(double a, double b) {
        return (a < b) || areEqual(a, b);
    }

    /**
     * This method gets 3 doubles, and checks if the first 1 is between the other two.
     *
     * @param num  the number we check
     * @param num1 the first edge of the range
     * @param num2 the second edge of the range
     * @return true if num is between num1 and num2 (including them), and false otherwise
     */
    public static boolean isBetween(double num, double num1, double num2) {
        return (biggerOrEqual(num, num1) && smallerOrEqual(num, num2))
                || (smallerOrEqual(num, num1) && biggerOrEqual(num, num2));
    }

    /**
     * This method gets 3 doubles, and checks if the first 1 is between the other two, but not including them.
     *
     * @param num  the number we check
     * @param num1 the first edge of the range
     * @param num2 the second edge of the range
     * @return true if num is between num1 and num2 (not including them), and false otherwise
     */
    public static boolean isInside(double num, double num1, double num2) {
        return (num > num1 && num < num2) || (num < num1 && num > num2);
    }

    /**
     * An array of predefined colors used in the game.
     */
    public static final Color[] COLORS = {
            Color.RED,
            Color.GREEN,
            Color.CYAN,
            Color.YELLOW,
            Color.ORANGE,
            new Color(128, 0, 128) // purple
    };

}