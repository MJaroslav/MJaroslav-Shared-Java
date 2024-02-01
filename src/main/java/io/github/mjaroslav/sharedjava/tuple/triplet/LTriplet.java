package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class LTriplet extends SimpleTriplet<Long, Long, Long> {
    public LTriplet() {
        super(0L, 0L, 0L);
    }

    public LTriplet(long x, long y, long z) {
        super(x, y, z);
    }

    @Override
    public @NotNull Long getX() {
        val x = super.getX();
        if (x == null) {
            setX(0L);
            return 0L;
        } else return x;
    }

    @Override
    public @NotNull Long getY() {
        val y = super.getY();
        if (y == null) {
            setY(0L);
            return 0L;
        } else return y;
    }

    @Override
    public @NotNull Long getZ() {
        val z = super.getZ();
        if (z == null) {
            setZ(0L);
            return 0L;
        } else return z;
    }
}
