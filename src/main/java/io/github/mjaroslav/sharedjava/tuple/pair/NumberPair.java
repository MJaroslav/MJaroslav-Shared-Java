package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberPair<X extends Number, Y extends Number> extends SimplePair<X, Y> {
    public NumberPair(X x, Y y) {
        super(x, y);
    }
}
