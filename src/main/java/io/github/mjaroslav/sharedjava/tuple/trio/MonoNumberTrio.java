package io.github.mjaroslav.sharedjava.tuple.trio;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MonoNumberTrio<T extends Number> extends NumberTrio<T, T, T> implements MonoTrio<T> {
    public MonoNumberTrio(T x, T y, T z) {
        super(x, y, z);
    }
}
