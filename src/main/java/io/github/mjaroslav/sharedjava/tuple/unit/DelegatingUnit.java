package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.ToIntFunction;

@Getter
@Setter
@NoArgsConstructor
public class DelegatingUnit<X> implements Unit<X> {
    protected X x;

    protected @Nullable Function<Unit<X>, String> toStringFunc;
    protected @Nullable ToIntFunction<Unit<X>> hashCodeFunc;
    protected @Nullable BiPredicate<Unit<X>, Object> equalsFunc;

    public DelegatingUnit(X x) {
        setX(x);
    }

    @Contract("_ -> this")
    public DelegatingUnit<X> setToStringFunc(@Nullable Function<Unit<X>, String> toStringFunc) {
        this.toStringFunc = toStringFunc;
        return this;
    }

    @Contract("_ -> this")
    public DelegatingUnit<X> setHashCodeFunc(@Nullable ToIntFunction<Unit<X>> hashCodeFunc) {
        this.hashCodeFunc = hashCodeFunc;
        return this;
    }

    @Contract("_ -> this")
    public DelegatingUnit<X> setEqualsFunc(@Nullable BiPredicate<Unit<X>, Object> equalsFunc) {
        this.equalsFunc = equalsFunc;
        return this;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        val func = getEqualsFunc();
        return func != null && func.test(this, obj) || Objects.equals(getX(), obj instanceof Unit<?> unit ? unit.getX() : obj);
    }

    @Override
    public int hashCode() {
        val func = getHashCodeFunc();
        return func != null ? func.applyAsInt(this) : Objects.hashCode(getX());
    }

    @Override
    public String toString() {
        val func = getToStringFunc();
        return this.getClass().getSimpleName() + "(" + (func != null ? func.apply(this) : valueToString()) + ")";
    }
}
