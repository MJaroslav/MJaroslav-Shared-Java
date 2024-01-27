package io.github.mjaroslav.sharedjava.function;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

@FunctionalInterface
public interface TerFunction<T, U, V, R> {
    R apply(T t, U u, V v);

    @Contract(pure = true)
    default <Q> TerFunction<T, U, V, Q> andThen(@NotNull Function<? super R, ? extends Q> after) {
        return (T t, U u, V v) -> after.apply(apply(t, u, v));
    }
}
