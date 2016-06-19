package com.lucilu.rxdynamicsearch.ui.adapter;

import com.lucilu.rxdynamicsearch.ui.adapter.base.IAdapterInteractor;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderInstantiator;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem.Type;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import javax.inject.Inject;

import static com.lucilu.rxdynamicsearch.Constants.ListAdapter.INVALID_VIEW_TYPE;

public class ListAdapter extends Adapter {

    @Inject
    IAdapterInteractor<ListItem> mAdapterInteractor;

    @Inject
    IViewHolderInstantiator<ListItem.Type> mInstantiator;

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return mInstantiator.createViewHolder(Type.typeFromOrdinal(viewType));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return mAdapterInteractor.getCount();
    }

    @Override
    public int getItemViewType(final int position) {
        return mAdapterInteractor.getItem(position)
                                 .match(item -> item.type().ordinal(),
                                        () -> INVALID_VIEW_TYPE);
    }
}
