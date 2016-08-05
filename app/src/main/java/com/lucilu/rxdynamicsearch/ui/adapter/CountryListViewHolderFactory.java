package com.lucilu.rxdynamicsearch.ui.adapter;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderFactory;
import com.lucilu.rxdynamicsearch.ui.adapter.viewholder.CountryViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.lucilu.rxdynamicsearch.Constants.ListItem.COUNTRY;

public final class CountryListViewHolderFactory implements IViewHolderFactory {

    @NonNull
    private final Context mContext;

    public CountryListViewHolderFactory(@NonNull final Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder createViewHolder(@NonNull final ViewGroup parent, final int itemType) {
        switch (itemType) {
            case COUNTRY:
                View view = LayoutInflater.from(mContext)
                                          .inflate(R.layout.item_country, parent, false);
                return new CountryViewHolder(view);
            default:
                throw new IllegalStateException("Unknown type " + itemType);
        }
    }
}
