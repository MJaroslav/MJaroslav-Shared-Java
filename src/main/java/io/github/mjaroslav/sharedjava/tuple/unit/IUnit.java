package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;

public class IUnit extends SimpleUnit<Integer> {
    public IUnit() {
        super(0);
    }

    public IUnit(int x) {
        super(x);
    }

    public int x() {
        val x = getX();
        if (x == null) {
            setX(0);
            return 0;
        }
        return x;
    }
}
