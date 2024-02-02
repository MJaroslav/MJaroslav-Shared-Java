package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * Simple implementation of {@link Unit} for Short values, contains: equals, hashCode and toString functionality.
 * Null values will be replaced with (short) 0.
 *
 * @author MJaroslav
 * @since 1.0.0
 */
public class SUnit extends SimpleUnit<Short> {
    public SUnit() {
        super((short) 0);
    }

    public SUnit(short x) {
        super(x);
    }

    @Override
    public @NotNull Short getX() {
        val x = super.getX();
        if (x == null) {
            setX((short) 0);
            return (short) 0;
        } else return x;
    }
}
