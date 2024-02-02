package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * Simple implementation of {@link Unit} for Double values, contains: equals, hashCode and toString functionality.
 * Null values will be replaced with 0D.
 *
 * @author MJaroslav
 * @since 1.0.0
 */
public class DUnit extends SimpleUnit<Double> {
    public DUnit() {
        super(0D);
    }

    public DUnit(double x) {
        super(x);
    }

    @Override
    public @NotNull Double getX() {
        val x = super.getX();
        if (x == null) {
            setX(0D);
            return 0D;
        } else return x;
    }
}
