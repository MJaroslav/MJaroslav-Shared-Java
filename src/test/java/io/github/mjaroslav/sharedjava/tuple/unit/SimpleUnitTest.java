package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.TupleShared;
import lombok.val;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class SimpleUnitTest {
    @Test
    void getX() {
        val expected = 1;
        val notNullValueUnit = new SimpleUnit<>(expected);
        val nullValueUnit = new SimpleUnit<Integer>();
        TupleShared.testGetX(notNullValueUnit, nullValueUnit, expected);
    }

    @Test
    void setX() {
        val expected = true;
        val nullValueUnit = new SimpleUnit<Boolean>();
        TupleShared.testSetX(nullValueUnit, expected);
    }

    @Test
    void equalsAndHashCode() {
        EqualsVerifier.simple().forClass(SimpleUnit.class).verify();
    }
}
