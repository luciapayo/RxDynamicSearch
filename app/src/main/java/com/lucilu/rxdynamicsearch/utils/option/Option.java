package com.lucilu.rxdynamicsearch.utils.option;

import com.lucilu.rxdynamicsearch.utils.rx.Unit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Represent possibility of value not existing, which needs to be unwrapped before using
 *
 * @param <T> type of object that could possibly be missing
 */
public abstract class Option<T> {

    /**
     * Option with no value.
     */
    @NonNull
    public static final None NONE = new None();

    /**
     * @return NONE option without warning.
     */
    @SuppressWarnings("unchecked")
    @NonNull
    public static <T> Option<T> none() {
        return NONE;
    }

    /**
     * Option created from given value.
     *
     * @param value Value that should be wrapped in an Option
     * @return Some of the @value if it is not null, otherwise None
     */
    @SuppressWarnings("unchecked")
    @NonNull
    public static <IN> Option<IN> ofObj(@Nullable final IN value) {
        return value == null ? Option.NONE : new Some(value);
    }

    /**
     * Matches current option to Some or None and returns unit
     *
     * @param fSome Action that will be called if value exists
     * @param fNone Action that will be called if value does not exist
     * @return Unit
     */
    @NonNull
    public abstract Unit matchAction(@NonNull final Action1<T> fSome,
                                     @NonNull final Action0 fNone);
}
