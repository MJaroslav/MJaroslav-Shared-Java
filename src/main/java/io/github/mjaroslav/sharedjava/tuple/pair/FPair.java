package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;

public class FPair extends SimplePair<Float, Float> {
    public FPair() {
        super(0F, 0F);
    }

    public FPair(float x, float y) {
        super(x, y);
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
}
