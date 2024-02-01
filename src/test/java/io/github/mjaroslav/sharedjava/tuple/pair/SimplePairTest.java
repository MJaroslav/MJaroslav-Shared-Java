package io.github.mjaroslav.sharedjava.tuple.pair;

import io.github.mjaroslav.sharedjava.tuple.TupleShared;
import lombok.val;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimplePairTest {
    @Test
    void getX() {
        val expected = 1;
        val notNullValuePair = new SimplePair<>(expected, null);
        val nullValuePair = new SimplePair<Integer, Object>();
        TupleShared.testGetX(notNullValuePair, nullValuePair, expected);
    }

    @Test
    void getY() {
        val expected = 1;
        val notNullValuePair = new SimplePair<>(null, expected);
        val nullValuePair = new SimplePair<Object, Integer>();
        TupleShared.testGetY(notNullValuePair, nullValuePair, expected);
    }

    @Test
    void setX() {
        val expected = true;
        val nullValuePair = new SimplePair<Boolean, Object>();
        TupleShared.testSetX(nullValuePair, expected);
    }

    @Test
    void setY() {
        val expected = true;
        val nullValuePair = new SimplePair<Object, Boolean>();
        TupleShared.testSetY(nullValuePair, expected);
    }

    @Test
    void equalsAndHashCode() {
        EqualsVerifier.simple().forClass(SimplePair.class).verify();
    }

    @Test
    void testToString() {
        val pair = new SimplePair<>(1, -1);
        val expected = "SimplePair(x=1, y=-1)";
        val actual = pair.toString();
        Assertions.assertEquals(expected, actual, "String not match");
    }
}
