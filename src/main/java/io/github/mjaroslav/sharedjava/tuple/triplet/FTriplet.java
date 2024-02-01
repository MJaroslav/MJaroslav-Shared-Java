package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class FTriplet extends SimpleTriplet<Float, Float, Float> {
    public FTriplet() {
        super(0F, 0F, 0F);
    }

    public FTriplet(float x, float y, float z) {
        super(x, y, z);
    }

    @Override
    public @NotNull Float getX() {
        val x = super.getX();
        if (x == null) {
            setX(0F);
            return 0F;
        } else return x;
    }

    @Override
    public @NotNull Float getY() {
        val y = super.getY();
        if (y == null) {
            setY(0F);
            return 0F;
        } else return y;
    }

    @Override
    public @NotNull Float getZ() {
        val z = super.getZ();
        if (z == null) {
            setZ(0F);
            return 0F;
        } else return z;
    }
}
