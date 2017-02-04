package com.lucilu.rxdynamicsearch.ui.adapter.base;

import com.lucilu.rxdynamicsearch.viewmodel.pojo.DisplayableItem;

/**
 * Created by Lucia on 04/02/2017.
 */

public interface IListItemComparator {

    boolean areItemsTheSame(final DisplayableItem oldItem, final DisplayableItem newItem);

    boolean areContentsTheSame(final DisplayableItem oldItem, final DisplayableItem newItem);
}
