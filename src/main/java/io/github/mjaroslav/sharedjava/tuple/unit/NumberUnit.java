package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberUnit<T extends Number> extends SimpleUnit<T> {
    public NumberUnit(T x) {
        setX(x);
    }
}
