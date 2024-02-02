package io.github.mjaroslav.sharedjava.tuple.pair;

import io.github.mjaroslav.sharedjava.tuple.Pair;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Simple implementation of {@link Pair}, contains: equals, hashCode and toString functionality.
 * Can contain null values and sets it by default constructor.
 *
 * @param <X> type of x (first) value
 * @param <Y> type of y (second) value
 * @author MJaroslav
 * @since 1.0.0
 */
@NoArgsConstructor
public @Data class SimplePair<X, Y> implements Pair<X, Y> {
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

    public SimplePair(X x, Y y) {
        set(x, y);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return this == obj || obj instanceof Pair<?, ?> pair &&
            Objects.equals(getX(), pair.getX()) && Objects.equals(getY(), pair.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
