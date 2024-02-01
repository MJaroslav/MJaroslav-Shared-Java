package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PrimitiveTripletTest {
    @Test
    void nullBTriplet() {
        val unit = new BTriplet();
        val expected = (byte) 0;
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "BTriplet not checks for null");
        actual = unit.getY();
        Assertions.assertEquals(expected, actual, "BTriplet not checks for null");
        actual = unit.getZ();
        Assertions.assertEquals(expected, actual, "BTriplet not checks for null");
    }

    @Test
    void nullDTriplet() {
        val unit = new DTriplet();
        val expected = 0D;
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "DTriplet not checks for null");
        actual = unit.getY();
        Assertions.assertEquals(expected, actual, "DTriplet not checks for null");
        actual = unit.getZ();
        Assertions.assertEquals(expected, actual, "BTriplet not checks for null");
    }

    @Test
    void nullFTriplet() {
        val unit = new FTriplet();
        val expected = 0F;
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "FTriplet not checks for null");
        actual = unit.getY();
        Assertions.assertEquals(expected, actual, "FTriplet not checks for null");
        actual = unit.getZ();
        Assertions.assertEquals(expected, actual, "BTriplet not checks for null");
    }

    @Test
    void nullITriplet() {
        val unit = new ITriplet();
        val expected = 0;
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "ITriplet not checks for null");
        actual = unit.getY();
        Assertions.assertEquals(expected, actual, "ITriplet not checks for null");
        actual = unit.getZ();
        Assertions.assertEquals(expected, actual, "BTriplet not checks for null");
    }

    @Test
    void nullLTriplet() {
        val unit = new LTriplet();
        val expected = 0L;
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "LTriplet not checks for null");
        actual = unit.getY();
        Assertions.assertEquals(expected, actual, "LTriplet not checks for null");
        actual = unit.getZ();
        Assertions.assertEquals(expected, actual, "BTriplet not checks for null");
    }

    @Test
    void nullSTriplet() {
        val unit = new STriplet();
        val expected = (short) 0;
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "STriplet not checks for null");
        actual = unit.getY();
        Assertions.assertEquals(expected, actual, "STriplet not checks for null");
        actual = unit.getZ();
        Assertions.assertEquals(expected, actual, "BTriplet not checks for null");
    }
}
