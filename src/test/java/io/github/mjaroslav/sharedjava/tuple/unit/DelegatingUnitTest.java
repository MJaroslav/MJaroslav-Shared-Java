package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.TupleShared;
import lombok.val;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

class DelegatingUnitTest {
    @Test
    void getX() {
        val expected = 1;
        val notNullValueUnit = new DelegatingUnit<>(expected);
        val nullValueUnit = new DelegatingUnit<Integer>();
        TupleShared.testGetX(notNullValueUnit, nullValueUnit, expected);
    }

    @Test
    void setX() {
        val expected = true;
        val nullValueUnit = new DelegatingUnit<Boolean>();
        TupleShared.testSetX(nullValueUnit, expected);
    }

    @Test
    void equalsAndHashCode() {
        EqualsVerifier.simple().forClass(DelegatingUnit.class).withOnlyTheseFields("x")
            .withPrefabValues(BiPredicate.class, new UnitEqualsDummy(), new UnitEqualsDummy())
            .withPrefabValues(ToIntFunction.class, new UnitHashCodeDummy(), new UnitHashCodeDummy()).verify();
    }

    @Test
    void testToString() {
        val unit = new DelegatingUnit<>(0);
        var expected = "DelegatingUnit(x=0)";
        var actual = unit.toString();
        Assertions.assertEquals(expected, actual, "Default string not match");
        unit.setToStringFunc(u -> String.valueOf(u.getX()));
        expected = "0";
        actual = unit.toString();
        Assertions.assertEquals(expected, actual, "Delegated string not match");
    }
}
