package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;

public class BUnit extends SimpleUnit<Byte> {
    public BUnit() {
        super((byte) 0);
    }

    public BUnit(byte x) {
        super(x);
    }

    public byte x() {
        val x = getX();
        if (x == null) {
            setX((byte) 0);
            return (byte) 0;
        }
        return x;
    }
}
