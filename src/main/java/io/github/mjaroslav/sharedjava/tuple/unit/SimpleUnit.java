package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Simple implementation of {@link Unit}, contains: equals, hashCode and toString functionality.
 * Can contain null values and sets it by default constructor.
 *
 * @param <X> type of x (first) value
 * @author MJaroslav
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor
public @Data class SimpleUnit<X> implements Unit<X> {
    /**
     * x (first) value, use setter and getter for it.
     *
     * @since 1.0.0
     */
    protected X x;

    @Override
    public boolean equals(@Nullable Object obj) {
        return this == obj || obj instanceof Unit<?> unit && Objects.equals(getX(), unit.getX());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX());
    }
}
