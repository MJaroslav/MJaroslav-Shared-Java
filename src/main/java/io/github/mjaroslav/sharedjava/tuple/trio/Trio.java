package io.github.mjaroslav.sharedjava.tuple.trio;

import io.github.mjaroslav.sharedjava.tuple.pair.Pair;
import lombok.val;

public interface Trio<X, Y, Z> extends Pair<X, Y> {
    Z getZ();

    void setZ(Z z);

    default void set(X x, Y y, Z z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    @Override
    default String valueToString() {
        val x = getX();
        val y = getY();
        val z = getZ();
        return "x=" + (x != null ? x.toString() : "null") + ", y=" + (y != null ? y.toString() : "null") +
            ", z=" + (z != null ? z.toString() : "null");
    }
}
