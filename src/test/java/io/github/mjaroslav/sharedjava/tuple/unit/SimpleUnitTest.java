package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleUnitTest {
    @Test
    void getX() {
        val expected = 1;
        var unit = new SimpleUnit<>(expected);
        var actual = unit.getX();
        Assertions.assertEquals(expected, actual, "Not null value not match");
        unit = new SimpleUnit<>();
        actual = unit.getX();
        Assertions.assertNull(actual, "Null value not null");
    }

    @Test
    void setX() {
        val unit = new SimpleUnit<Boolean>();
        var actual = unit.getX();
        Assertions.assertNull(actual, "Null value not null");
        unit.setX(true);
        actual = unit.getX();
        Assertions.assertTrue(actual, "Not null value not match");
    }

    @Test
    void equalsAndHashCode() {
        EqualsVerifier.simple().forClass(SimpleUnit.class).verify();
    }
}
