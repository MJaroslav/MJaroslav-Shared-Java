package io.github.mjaroslav.sharedjava.tuple.pair;

import io.github.mjaroslav.sharedjava.tuple.unit.Unit;
import lombok.val;

public interface Pair<X, Y> extends Unit<X> {
    Y getY();

    void setY(Y y);

    default void set(X x, Y y) {
        setX(x);
        setY(y);
    }

    @Override
    default String valueToString() {
        val x = getX();
        val y = getY();
        return "x=" + (x != null ? x.toString() : "null") + ", y=" + (y != null ? y.toString() : "null");
    }
}
