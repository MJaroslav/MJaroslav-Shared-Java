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

@NoArgsConstructor
public @Data class DelegatingPair<X, Y> implements Pair<X, Y> {
    protected X x;
    protected Y y;

    protected @Nullable Function<Pair<X, Y>, String> toStringFunc;
    protected @Nullable ToIntFunction<Pair<X, Y>> hashCodeFunc;
    protected @Nullable BiPredicate<Pair<X, Y>, Object> equalsFunc;

    public DelegatingPair(X x, Y y) {
        set(x, y);
    }

    @SuppressWarnings("UnusedReturnValue")
    @Contract("_ -> this")
    public DelegatingPair<X, Y> setToStringFunc(@Nullable Function<Pair<X, Y>, String> toStringFunc) {
        this.toStringFunc = toStringFunc;
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    @Contract("_ -> this")
    public DelegatingPair<X, Y> setHashCodeFunc(@Nullable ToIntFunction<Pair<X, Y>> hashCodeFunc) {
        this.hashCodeFunc = hashCodeFunc;
        return this;
    }

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
