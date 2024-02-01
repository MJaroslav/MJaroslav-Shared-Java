package io.github.mjaroslav.sharedjava.tuple.triplet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiPredicate;

public class TripletEqualsDummy implements BiPredicate<DelegatingTriplet<Object, Object, Object>, Object> {
    @Override
    public boolean test(@NotNull DelegatingTriplet<Object, Object, Object> triplet, @Nullable Object obj) {
        triplet.setEqualsFunc(null);
        return triplet.equals(obj);
    }
}
