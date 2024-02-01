package io.github.mjaroslav.sharedjava.tuple.unit;

import lombok.val;
import org.jetbrains.annotations.NotNull;

public class BUnit extends SimpleUnit<Byte> {
    public BUnit() {
        super((byte) 0);
    }

    public BUnit(byte x) {
        super(x);
    }

    @Override
    public @NotNull Byte getX() {
        val x = super.getX();
        if (x == null) {
            setX((byte) 0);
            return (byte) 0;
        } else return x;
    }
}
