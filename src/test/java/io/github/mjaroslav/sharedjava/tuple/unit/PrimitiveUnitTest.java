package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PrimitiveUnitTest {
    @Test
    void nullBUnit() {
        val unit = new BUnit();
        val expected = (byte) 0;
        val actual = unit.getX();
        Assertions.assertEquals(expected, actual, "BUnit not checks for null");
    }

    @Test
    void nullDUnit() {
        val unit = new DUnit();
        val expected = 0D;
        val actual = unit.getX();
        Assertions.assertEquals(expected, actual, "DUnit not checks for null");
    }

    @Test
    void nullFUnit() {
        val unit = new FUnit();
        val expected = 0F;
        val actual = unit.getX();
        Assertions.assertEquals(expected, actual, "FUnit not checks for null");
    }

    @Test
    void nullIUnit() {
        val unit = new IUnit();
        val expected = 0;
        val actual = unit.getX();
        Assertions.assertEquals(expected, actual, "IUnit not checks for null");
    }

    @Test
    void nullLUnit() {
        val unit = new LUnit();
        val expected = 0L;
        val actual = unit.getX();
        Assertions.assertEquals(expected, actual, "LUnit not checks for null");
    }

    @Test
    void nullSUnit() {
        val unit = new SUnit();
        val expected = (short) 0;
        val actual = unit.getX();
        Assertions.assertEquals(expected, actual, "SUnit not checks for null");
    }
}
