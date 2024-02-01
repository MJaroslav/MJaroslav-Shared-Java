package io.github.mjaroslav.sharedjava.tuple.pair;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class LPair extends SimplePair<Long, Long> {
    public LPair() {
        super(0L, 0L);
    }

    public LPair(long x, long y) {
        super(x, y);
    }

    @Override
    public @NotNull Long getX() {
        val x = super.getX();
        if (x == null) {
            setX(0L);
            return 0L;
        } else return x;
    }

    @Override
    public @NotNull Long getY() {
        val y = super.getY();
        if (y == null) {
            setY(0L);
            return 0L;
        } else return y;
    }
}
