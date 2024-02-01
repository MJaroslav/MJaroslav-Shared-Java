package io.github.mjaroslav.sharedjava.tuple;

import io.github.mjaroslav.sharedjava.tuple.triplet.*;

/**
 * @param <X> type of x (first) value
 * @param <Y> type of y (second) value
 * @param <Z> type of z (third) value
 * @author MJaroslav
 * @see SimpleTriplet
 * @see DelegatingTriplet
 * @see BTriplet
 * @see DTriplet
 * @see FTriplet
 * @see ITriplet
 * @see LTriplet
 * @see STriplet
 * @since 0.1.0
 */
public interface Triplet<X, Y, Z> extends Pair<X, Y> {
    /**
     * Get z (third) value.
     *
     * @return value; nullability depends on implementation
     * @since 0.1.0
     */
    Z getZ();

    /**
     * Set z (third) value.
     *
     * @param z value; nullability depends on implementation
     * @since 0.1.0
     */
    void setZ(Z z);

    /**
     * Set x (first), y (second) and z (third) values. You may not override this.
     *
     * @param x first value; nullability depends on implementation
     * @param y second value; nullability depends on implementation
     * @param z third value; nullability depends on implementation
     * @since 0.1.0
     */
    default void set(X x, Y y, Z z) {
        setX(x);
        setY(y);
        setZ(z);
    }
}
