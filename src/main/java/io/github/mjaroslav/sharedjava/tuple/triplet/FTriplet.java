package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;

public class FTriplet  extends SimpleTriplet<Float, Float, Float> {
    public FTriplet() {
        super(0F, 0F, 0F);
    }

    public FTriplet(float x, float y, float z) {
        super(x, y, z);
    }

    public float x() {
        val x = getX();
        if (x == null) {
            setX(0F);
            return 0F;
        }
        return x;
    }

    public float y() {
        val y = getY();
        if (y == null) {
            setY(0F);
            return 0F;
        }
        return y;
    }

    public float z() {
        val z = getZ();
        if (z == null) {
            setZ(0F);
            return 0F;
        }
        return z;
    }
}
