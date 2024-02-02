package io.github.mjaroslav.sharedjava.tuple.unit;

import io.github.mjaroslav.sharedjava.tuple.Unit;
import lombok.val;
import org.jetbrains.annotations.NotNull;

/**
 * Simple implementation of {@link Unit} for Float values, contains: equals, hashCode and toString functionality.
 * Null values will be replaced with 0F.
 *
 * @author MJaroslav
 * @since 1.0.0
 */
public class FUnit extends SimpleUnit<Float> {
    public FUnit() {
        super(0F);
    }

    public FUnit(float x) {
        super(x);
    }

    @Override
    public @NotNull Float getX() {
        val x = super.getX();
        if (x == null) {
            setX(0F);
            return 0F;
        } else return x;
    }
}
