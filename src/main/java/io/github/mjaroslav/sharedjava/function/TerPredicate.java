package io.github.mjaroslav.sharedjava.function;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface TerPredicate<T, U, V> {
    boolean test(T t, U u, V v);

    @Contract(pure = true)
    default TerPredicate<T, U, V> and(@NotNull TerPredicate<? super T, ? super U, ? super V> other) {
        return (T t, U u, V v) -> test(t, u, v) && other.test(t, u, v);
    }

    @Contract(pure = true)
    default TerPredicate<T, U, V> negate() {
        return (T t, U u, V v) -> !test(t, u, v);
    }

    @Contract(pure = true)
    default TerPredicate<T, U, V> or(@NotNull TerPredicate<? super T, ? super U, ? super V> other) {
        return (T t, U u, V v) -> test(t, u, v) || other.test(t, u, v);
    }
}
