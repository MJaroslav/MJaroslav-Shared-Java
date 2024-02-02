package io.github.mjaroslav.sharedjava.tuple.triplet;

import io.github.mjaroslav.sharedjava.tuple.Triplet;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * Simple implementation of {@link Triplet} for Long values, contains: equals, hashCode and toString functionality.
 * Null values will be replaced with 0L.
 *
 * @author MJaroslav
 * @since 1.0.0
 */
public class LTriplet extends SimpleTriplet<Long, Long, Long> {
    public LTriplet() {
        super(0L, 0L, 0L);
    }

    public LTriplet(long x, long y, long z) {
        super(x, y, z);
    }

    @Override
    public @NotNull Long getX() {
        val x = super.getX();
        if (x == null) {
            setX(0L);
            return 0L;
        } else return x;
    }

    @Override
    public @NotNull Long getY() {
        val y = super.getY();
        if (y == null) {
            setY(0L);
            return 0L;
        } else return y;
    }

    @Override
    public @NotNull Long getZ() {
        val z = super.getZ();
        if (z == null) {
            setZ(0L);
            return 0L;
        } else return z;
    }
}
