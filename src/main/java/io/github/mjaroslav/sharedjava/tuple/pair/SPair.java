package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class SPair extends SimplePair<Short, Short> {
    public SPair() {
        super((short) 0, (short) 0);
    }

    public SPair(short x, short y) {
        super(x, y);
    }

    @Override
    public @NotNull Short getX() {
        val x = super.getX();
        if (x == null) {
            setX((short) 0);
            return (short) 0;
        } else return x;
    }

    @Override
    public @NotNull Short getY() {
        val y = super.getY();
        if (y == null) {
            setY((short) 0);
            return (short) 0;
        } else return y;
    }
}
