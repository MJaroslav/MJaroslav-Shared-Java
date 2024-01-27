package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;

public class SPair extends SimplePair<Short, Short> {
    public SPair() {
        super((short) 0, (short) 0);
    }

    public SPair(short x, short y) {
        super(x, y);
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
}
