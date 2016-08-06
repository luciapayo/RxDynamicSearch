package com.lucilu.rxdynamicsearch.ui.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

public abstract class SimpleViewHolder<T> extends ViewHolder {

    public SimpleViewHolder(final View itemView) {
        super(itemView);
    }

    public abstract void bind(@NonNull final T model);
}
