package io.github.mjaroslav.sharedjava.reflect;

import org.jetbrains.annotations.NotNull;

public class UnableToAccessFieldException extends RuntimeException {
    public UnableToAccessFieldException(@NotNull Throwable e) {
        super(e);
    }
}
