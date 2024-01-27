package io.github.mjaroslav.sharedjava.function;

@FunctionalInterface
public interface ToIntTerFunction<T, U, V> {
    int applyAsInt(T t, U u, V v);
}
