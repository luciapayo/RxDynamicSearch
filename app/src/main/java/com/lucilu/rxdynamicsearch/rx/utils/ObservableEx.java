package com.lucilu.rxdynamicsearch.rx.utils;

import android.support.annotation.NonNull;

import polanski.option.Option;
import polanski.option.OptionUnsafe;
import rx.Observable;
import rx.functions.Func1;

/**
 * Extension class for {@link Observable}.
 */
public final class ObservableEx {

    private ObservableEx() {
    }

    /**
     * Filters NONE {@link Option} items that are converted by selector.
     *
     * @param observable to be filtered
     * @param selector   to convert value to {@link Option}
     * @return Filtered observable
     */
    @NonNull
    public static <IN, OUT> Observable<OUT> choose(@NonNull final Observable<IN> observable,
                                                   @NonNull final Func1<IN, Option<OUT>> selector) {
        return observable.map(selector)
                         .filter(Option::isSome)
                         .map(OptionUnsafe::getUnsafe);
    }

    /**
     * Filters NONE {@link Option} items that are converted by selector.
     *
     * @param observable to be filtered
     * @return Filtered observable
     */
    @NonNull
    public static <T> Observable<T> choose(@NonNull final Observable<Option<T>> observable) {
        return choose(observable, it -> it);
    }
}
