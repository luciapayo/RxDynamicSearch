package com.lucilu.baseapp.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Static class that provides helper methods to check preconditions.
 */
public class Preconditions {

    private Preconditions() {
        throw new AssertionError("Don't create instances of this object");
    }

    /**
     * Checks if the reference is not null.
     *
     * @param reference an object reference
     * @return the non-null reference
     * @throws NullPointerException if {@code reference} is null
     */
    @Nonnull
    public static <T> T get(@Nullable final T reference) {
        if (reference == null) {
            throw new NullPointerException("Assertion for a nonnull object failed.");
        }
        return reference;
    }
}
