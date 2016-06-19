package com.lucilu.rxdynamicsearch.ui.adapter.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Instantiates a {@link ViewHolder} based on the type.
 */
public interface IViewHolderInstantiator<T> {

    /**
     * Creates a {@link ViewHolder} for the passed type
     *
     * @return the newly created {@link ViewHolder}
     */
    @NonNull
    ViewHolder createViewHolder(@NonNull final T itemType);

}
