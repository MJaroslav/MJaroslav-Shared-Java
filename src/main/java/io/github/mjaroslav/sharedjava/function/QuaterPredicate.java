package io.github.mjaroslav.sharedjava.function;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface QuaterPredicate<T, U, V, W> {
    boolean test(T t, U u, V v, W w);

    @Contract(pure = true)
    default QuaterPredicate<T, U, V, W> and(@NotNull QuaterPredicate<? super T, ? super U, ? super V, ? super W> other) {
        return (T t, U u, V v, W w) -> test(t, u, v, w) && other.test(t, u, v, w);
    }

    @Contract(pure = true)
    default QuaterPredicate<T, U, V, W> negate() {
        return (T t, U u, V v, W w) -> !test(t, u, v, w);
    }

    @Contract(pure = true)
    default QuaterPredicate<T, U, V, W> or(@NotNull QuaterPredicate<? super T, ? super U, ? super V, ? super W> other) {
        return (T t, U u, V v, W w) -> test(t, u, v, w) || other.test(t, u, v, w);
    }
}
