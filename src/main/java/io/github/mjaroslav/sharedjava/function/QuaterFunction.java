package io.github.mjaroslav.sharedjava.function;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@FunctionalInterface
public interface QuaterFunction<T, U, V, W, R> {
    R apply(T t, U u, V v, W w);

    @Contract(pure = true)
    default <Q> QuaterFunction<T, U, V, W, Q> andThen(@NotNull Function<? super R, ? extends Q> after) {
        return (T t, U u, V v, W w) -> after.apply(apply(t, u, v, w));
    }
}
