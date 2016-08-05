package com.lucilu.rxdynamicsearch.ui.adapter.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public abstract class ListItemViewHolder<T> extends ViewHolder {

    public ListItemViewHolder(final View itemView) {
        super(itemView);
    }

    protected abstract void bindToModel(@NonNull final T model);
}
