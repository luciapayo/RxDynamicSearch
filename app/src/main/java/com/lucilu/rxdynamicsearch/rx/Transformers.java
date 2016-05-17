package com.lucilu.rxdynamicsearch.rx;

import com.lucilu.rxdynamicsearch.rx.transformer.ChooseTransformer;

import android.support.annotation.NonNull;

import rx.Observable;

/**
 * Collection of transformers.
 */
public final class Transformers {

    private Transformers() {
    }

    /**
     * Returns a {@link Observable.Transformer} that Filters out all Option of NONE if any,
     * but if Some, then unwraps and returns the value.
     *
     * @return choose transformer.
     */
    @NonNull
    public static <T> ChooseTransformer<T> choose() {
        return new ChooseTransformer<>();
    }
}
