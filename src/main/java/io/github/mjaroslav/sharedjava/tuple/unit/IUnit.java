package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class IUnit extends SimpleUnit<Integer> {
    public IUnit() {
        super(0);
    }

    public IUnit(int x) {
        super(x);
    }

    @Override
    public @NotNull Integer getX() {
        val x = super.getX();
        if (x == null) {
            setX(0);
            return 0;
        } else return x;
    }
}
