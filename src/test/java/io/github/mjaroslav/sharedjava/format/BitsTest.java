package io.github.mjaroslav.sharedjava.format;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BitsTest {
    static final int MASK = 0b1010;

    @Test
    void test$isMaskAnd() {
        Assertions.assertFalse(Bits.isMaskAnd(0b0000, MASK), "Not match");
        Assertions.assertTrue(Bits.isMaskAnd(0b1010, MASK), "Match");
        Assertions.assertFalse(Bits.isMaskAnd(0b1000, MASK), "Match not all bits");
        Assertions.assertFalse(Bits.isMaskAnd(0b0010, MASK), "Match not all bits");
    }

    @Test
    void test$isMaskOr() {
        Assertions.assertFalse(Bits.isMaskOr(0b0000, MASK), "Not match");
        Assertions.assertTrue(Bits.isMaskOr(0b1010, MASK), "Match");
        Assertions.assertTrue(Bits.isMaskOr(0b1000, MASK), "Match one bit");
        Assertions.assertTrue(Bits.isMaskOr(0b0010, MASK), "Match one bit");
    }

}
