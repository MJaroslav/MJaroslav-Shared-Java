package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SimpleMonoPair<T> extends SimplePair<T, T> implements MonoPair<T> {
    public SimpleMonoPair(T x, T y) {
        set(x, y);
    }
}
