package io.github.mjaroslav.sharedjava.tuple.triplet;

import lombok.val;

public class DTriplet extends SimpleTriplet<Double, Double, Double> {
    public DTriplet() {
        super(0D, 0D, 0D);
    }

    public DTriplet(double x, double y, double z) {
        super(x, y, z);
    }

    public double x() {
        val x = getX();
        if (x == null) {
            setX(0D);
            return 0D;
        }
        return x;
    }

    public double y() {
        val y = getY();
        if (y == null) {
            setY(0D);
            return 0D;
        }
        return y;
    }

    public double z() {
        val z = getZ();
        if (z == null) {
            setZ(0D);
            return 0D;
        }
        return z;
    }
}
