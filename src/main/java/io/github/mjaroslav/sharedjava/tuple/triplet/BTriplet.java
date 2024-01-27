package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;

public class BTriplet extends SimpleTriplet<Byte, Byte, Byte> {
    public BTriplet() {
        super((byte) 0, (byte) 0, (byte) 0);
    }

    public BTriplet(byte x, byte y, byte z) {
        super(x, y, z);
    }

    public byte x() {
        val x = getX();
        if (x == null) {
            setX((byte) 0);
            return (byte) 0;
        }
        return x;
    }

    public byte y() {
        val y = getY();
        if (y == null) {
            setY((byte) 0);
            return (byte) 0;
        }
        return y;
    }

    public byte z() {
        val z = getZ();
        if (z == null) {
            setZ((byte) 0);
            return (byte) 0;
        }
        return z;
    }
}
