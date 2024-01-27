package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;

public class BPair extends SimplePair<Byte, Byte> {
    public BPair() {
        super((byte) 0, (byte) 0);
    }

    public BPair(byte x, byte y) {
        super(x, y);
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
}
