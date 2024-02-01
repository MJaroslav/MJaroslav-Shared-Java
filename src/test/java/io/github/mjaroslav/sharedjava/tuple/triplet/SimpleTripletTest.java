package io.github.mjaroslav.sharedjava.tuple.triplet;

import io.github.mjaroslav.sharedjava.tuple.TupleShared;
import lombok.val;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleTripletTest {
    @Test
    void getX() {
        val expected = 1;
        val notNullValueTriplet = new SimpleTriplet<>(expected, null, null);
        val nullValueTriplet = new SimpleTriplet<Integer, Object, Object>();
        TupleShared.testGetX(notNullValueTriplet, nullValueTriplet, expected);
    }

    @Test
    void getY() {
        val expected = 1;
        val notNullValueTriplet = new SimpleTriplet<>(null, expected, null);
        val nullValueTriplet = new SimpleTriplet<Object, Integer, Object>();
        TupleShared.testGetY(notNullValueTriplet, nullValueTriplet, expected);
    }

    @Test
    void getZ() {
        val expected = 1;
        val notNullValueTriplet = new SimpleTriplet<>(null, null, expected);
        val nullValueTriplet = new SimpleTriplet<Object, Object, Integer>();
        TupleShared.testGetZ(notNullValueTriplet, nullValueTriplet, expected);
    }

    @Test
    void setX() {
        val expected = true;
        val nullValueTriplet = new SimpleTriplet<Boolean, Object, Object>();
        TupleShared.testSetX(nullValueTriplet, expected);
    }

    @Test
    void setY() {
        val expected = true;
        val nullValueTriplet = new SimpleTriplet<Object, Boolean, Object>();
        TupleShared.testSetY(nullValueTriplet, expected);
    }

    @Test
    void setZ() {
        val expected = true;
        val nullValueTriplet = new SimpleTriplet<Object, Object, Boolean>();
        TupleShared.testSetZ(nullValueTriplet, expected);
    }

    @Test
    void equalsAndHashCode() {
        EqualsVerifier.simple().forClass(SimpleTriplet.class).verify();
    }

    @Test
    void testToString() {
        val triplet = new SimpleTriplet<>(1, 0, -1);
        val expected = "SimpleTriplet(x=1, y=0, z=-1)";
        val actual = triplet.toString();
        Assertions.assertEquals(expected, actual, "String not match");
    }
}
