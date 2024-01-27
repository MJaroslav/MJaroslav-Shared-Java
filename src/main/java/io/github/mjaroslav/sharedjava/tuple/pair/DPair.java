package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;

public class DPair extends SimplePair<Double, Double> {
    public DPair() {
        super(0D, 0D);
    }

    public DPair(double x, double y) {
        super(x, y);
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
}
