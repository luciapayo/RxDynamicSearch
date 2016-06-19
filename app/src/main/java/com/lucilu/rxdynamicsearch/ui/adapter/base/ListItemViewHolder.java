package com.lucilu.rxdynamicsearch.ui.adapter.base;

import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class ListItemViewHolder extends ViewHolder {

    public ListItemViewHolder(final View itemView) {
        super(itemView);
    }

    protected abstract void bindToListItem(@NonNull final ListItem item);

    protected static void assertListItemType(@NonNull final ListItem item,
                                             @NonNull final ListItem.Type type) {
        checkArgument(item.type() == type, "The item type " + item.type()
                                           + " is not the expected type " + type);
    }
}
