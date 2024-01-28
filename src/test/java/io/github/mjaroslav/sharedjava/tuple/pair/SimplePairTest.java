package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimplePairTest {
    @Test
    void getX() {
        val expected = 1;
        var unit = new SimplePair<>(expected, null);
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "Not null value not match");
        unit = new SimplePair<>();
        actual = unit.getX();
        Assertions.assertNull(actual, "Null value not null");
    }

    @Test
    void getY() {
        val expected = 1;
        var unit = new SimplePair<>(null, expected);
        var actual = unit.getY();
        Assertions.assertEquals(expected, actual, "Not null value not match");
        unit = new SimplePair<>();
        actual = unit.getY();
        Assertions.assertNull(actual, "Null value not null");
    }

    @Test
    void setX() {
        val unit = new SimplePair<Boolean, Object>();
        var actual = unit.getX();
        Assertions.assertNull(actual, "Null value not null");
        unit.setX(true);
        actual = unit.getX();
        Assertions.assertTrue(actual, "Not null value not match");
    }

    @Test
    void setY() {
        val unit = new SimplePair<Object, Boolean>();
        var actual = unit.getY();
        Assertions.assertNull(actual, "Null value not null");
        unit.setY(true);
        actual = unit.getY();
        Assertions.assertTrue(actual, "Not null value not match");
    }

    @Test
    void equalsAndHashCode() {
        EqualsVerifier.simple().forClass(SimplePair.class).verify();
    }
}
