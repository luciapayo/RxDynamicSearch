package com.lucilu.rxdynamicsearch.rx.transformer;

import com.lucilu.rxdynamicsearch.rx.utils.ObservableEx;

import polanski.option.Option;
import rx.Observable;

/**
 * Filters out all Option of NONE if any, but if Some, then unwraps and returns the
 * value.
 */
public final class ChooseTransformer<T>
        implements Observable.Transformer<Option<T>, T> {

    @Override
    public Observable<T> call(final Observable<Option<T>> optionObservable) {
        return ObservableEx.choose(optionObservable);
    }
}
