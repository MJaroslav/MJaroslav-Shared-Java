package io.github.mjaroslav.sharedjava.tuple;


import io.github.mjaroslav.sharedjava.tuple.unit.*;

/**
 * Special interface to represent tuples with one value.
 *
 * @param <X> type of x (first) value
 * @author MJaroslav
 * @see SimpleUnit
 * @see DelegatingUnit
 * @see BUnit
 * @see DUnit
 * @see FUnit
 * @see IUnit
 * @see LUnit
 * @see SUnit
 * @since 1.0.0
 */
public interface Unit<X> {
    /**
     * Get x (first) value.
     *
     * @return value; nullability depends on implementation
     * @since 1.0.0
     */
    X getX();

    /**
     * Set x (first) value.
     *
     * @param x value; nullability depends on implementation
     * @since 1.0.0
     */
    void setX(X x);
}
