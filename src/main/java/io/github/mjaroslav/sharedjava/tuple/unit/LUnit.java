package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * Simple implementation of {@link Unit} for Long values, contains: equals, hashCode and toString functionality.
 * Null values will be replaced with 0L.
 *
 * @author MJaroslav
 * @since 1.0.0
 */
public class LUnit extends SimpleUnit<Long> {
    public LUnit() {
        super(0L);
    }

    public LUnit(long x) {
        super(x);
    }

    @Override
    public @NotNull Long getX() {
        val x = super.getX();
        if (x == null) {
            setX(0L);
            return 0L;
        } else return x;
    }
}
