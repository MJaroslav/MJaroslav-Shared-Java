package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;

public interface Unit<X> {
    X getX();

    void setX(X x);

    default String valueToString() {
        val x = getX();
        return "x=" + (x != null ? x.toString() : "null");
    }
}
