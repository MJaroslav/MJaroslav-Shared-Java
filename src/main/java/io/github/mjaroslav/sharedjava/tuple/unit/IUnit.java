package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * Simple implementation of {@link Unit} for Integer values, contains: equals, hashCode and toString functionality.
 * Null values will be replaced with 0.
 *
 * @author MJaroslav
 * @since 0.1.0
 */
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
