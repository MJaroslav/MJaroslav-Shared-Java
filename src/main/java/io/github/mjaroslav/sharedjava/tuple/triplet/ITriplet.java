package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class ITriplet extends SimpleTriplet<Integer, Integer, Integer> {
    public ITriplet() {
        super(0, 0, 0);
    }

    public ITriplet(int x, int y, int z) {
        super(x, y, z);
    }

    @Override
    public @NotNull Integer getX() {
        val x = super.getX();
        if (x == null) {
            setX(0);
            return 0;
        } else return x;
    }

    @Override
    public @NotNull Integer getY() {
        val y = super.getY();
        if (y == null) {
            setY(0);
            return 0;
        } else return y;
    }

    @Override
    public @NotNull Integer getZ() {
        val z = super.getZ();
        if (z == null) {
            setZ(0);
            return 0;
        } else return z;
    }
}
