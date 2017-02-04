package com.lucilu.rxdynamicsearch.ui.adapter;

import com.lucilu.rxdynamicsearch.ui.adapter.base.IListItemComparator;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.DisplayableItem;

public final class CountryListItemComparator implements IListItemComparator {

    public boolean areItemsTheSame(final DisplayableItem oldItem,
                                   final DisplayableItem newItem) {
        return false;
    }

    public boolean areContentsTheSame(final DisplayableItem oldItem,
                                      final DisplayableItem newItem) {
        return false;
    }
}
