package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class FPair extends SimplePair<Float, Float> {
    public FPair() {
        super(0F, 0F);
    }

    public FPair(float x, float y) {
        super(x, y);
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
}
