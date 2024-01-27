package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;

public class ITriplet extends SimpleTriplet<Integer, Integer, Integer> {
    public ITriplet() {
        super(0, 0, 0);
    }

    public ITriplet(int x, int y, int z) {
        super(x, y, z);
    }

    public int x() {
        val x = getX();
        if (x == null) {
            setX(0);
            return 0;
        }
        return x;
    }

    public int y() {
        val y = getY();
        if (y == null) {
            setY(0);
            return 0;
        }
        return y;
    }

    public int z() {
        val z = getZ();
        if (z == null) {
            setZ(0);
            return 0;
        }
        return z;
    }
}
