package io.github.mjaroslav.sharedjava.tuple.pair;

import io.github.mjaroslav.sharedjava.tuple.TupleShared;
import lombok.val;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

class DelegatingPairTest {
    @Test
    void getX() {
        val expected = 1;
        val notNullValuePair = new DelegatingPair<>(expected, null);
        val nullValuePair = new DelegatingPair<Integer, Object>();
        TupleShared.testGetX(notNullValuePair, nullValuePair, expected);
    }

    @Test
    void getY() {
        val expected = 1;
        val notNullValuePair = new DelegatingPair<>(null, expected);
        val nullValuePair = new DelegatingPair<Object, Integer>();
        TupleShared.testGetY(notNullValuePair, nullValuePair, expected);
    }

    @Test
    void setX() {
        val expected = true;
        val nullValuePair = new DelegatingPair<Boolean, Object>();
        TupleShared.testSetX(nullValuePair, expected);
    }

    @Test
    void setY() {
        val expected = true;
        val nullValuePair = new DelegatingPair<Object, Boolean>();
        TupleShared.testSetY(nullValuePair, expected);
    }

    @Test
    void equalsAndHashCode() {
        EqualsVerifier.simple().forClass(DelegatingPair.class).withOnlyTheseFields("x", "y")
            .withPrefabValues(BiPredicate.class, new PairEqualsDummy(), new PairEqualsDummy())
            .withPrefabValues(ToIntFunction.class, new PairHashCodeDummy(), new PairHashCodeDummy()).verify();
    }

    @Test
    void testToString() {
        val unit = new DelegatingPair<>(0, 1);
        var expected = "DelegatingPair(x=0, y=1)";
        var actual = unit.toString();
        Assertions.assertEquals(expected, actual, "Default string not match");
        unit.setToStringFunc(p -> p.getX() + "x" + p.getY());
        expected = "0x1";
        actual = unit.toString();
        Assertions.assertEquals(expected, actual, "Delegated string not match");
    }
}
