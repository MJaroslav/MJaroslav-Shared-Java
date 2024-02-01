package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class STriplet extends SimpleTriplet<Short, Short, Short> {
    public STriplet() {
        super((short) 0, (short) 0, (short) 0);
    }

    public STriplet(short x, short y, short z) {
        super(x, y, z);
    }

    @Override
    public @NotNull Short getX() {
        val x = super.getX();
        if (x == null) {
            setX((short) 0);
            return (short) 0;
        } else return x;
    }

    @Override
    public @NotNull Short getY() {
        val y = super.getY();
        if (y == null) {
            setY((short) 0);
            return (short) 0;
        } else return y;
    }

    @Override
    public @NotNull Short getZ() {
        val z = super.getZ();
        if (z == null) {
            setZ((short) 0);
            return (short) 0;
        } else return z;
    }
}
