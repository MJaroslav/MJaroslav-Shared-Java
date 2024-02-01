package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class DPair extends SimplePair<Double, Double> {
    public DPair() {
        super(0D, 0D);
    }

    public DPair(double x, double y) {
        super(x, y);
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
}
