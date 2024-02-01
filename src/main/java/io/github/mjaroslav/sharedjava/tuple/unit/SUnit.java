package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class SUnit extends SimpleUnit<Short> {
    public SUnit() {
        super((short) 0);
    }

    public SUnit(short x) {
        super(x);
    }

    @Override
    public @NotNull Short getX() {
        val x = super.getX();
        if (x == null) {
            setX((short) 0);
            return (short) 0;
        } else return x;
    }
}
