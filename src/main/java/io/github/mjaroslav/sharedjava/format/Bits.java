package io.github.mjaroslav.sharedjava.format;

import lombok.experimental.UtilityClass;

/**
 * Utilities for checking bits bit mask for example.
 *
 * @author MJaroslav
 * @since 0.1.0
 */
@UtilityClass
public class Bits {
    /**
     * Test int with bit mask for full match of bits.
     *
     * @param test int for test
     * @param mask bit mask
     * @return true if and only if test contains all bits from mask and not contains another
     * @since 0.1.0
     */
    public boolean isMaskAnd(int test, int mask) {
        return (test & mask) == mask;
    }

    /**
     * Test int with bit mask for match of any bit.
     *
     * @param test int for test
     * @param mask bit mask
     * @return true if test and mask has at lest one shared bit
     * @since 0.1.0
     */
    public boolean isMaskOr(int test, int mask) {
        return (test & mask) != 0;
    }
}
