package io.github.mjaroslav.sharedjava.tuple;

import io.github.mjaroslav.sharedjava.tuple.pair.*;

/**
 * Special interface to represent tuples with two values. Can be used as {@link Unit}.
 *
 * @param <X> type of x (first) value
 * @param <Y> type of y (second) value
 * @author MJaroslav
 * @see SimplePair
 * @see DelegatingPair
 * @see BPair
 * @see DPair
 * @see FPair
 * @see IPair
 * @see LPair
 * @see SPair
 * @since 1.0.0
 */
public interface Pair<X, Y> extends Unit<X> {
    /**
     * Get y (second) value.
     *
     * @return value; nullability depends on implementation
     * @since 1.0.0
     */
    Y getY();

    /**
     * Set y (second) value.
     *
     * @param y value; nullability depends on implementation
     * @since 1.0.0
     */
    void setY(Y y);

    /**
     * Set x (first) and y (second) values. You may not override this.
     *
     * @param x first value; nullability depends on implementation
     * @param y second value; nullability depends on implementation
     * @since 1.0.0
     */
    default void set(X x, Y y) {
        setX(x);
        setY(y);
    }
}
