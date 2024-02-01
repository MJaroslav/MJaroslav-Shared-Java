package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PrimitivePairTest {
    @Test
    void nullBPair() {
        val unit = new BPair();
        val expected = (byte) 0;
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "BPair not checks for null");
        actual = unit.getY();
        Assertions.assertEquals(expected, actual, "BPair not checks for null");
    }

    @Test
    void nullDPair() {
        val unit = new DPair();
        val expected = 0D;
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "DPair not checks for null");
        actual = unit.getY();
        Assertions.assertEquals(expected, actual, "DPair not checks for null");
    }

    @Test
    void nullFPair() {
        val unit = new FPair();
        val expected = 0F;
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "FPair not checks for null");
        actual = unit.getY();
        Assertions.assertEquals(expected, actual, "FPair not checks for null");
    }

    @Test
    void nullIPair() {
        val unit = new IPair();
        val expected = 0;
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "IPair not checks for null");
        actual = unit.getY();
        Assertions.assertEquals(expected, actual, "IPair not checks for null");
    }

    @Test
    void nullLPair() {
        val unit = new LPair();
        val expected = 0L;
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "LPair not checks for null");
        actual = unit.getY();
        Assertions.assertEquals(expected, actual, "LPair not checks for null");
    }

    @Test
    void nullSPair() {
        val unit = new SPair();
        val expected = (short) 0;
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "SPair not checks for null");
        actual = unit.getY();
        Assertions.assertEquals(expected, actual, "SPair not checks for null");
    }
}
