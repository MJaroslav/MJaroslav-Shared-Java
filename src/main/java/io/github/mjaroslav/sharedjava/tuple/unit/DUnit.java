package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;

public class DUnit extends SimpleUnit<Double> {
    public DUnit() {
        super(0D);
    }

    public DUnit(double x) {
        super(x);
    }

    public double x() {
        val x = getX();
        if (x == null) {
            setX(0D);
            return 0D;
        }
        return x;
    }
}
