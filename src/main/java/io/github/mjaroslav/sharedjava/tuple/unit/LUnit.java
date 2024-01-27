package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;

public class LUnit extends SimpleUnit<Long> {
    public LUnit() {
        super(0L);
    }

    public LUnit(long x) {
        super(x);
    }

    public long x() {
        val x = getX();
        if (x == null) {
            setX(0L);
            return 0L;
        }
        return x;
    }
}
