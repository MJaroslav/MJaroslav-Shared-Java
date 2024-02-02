package io.github.mjaroslav.sharedjava.tuple.pair;

import io.github.mjaroslav.sharedjava.tuple.Pair;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * Simple implementation of {@link Pair} for Double values, contains: equals, hashCode and toString functionality.
 * Null values will be replaced with 0D.
 *
 * @author MJaroslav
 * @since 1.0.0
 */
public class DPair extends SimplePair<Double, Double> {
    public DPair() {
        super(0D, 0D);
    }

    public DPair(double x, double y) {
        super(x, y);
    }

    @Override
    public @NotNull Double getX() {
        val x = super.getX();
        if (x == null) {
            setX(0D);
            return 0D;
        } else return x;
    }

    @Override
    public @NotNull Double getY() {
        val y = super.getY();
        if (y == null) {
            setY(0D);
            return 0D;
        } else return y;
    }
}
