package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;

public class LTriplet  extends SimpleTriplet<Long, Long, Long> {
    public LTriplet() {
        super(0L, 0L, 0L);
    }

    public LTriplet(long x, long y, long z) {
        super(x, y, z);
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

    public long z() {
        val z = getZ();
        if (z == null) {
            setZ(0L);
            return 0L;
        }
        return z;
    }
}
