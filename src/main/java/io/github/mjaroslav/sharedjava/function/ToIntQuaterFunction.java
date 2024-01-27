package io.github.mjaroslav.sharedjava.function;

@FunctionalInterface
public interface ToIntQuaterFunction<T, U, V, W> {
    int applyAsInt(T t, U u, V v, W w);
}
