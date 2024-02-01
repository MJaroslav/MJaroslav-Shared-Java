package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class IPair extends SimplePair<Integer, Integer> {
    public IPair() {
        super(0, 0);
    }

    public IPair(int x, int y) {
        super(x, y);
    }

    @Override
    public @NotNull Integer getX() {
        val x = super.getX();
        if (x == null) {
            setX(0);
            return 0;
        } else return x;
    }

    @Override
    public @NotNull Integer getY() {
        val y = super.getY();
        if (y == null) {
            setY(0);
            return 0;
        } else return y;
    }
}
