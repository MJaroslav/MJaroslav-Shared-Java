package io.github.mjaroslav.sharedjava.tuple.pair;

import io.github.mjaroslav.sharedjava.tuple.Pair;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * Implementation of {@link Pair}, allows you delegating hashCode, equals and toString by functions.
 * Can contain null values and sets it by default constructor.
 *
 * @param <X> type of x (first) value
 * @param <Y> type of y (second) value
 * @author MJaroslav
 * @since 0.1.0
 */
@NoArgsConstructor
public @Data class DelegatingPair<X, Y> implements Pair<X, Y> {
    /**
     * x (first) value, use setter and getter for it.
     *
     * @since 0.1.0
     */
    protected X x;
    /**
     * y (second) value, use setter and getter for it.
     *
     * @since 0.1.0
     */
    protected Y y;

    /**
     * Delegating function for toString; Will use default behavior if null.
     * Use setter and getter for it.
     *
     * @since 0.1.0
     */
    protected @Nullable Function<Pair<X, Y>, String> toStringFunc;
    /**
     * Delegating function for hashCode; Will use default behavior if null.
     * Use setter and getter for it.
     *
     * @since 0.1.0
     */
    protected @Nullable ToIntFunction<Pair<X, Y>> hashCodeFunc;
    /**
     * Delegating function for equals; Will use default behavior if null.
     * Use setter and getter for it.
     *
     * @since 0.1.0
     */
    protected @Nullable BiPredicate<Pair<X, Y>, Object> equalsFunc;

    public DelegatingPair(X x, Y y) {
        set(x, y);
    }

    /**
     * Sets or remove (null value) toString delegating function.
     *
     * @param toStringFunc function for toString delegating; use null for default behavior
     * @return this Unit object
     * @since 0.1.0
     */
    @SuppressWarnings("UnusedReturnValue")
    @Contract("_ -> this")
    public DelegatingPair<X, Y> setToStringFunc(@Nullable Function<Pair<X, Y>, String> toStringFunc) {
        this.toStringFunc = toStringFunc;
        return this;
    }

    /**
     * Sets or remove (null value) hashCode delegating function.
     *
     * @param hashCodeFunc function for hashCode delegating; use null for default behavior
     * @return this Unit object
     * @since 0.1.0
     */
    @SuppressWarnings("UnusedReturnValue")
    @Contract("_ -> this")
    public DelegatingPair<X, Y> setHashCodeFunc(@Nullable ToIntFunction<Pair<X, Y>> hashCodeFunc) {
        this.hashCodeFunc = hashCodeFunc;
        return this;
    }

    /**
     * Sets or remove (null value) equals delegating function.
     *
     * @param equalsFunc function for equals delegating; use null for default behavior
     * @return this Unit object
     * @since 0.1.0
     */
    @SuppressWarnings("UnusedReturnValue")
    @Contract("_ -> this")
    public DelegatingPair<X, Y> setEqualsFunc(@Nullable BiPredicate<Pair<X, Y>, Object> equalsFunc) {
        this.equalsFunc = equalsFunc;
        return this;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        val func = getEqualsFunc();
        return func != null ? func.test(this, obj) : this == obj || obj instanceof Pair<?, ?> pair &&
            Objects.equals(getX(), pair.getX()) && Objects.equals(getY(), pair.getY());
    }

    @Override
    public int hashCode() {
        val func = getHashCodeFunc();
        return func != null ? func.applyAsInt(this) : Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        val func = getToStringFunc();
        return func != null ? func.apply(this) : "DelegatingPair(x=" + this.getX() + ", y=" + this.getY() + ")";
    }
}
