package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class FUnit extends SimpleUnit<Float> {
    public FUnit() {
        super(0F);
    }

    public FUnit(float x) {
        super(x);
    }

    @Override
    public @NotNull Float getX() {
        val x = super.getX();
        if (x == null) {
            setX(0F);
            return 0F;
        } else return x;
    }
}
