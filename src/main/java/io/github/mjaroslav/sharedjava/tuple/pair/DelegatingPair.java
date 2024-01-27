package io.github.mjaroslav.sharedjava.tuple.pair;

import io.github.mjaroslav.sharedjava.function.TerPredicate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

@Getter
@Setter
@NoArgsConstructor
public class DelegatingPair<X, Y> implements Pair<X, Y> {
    protected X x;
    protected Y y;

    protected @Nullable BiFunction<X, Y, String> toStringFunc;
    protected @Nullable ToIntBiFunction<X, Y> hashCodeFunc;
    protected @Nullable TerPredicate<X, Y, Object> equalsFunc;

    public DelegatingPair(X x, Y y) {
        set(x, y);
    }

    @Contract("_ -> this")
    public DelegatingPair<X, Y> setToStringFunc(@Nullable BiFunction<X, Y, String> toStringFunc) {
        this.toStringFunc = toStringFunc;
        return this;
    }

    @Contract("_ -> this")
    public DelegatingPair<X, Y> setHashCodeFunc(@Nullable ToIntBiFunction<X, Y> hashCodeFunc) {
        this.hashCodeFunc = hashCodeFunc;
        return this;
    }

    @Contract("_ -> this")
    public DelegatingPair<X, Y> setEqualsFunc(@Nullable TerPredicate<X, Y, Object> equalsFunc) {
        this.equalsFunc = equalsFunc;
        return this;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        val func = getEqualsFunc();
        return func != null ? func.test(getX(), getY(), obj) :
            obj instanceof Pair<?, ?> pair && Objects.equals(getX(), pair.getX()) && Objects.equals(getY(), pair.getY());
    }

    @Override
    public int hashCode() {
        val func = getHashCodeFunc();
        return func != null ? func.applyAsInt(getX(), getY()) : Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        val func = getToStringFunc();
        return this.getClass().getSimpleName() + "(" + (func != null ? func.apply(getX(), getY()) : valueToString()) + ")";
    }
}
