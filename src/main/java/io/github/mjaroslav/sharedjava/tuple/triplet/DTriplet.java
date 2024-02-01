package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class DTriplet extends SimpleTriplet<Double, Double, Double> {
    public DTriplet() {
        super(0D, 0D, 0D);
    }

    public DTriplet(double x, double y, double z) {
        super(x, y, z);
    }

    @Override
    public @NotNull Double getX() {
        val x = super.getX();
        if (x == null) {
            setX(0D);
            return 0D;
        } else return x;
    }

    @Override
    public @NotNull Double getY() {
        val y = super.getY();
        if (y == null) {
            setY(0D);
            return 0D;
        } else return y;
    }

    @Override
    public @NotNull Double getZ() {
        val z = super.getZ();
        if (z == null) {
            setZ(0D);
            return 0D;
        } else return z;
    }
}
