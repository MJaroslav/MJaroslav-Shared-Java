package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;

public class STriplet extends SimpleTriplet<Short, Short, Short> {
    public STriplet() {
        super((short) 0, (short) 0, (short) 0);
    }

    public STriplet(short x, short y, short z) {
        super(x, y, z);
    }

    public short x() {
        val x = getX();
        if (x == null) {
            setX((short) 0);
            return (short) 0;
        }
        return x;
    }

    public short y() {
        val y = getY();
        if (y == null) {
            setY((short) 0);
            return (short) 0;
        }
        return y;
    }

    public short z() {
        val z = getZ();
        if (z == null) {
            setZ((short) 0);
            return (short) 0;
        }
        return z;
    }
}
