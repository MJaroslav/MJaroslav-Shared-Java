package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.Unit;
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
 * Implementation of {@link Unit}, allows you delegating hashCode, equals and toString by functions.
 * Can contain null values and sets it by default constructor.
 *
 * @param <X> type of x (first) value
 * @author MJaroslav
 * @since 0.1.0
 */
@NoArgsConstructor
public @Data class DelegatingUnit<X> implements Unit<X> {
    /**
     * x (first) value, use setter and getter for it.
     *
     * @since 0.1.0
     */
    protected X x;

    /**
     * Delegating function for toString; Will use default behavior if null.
     * Use setter and getter for it.
     *
     * @since 0.1.0
     */
    protected @Nullable Function<Unit<X>, String> toStringFunc;
    /**
     * Delegating function for hashCode; Will use default behavior if null.
     * Use setter and getter for it.
     *
     * @since 0.1.0
     */
    protected @Nullable ToIntFunction<Unit<X>> hashCodeFunc;
    /**
     * Delegating function for equals; Will use default behavior if null.
     * Use setter and getter for it.
     *
     * @since 0.1.0
     */
    protected @Nullable BiPredicate<Unit<X>, Object> equalsFunc;

    public DelegatingUnit(X x) {
        setX(x);
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
    public DelegatingUnit<X> setToStringFunc(@Nullable Function<Unit<X>, String> toStringFunc) {
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
    public DelegatingUnit<X> setHashCodeFunc(@Nullable ToIntFunction<Unit<X>> hashCodeFunc) {
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
    public DelegatingUnit<X> setEqualsFunc(@Nullable BiPredicate<Unit<X>, Object> equalsFunc) {
        this.equalsFunc = equalsFunc;
        return this;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        val func = getEqualsFunc();
        return func != null ? func.test(this, obj) : this == obj || obj instanceof Unit<?> unit &&
            Objects.equals(getX(), unit.getX());
    }

    @Override
    public int hashCode() {
        val func = getHashCodeFunc();
        return func != null ? func.applyAsInt(this) : Objects.hash(getX());
    }

    @Override
    public String toString() {
        val func = getToStringFunc();
        return func != null ? func.apply(this) : "DelegatingUnit(x=" + this.getX() + ")";
    }
}
