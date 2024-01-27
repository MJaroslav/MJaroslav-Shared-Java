package io.github.mjaroslav.sharedjava.tuple.trio;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberTrio<X extends Number, Y extends Number, Z extends Number> extends SimpleTrio<X, Y, Z> {
    public NumberTrio(X x, Y y, Z z) {
        super(x, y, z);
    }
}
