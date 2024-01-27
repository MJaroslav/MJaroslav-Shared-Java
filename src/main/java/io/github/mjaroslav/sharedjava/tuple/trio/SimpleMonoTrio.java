package io.github.mjaroslav.sharedjava.tuple.trio;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SimpleMonoTrio<T> extends SimpleTrio<T, T, T> implements MonoTrio<T> {
    public SimpleMonoTrio(T x, T y, T z) {
        super(x, y, z);
    }
}
