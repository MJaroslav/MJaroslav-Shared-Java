package io.github.mjaroslav.sharedjava.tuple.triplet;

import io.github.mjaroslav.sharedjava.tuple.Triplet;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * Simple implementation of {@link Triplet} for Byte values, contains: equals, hashCode and toString functionality.
 * Null values will be replaced with (byte) 0.
 *
 * @author MJaroslav
 * @since 0.1.0
 */
public class BTriplet extends SimpleTriplet<Byte, Byte, Byte> {
    public BTriplet() {
        super((byte) 0, (byte) 0, (byte) 0);
    }

    public BTriplet(byte x, byte y, byte z) {
        super(x, y, z);
    }

    @Override
    public @NotNull Byte getX() {
        val x = super.getX();
        if (x == null) {
            setX((byte) 0);
            return (byte) 0;
        } else return x;
    }

    @Override
    public @NotNull Byte getY() {
        val y = super.getY();
        if (y == null) {
            setY((byte) 0);
            return (byte) 0;
        } else return y;
    }

    @Override
    public @NotNull Byte getZ() {
        val z = super.getZ();
        if (z == null) {
            setZ((byte) 0);
            return (byte) 0;
        } else return z;
    }
}
