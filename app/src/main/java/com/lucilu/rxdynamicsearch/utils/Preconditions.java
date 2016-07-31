package com.lucilu.rxdynamicsearch.utils;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Objects;

/**
 * Static class that provides helper methods to check preconditions.
 */
public final class Preconditions {

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
    @NonNull
    public static <T> T get(@Nullable final T reference) {
        if (reference == null) {
            throw new NullPointerException("Assertion for a nonnull object failed.");
        }
        return reference;
    }

    /**
     * Checks if the reference is not null.
     *
     * @param reference    object reference
     * @param errorMessage message used if the check fails
     * @return non-null reference
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference, @NonNull final String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(get(errorMessage));
        }
        return reference;
    }

    /**
     * Checks the truth of an expression for an argument.
     *
     * @param expression a boolean expression
     * @param errorMessage message used if the check fails
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression, @NonNull final String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(get(errorMessage));
        }
    }

    /**
     * Asserts that the current thread is a worker thread.
     */
    public static void assertWorkerThread() {
        if (isMainThread()) {
            throw new IllegalStateException(
                    "This task must be run on a worker thread and not on the Main thread.");
        }
    }

    /**
     * Asserts that the current thread is the Main Thread.
     */
    public static void assertUiThread() {
        if (!isMainThread()) {
            throw new IllegalStateException(
                    "This task must be run on the Main thread and not on a worker thread.");
        }
    }

    private static boolean isMainThread() {
        return Objects.equals(Looper.getMainLooper(), Looper.myLooper());
    }
}
