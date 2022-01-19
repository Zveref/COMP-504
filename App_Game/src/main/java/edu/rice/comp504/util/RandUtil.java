package edu.rice.comp504.util;

/**
 * Utility used to generate random numbers.
 */
public class RandUtil {
    /**
     * Generate a random number.
     * @param base  The mininum value
     * @param limit The maximum number from the base
     * @return A randomly generated number
     */
    public static int getRnd(int base, int limit) {
        return (int)Math.floor(Math.random() * limit + base);
    }
}
