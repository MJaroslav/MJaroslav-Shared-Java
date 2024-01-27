package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;

public class SUnit extends SimpleUnit<Short> {
    public SUnit() {
        super((short) 0);
    }

    public SUnit(short x) {
        super(x);
    }

    public short x() {
        val x = getX();
        if (x == null) {
            setX((short) 0);
            return (short) 0;
        }
        return x;
    }
}
