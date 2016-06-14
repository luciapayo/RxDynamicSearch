package com.lucilu.rxdynamicsearch.ui.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

public class ListAdapter<T extends ViewHolder> extends Adapter<T> {

    @Override
    public T onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(final T holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
