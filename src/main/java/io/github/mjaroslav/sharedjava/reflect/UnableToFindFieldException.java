package io.github.mjaroslav.sharedjava.reflect;

import org.jetbrains.annotations.NotNull;

public class UnableToFindFieldException extends RuntimeException {
    public UnableToFindFieldException(@NotNull Throwable e) {
        super(e);
    }
}
