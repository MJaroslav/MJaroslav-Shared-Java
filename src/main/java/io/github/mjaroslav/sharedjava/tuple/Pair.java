package io.github.mjaroslav.sharedjava.tuple;

public interface Pair<X, Y> extends Unit<X> {
    Y getY();

    void setY(Y y);

    default void set(X x, Y y) {
        setX(x);
        setY(y);
    }
}
