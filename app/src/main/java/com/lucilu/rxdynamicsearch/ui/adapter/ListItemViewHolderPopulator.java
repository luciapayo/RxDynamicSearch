package com.lucilu.rxdynamicsearch.ui.adapter;

import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderPopulator;
import com.lucilu.rxdynamicsearch.ui.adapter.viewholder.CountryViewHolder;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;

import polanski.option.Option;

import static com.lucilu.rxdynamicsearch.Constants.ListItem.COUNTRY;

public final class ListItemViewHolderPopulator implements IViewHolderPopulator<ListItem> {

    @Override
    public void populateViewHolder(@NonNull final Option<ViewHolder> viewHolder,
                                   @NonNull final ListItem model) {
        switch (model.type()) {
            case COUNTRY:
                viewHolder.ofType(CountryViewHolder.class)
                          .ifSome(holder -> holder.bindToListItem(model));
        }
    }
}
