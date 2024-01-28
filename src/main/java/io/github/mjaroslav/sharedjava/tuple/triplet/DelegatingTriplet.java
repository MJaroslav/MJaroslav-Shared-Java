package io.github.mjaroslav.sharedjava.tuple.triplet;

import io.github.mjaroslav.sharedjava.tuple.Triplet;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.ToIntFunction;

@NoArgsConstructor
public @Data class DelegatingTriplet<X, Y, Z> implements Triplet<X, Y, Z> {
    protected X x;
    protected Y y;
    protected Z z;

    protected @Nullable Function<Triplet<X, Y, Z>, String> toStringFunc;
    protected @Nullable ToIntFunction<Triplet<X, Y, Z>> hashCodeFunc;
    protected @Nullable BiPredicate<Triplet<X, Y, Z>, Object> equalsFunc;

    public DelegatingTriplet(X x, Y y, Z z) {
        set(x, y, z);
    }

    @Contract("_ -> this")
    public DelegatingTriplet<X, Y, Z> setToStringFunc(@Nullable Function<Triplet<X, Y, Z>, String> toStringFunc) {
        this.toStringFunc = toStringFunc;
        return this;
    }

    @Contract("_ -> this")
    public DelegatingTriplet<X, Y, Z> setHashCodeFunc(@Nullable ToIntFunction<Triplet<X, Y, Z>> hashCodeFunc) {
        this.hashCodeFunc = hashCodeFunc;
        return this;
    }

    @Contract("_ -> this")
    public DelegatingTriplet<X, Y, Z> setEqualsFunc(@Nullable BiPredicate<Triplet<X, Y, Z>, Object> equalsFunc) {
        this.equalsFunc = equalsFunc;
        return this;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        val func = getEqualsFunc();
        return func != null ? func.test(this, obj) : this == obj || obj instanceof Triplet<?, ?, ?> triplet &&
            Objects.equals(getX(), triplet.getX()) && Objects.equals(getY(), triplet.getY()) &&
            Objects.equals(getZ(), triplet.getZ());
    }

    @Override
    public int hashCode() {
        val func = getHashCodeFunc();
        return func != null ? func.applyAsInt(this) : Objects.hash(getX(), getY(), getZ());
    }
}
