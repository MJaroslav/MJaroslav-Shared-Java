package io.github.mjaroslav.sharedjava.tuple.pair;

import io.github.mjaroslav.sharedjava.tuple.Pair;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * Simple implementation of {@link Pair} for Byte values, contains: equals, hashCode and toString functionality.
 * Null values will be replaced with (byte) 0.
 *
 * @author MJaroslav
 * @since 0.1.0
 */
public class BPair extends SimplePair<Byte, Byte> {
    public BPair() {
        super((byte) 0, (byte) 0);
    }

    public BPair(byte x, byte y) {
        super(x, y);
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
}
