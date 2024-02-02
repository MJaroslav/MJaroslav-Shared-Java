package io.github.mjaroslav.sharedjava.tuple.triplet;

import io.github.mjaroslav.sharedjava.tuple.Triplet;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Simple implementation of {@link Triplet}, contains: equals, hashCode and toString functionality.
 * Can contain null values and sets it by default constructor.
 *
 * @param <X> type of x (first) value
 * @param <Y> type of y (second) value
 * @param <Z> type of z (third) value
 * @author MJaroslav
 * @since 1.0.0
 */
@NoArgsConstructor
public @Data class SimpleTriplet<X, Y, Z> implements Triplet<X, Y, Z> {
    /**
     * x (first) value, use setter and getter for it.
     *
     * @since 1.0.0
     */
    protected X x;
    /**
     * y (second) value, use setter and getter for it.
     *
     * @since 1.0.0
     */
    protected Y y;
    /**
     * z (third) value, use setter and getter for it.
     *
     * @since 1.0.0
     */
    protected Z z;

    public SimpleTriplet(X x, Y y, Z z) {
        set(x, y, z);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return this == obj || obj instanceof Triplet<?, ?, ?> triplet && Objects.equals(getX(), triplet.getX()) &&
            Objects.equals(getY(), triplet.getY()) && Objects.equals(getZ(), triplet.getZ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }
}
