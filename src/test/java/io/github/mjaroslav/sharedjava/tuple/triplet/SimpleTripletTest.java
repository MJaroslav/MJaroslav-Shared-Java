package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleTripletTest {
    @Test
    void getX() {
        val expected = 1;
        var unit = new SimpleTriplet<>(expected, null, null);
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "Not null value not match");
        unit = new SimpleTriplet<>();
        actual = unit.getX();
        Assertions.assertNull(actual, "Null value not null");
    }

    @Test
    void getY() {
        val expected = 1;
        var unit = new SimpleTriplet<>(null, expected, null);
        var actual = unit.getY();
        Assertions.assertEquals(expected, actual, "Not null value not match");
        unit = new SimpleTriplet<>();
        actual = unit.getY();
        Assertions.assertNull(actual, "Null value not null");
    }

    @Test
    void getZ() {
        val expected = 1;
        var unit = new SimpleTriplet<>(null, null, expected);
        var actual = unit.getZ();
        Assertions.assertEquals(expected, actual, "Not null value not match");
        unit = new SimpleTriplet<>();
        actual = unit.getZ();
        Assertions.assertNull(actual, "Null value not null");
    }

    @Test
    void setX() {
        val unit = new SimpleTriplet<Boolean, Object, Object>();
        var actual = unit.getX();
        Assertions.assertNull(actual, "Null value not null");
        unit.setX(true);
        actual = unit.getX();
        Assertions.assertTrue(actual, "Not null value not match");
    }

    @Test
    void setY() {
        val unit = new SimpleTriplet<Object, Boolean, Object>();
        var actual = unit.getY();
        Assertions.assertNull(actual, "Null value not null");
        unit.setY(true);
        actual = unit.getY();
        Assertions.assertTrue(actual, "Not null value not match");
    }

    @Test
    void setZ() {
        val unit = new SimpleTriplet<Object, Object, Boolean>();
        var actual = unit.getZ();
        Assertions.assertNull(actual, "Null value not null");
        unit.setZ(true);
        actual = unit.getZ();
        Assertions.assertTrue(actual, "Not null value not match");
    }

    @Test
    void equalsAndHashCode() {
        EqualsVerifier.simple().forClass(SimpleTriplet.class).verify();
    }
}
