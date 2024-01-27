package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;

public class FUnit extends SimpleUnit<Float> {
    public FUnit() {
        super(0F);
    }

    public FUnit(float x) {
        super(x);
    }

    public float x() {
        val x = getX();
        if (x == null) {
            setX(0F);
            return 0F;
        }
        return x;
    }
}
