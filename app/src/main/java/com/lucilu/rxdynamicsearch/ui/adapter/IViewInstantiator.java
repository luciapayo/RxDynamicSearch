package com.lucilu.rxdynamicsearch.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Instantiates a view based on a model.
 */
public interface IViewInstantiator<T> {

    /**
     * Creates a view based on the model.
     *
     * @param model model object for the created view
     * @return the created view
     */
    @NonNull
    View createView(@NonNull final T model);
}
