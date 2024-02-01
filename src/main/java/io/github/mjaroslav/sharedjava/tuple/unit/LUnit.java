package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class LUnit extends SimpleUnit<Long> {
    public LUnit() {
        super(0L);
    }

    public LUnit(long x) {
        super(x);
    }

    @Override
    public @NotNull Long getX() {
        val x = super.getX();
        if (x == null) {
            setX(0L);
            return 0L;
        } else return x;
    }
}
