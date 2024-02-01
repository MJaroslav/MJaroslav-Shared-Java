package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class DUnit extends SimpleUnit<Double> {
    public DUnit() {
        super(0D);
    }

    public DUnit(double x) {
        super(x);
    }

    @Override
    public @NotNull Double getX() {
        val x = super.getX();
        if (x == null) {
            setX(0D);
            return 0D;
        } else return x;
    }
}
