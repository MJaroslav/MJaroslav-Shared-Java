package io.github.mjaroslav.sharedjava.tuple;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;

@UtilityClass
public class TupleShared {
    public <T> void testGetX(@NotNull Unit<T> notNullValueUnit, @NotNull Unit<T> nullValueUnit, @NotNull T expected) {
        var actual = notNullValueUnit.getX();
        Assertions.assertEquals(expected, actual, "Not null value not match");
        actual = nullValueUnit.getX();
        Assertions.assertNull(actual, "Null value not null");
    }

    public <T> void testGetY(@NotNull Pair<?, T> notNullValuePair, @NotNull Pair<?, T> nullValuePair, @NotNull T expected) {
        var actual = notNullValuePair.getY();
        Assertions.assertEquals(expected, actual, "Not null value not match");
        actual = nullValuePair.getY();
        Assertions.assertNull(actual, "Null value not null");
    }

    public <T> void testGetZ(@NotNull Triplet<?, ?, T> notNullValueTriplet, @NotNull Triplet<?, ?, T> nullValueTriplet,
                             @NotNull T expected) {
        var actual = notNullValueTriplet.getZ();
        Assertions.assertEquals(expected, actual, "Not null value not match");
        actual = nullValueTriplet.getZ();
        Assertions.assertNull(actual, "Null value not null");
    }

    public <T> void testSetX(@NotNull Unit<T> nullValueUnit, @NotNull T expected) {
        var actual = nullValueUnit.getX();
        Assertions.assertNull(actual, "Null value not null");
        nullValueUnit.setX(expected);
        actual = nullValueUnit.getX();
        Assertions.assertEquals(expected, actual, "Not null value not match");
    }

    public <T> void testSetY(@NotNull Pair<?, T> nullValuePair, @NotNull T expected) {
        var actual = nullValuePair.getY();
        Assertions.assertNull(actual, "Null value not null");
        nullValuePair.setY(expected);
        actual = nullValuePair.getY();
        Assertions.assertEquals(expected, actual, "Not null value not match");
    }

    public <T> void testSetZ(@NotNull Triplet<?, ?, T> nullValueTriplet, @NotNull T expected) {
        var actual = nullValueTriplet.getZ();
        Assertions.assertNull(actual, "Null value not null");
        nullValueTriplet.setZ(expected);
        actual = nullValueTriplet.getZ();
        Assertions.assertEquals(expected, actual, "Not null value not match");
    }
}
