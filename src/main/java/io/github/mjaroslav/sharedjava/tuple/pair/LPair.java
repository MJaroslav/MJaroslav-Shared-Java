package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;

public class LPair extends SimplePair<Long, Long> {
    public LPair() {
        super(0L, 0L);
    }

    public LPair(long x, long y) {
        super(x, y);
    }

    public long x() {
        val x = getX();
        if (x == null) {
            setX(0L);
            return 0L;
        }
        return x;
    }

    public long y() {
        val y = getY();
        if (y == null) {
            setY(0L);
            return 0L;
        }
        return y;
    }
}
