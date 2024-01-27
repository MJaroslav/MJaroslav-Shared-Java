package io.github.mjaroslav.sharedjava.tuple.trio;

import io.github.mjaroslav.sharedjava.function.QuaterPredicate;
import io.github.mjaroslav.sharedjava.function.TerFunction;
import io.github.mjaroslav.sharedjava.function.ToIntTerFunction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class DelegatingTrio<X, Y, Z> implements Trio<X, Y, Z> {
    protected X x;
    protected Y y;
    protected Z z;

    // TODO: May be just use Trio/Pair/Unit instead of unpacking generics?
    protected @Nullable TerFunction<X, Y, Z, String> toStringFunc;
    protected @Nullable ToIntTerFunction<X, Y, Z> hashCodeFunc;
    protected @Nullable QuaterPredicate<X, Y, Z, Object> equalsFunc;

    public DelegatingTrio(X x, Y y, Z z) {
        set(x, y, z);
    }

    @Contract("_ -> this")
    public DelegatingTrio<X, Y, Z> setToStringFunc(@Nullable TerFunction<X, Y, Z, String> toStringFunc) {
        this.toStringFunc = toStringFunc;
        return this;
    }

    @Contract("_ -> this")
    public DelegatingTrio<X, Y, Z> setHashCodeFunc(@Nullable ToIntTerFunction<X, Y, Z> hashCodeFunc) {
        this.hashCodeFunc = hashCodeFunc;
        return this;
    }

    @Contract("_ -> this")
    public DelegatingTrio<X, Y, Z> setEqualsFunc(@Nullable QuaterPredicate<X, Y, Z, Object> equalsFunc) {
        this.equalsFunc = equalsFunc;
        return this;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        val func = getEqualsFunc();
        return func != null ? func.test(getX(), getY(), getZ(), obj) :
            obj instanceof Trio<?, ?, ?> trio && Objects.equals(getX(), trio.getX()) &&
                Objects.equals(getY(), trio.getY()) && Objects.equals(getZ(), trio.getZ());
    }

    @Override
    public int hashCode() {
        val func = getHashCodeFunc();
        return func != null ? func.applyAsInt(getX(), getY(), getZ()) : Objects.hash(getX(), getY(), getZ());
    }

    @Override
    public String toString() {
        val func = getToStringFunc();
        return this.getClass().getSimpleName() + "(" + (func != null ? func.apply(getX(), getY(), getZ())
            : valueToString()) + ")";
    }
}
