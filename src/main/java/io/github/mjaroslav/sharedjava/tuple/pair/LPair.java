package io.github.mjaroslav.sharedjava.tuple.pair;

import io.github.mjaroslav.sharedjava.tuple.Pair;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * Simple implementation of {@link Pair} for Long values, contains: equals, hashCode and toString functionality.
 * Null values will be replaced with 0L.
 *
 * @author MJaroslav
 * @since 1.0.0
 */
public class LPair extends SimplePair<Long, Long> {
    public LPair() {
        super(0L, 0L);
    }

    public LPair(long x, long y) {
        super(x, y);
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
}
