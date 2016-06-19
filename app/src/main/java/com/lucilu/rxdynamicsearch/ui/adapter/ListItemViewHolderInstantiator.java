package com.lucilu.rxdynamicsearch.ui.adapter;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderInstantiator;
import com.lucilu.rxdynamicsearch.ui.adapter.viewholder.CountryViewHolder;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;

public final class ListItemViewHolderInstantiator
        implements IViewHolderInstantiator<ListItem.Type> {

    @NonNull
    private final Context mContext;

    public ListItemViewHolderInstantiator(@NonNull final Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder createViewHolder(@NonNull final ListItem.Type itemType) {
        switch (itemType) {
            case COUNTRY:
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_country, null);
                return new CountryViewHolder(view);
            default:
                throw new IllegalStateException("Unknown type " + itemType);
        }
    }
}
