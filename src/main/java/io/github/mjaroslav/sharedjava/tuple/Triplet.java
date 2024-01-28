package io.github.mjaroslav.sharedjava.tuple;

public interface Triplet<X, Y, Z> extends Pair<X, Y> {
    Z getZ();

    void setZ(Z z);

    default void set(X x, Y y, Z z) {
        setX(x);
        setY(y);
        setZ(z);
    }
}
