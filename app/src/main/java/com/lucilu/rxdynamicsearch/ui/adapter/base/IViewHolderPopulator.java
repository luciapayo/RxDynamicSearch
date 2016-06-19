package com.lucilu.rxdynamicsearch.ui.adapter.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;

import polanski.option.Option;

/**
 * Populates a {@link ViewHolder} with the model details.
 */
public interface IViewHolderPopulator<T> {

    /**
     * Populates the passed {@link ViewHolder} with the details of the passed model.
     *
     * @param viewHolder {@link Option} wrapping the {@link ViewHolder}
     */
    void populateViewHolder(@NonNull final Option<ViewHolder> viewHolder,
                            @NonNull final T model);
}
