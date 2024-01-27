package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MonoNumberPair<T extends Number> extends NumberPair<T, T> implements MonoPair<T> {
    public MonoNumberPair(T x, T y) {
        super(x, y);
    }
}
