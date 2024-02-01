package io.github.mjaroslav.sharedjava.reflect;

import org.jetbrains.annotations.NotNull;

public class UnableToFindGenericTypeException extends RuntimeException {
    public UnableToFindGenericTypeException(@NotNull Throwable cause) {
        super(cause);
    }
}
