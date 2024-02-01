package io.github.mjaroslav.sharedjava.tuple.triplet;

import io.github.mjaroslav.sharedjava.tuple.TupleShared;
import lombok.val;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

class DelegatingTripletTest {
    @Test
    void getX() {
        val expected = 1;
        val notNullValueTriplet = new DelegatingTriplet<>(expected, null, null);
        val nullValueTriplet = new DelegatingTriplet<Integer, Object, Object>();
        TupleShared.testGetX(notNullValueTriplet, nullValueTriplet, expected);
    }

    @Test
    void getY() {
        val expected = 1;
        val notNullValueTriplet = new DelegatingTriplet<>(null, expected, null);
        val nullValueTriplet = new DelegatingTriplet<Object, Integer, Object>();
        TupleShared.testGetY(notNullValueTriplet, nullValueTriplet, expected);
    }

    @Test
    void getZ() {
        val expected = 1;
        val notNullValueTriplet = new DelegatingTriplet<>(null, null, expected);
        val nullValueTriplet = new DelegatingTriplet<Object, Object, Integer>();
        TupleShared.testGetZ(notNullValueTriplet, nullValueTriplet, expected);
    }

    @Test
    void setX() {
        val expected = true;
        val nullValueTriplet = new DelegatingTriplet<Boolean, Object, Object>();
        TupleShared.testSetX(nullValueTriplet, expected);
    }

    @Test
    void setY() {
        val expected = true;
        val nullValueTriplet = new DelegatingTriplet<Object, Boolean, Object>();
        TupleShared.testSetY(nullValueTriplet, expected);
    }

    @Test
    void setZ() {
        val expected = true;
        val nullValueTriplet = new DelegatingTriplet<Object, Object, Boolean>();
        TupleShared.testSetZ(nullValueTriplet, expected);
    }

    @Test
    void equalsAndHashCode() {
        EqualsVerifier.simple().forClass(DelegatingTriplet.class).withOnlyTheseFields("x", "y", "z")
            .withPrefabValues(BiPredicate.class, new TripletEqualsDummy(), new TripletEqualsDummy())
            .withPrefabValues(ToIntFunction.class, new TripletHashCodeDummy(), new TripletHashCodeDummy()).verify();
    }

    @Test
    void testToString() {
        val unit = new DelegatingTriplet<>(0, 1, -1);
        var expected = "DelegatingTriplet(x=0, y=1, z=-1)";
        var actual = unit.toString();
        Assertions.assertEquals(expected, actual, "Default string not match");
        unit.setToStringFunc(t -> t.getX() + "x" + t.getY() + "x" + t.getZ());
        expected = "0x1x-1";
        actual = unit.toString();
        Assertions.assertEquals(expected, actual, "Delegated string not match");
    }
}
