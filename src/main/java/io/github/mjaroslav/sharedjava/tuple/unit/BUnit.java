package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * Simple implementation of {@link Unit} for Byte values, contains: equals, hashCode and toString functionality.
 * Null values will be replaced with (byte) 0.
 *
 * @author MJaroslav
 * @since 1.0.0
 */
public class BUnit extends SimpleUnit<Byte> {
    public BUnit() {
        super((byte) 0);
    }

    public BUnit(byte x) {
        super(x);
    }

    @Override
    public @NotNull Byte getX() {
        val x = super.getX();
        if (x == null) {
            setX((byte) 0);
            return (byte) 0;
        } else return x;
    }
}
