package com.lucilu.rxdynamicsearch.ui.adapter;

import com.lucilu.rxdynamicsearch.ui.adapter.base.IAdapterInteractor;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderFactory;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderPopulator;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import static com.lucilu.rxdynamicsearch.Constants.ListItem.INVALID_VIEW_TYPE;
import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;
import static polanski.option.Option.ofObj;

public final class ListAdapter extends Adapter {

    @NonNull
    private final IAdapterInteractor<ListItem> mInteractor;

    @NonNull
    private final IViewHolderFactory mInstantiator;

    @NonNull
    private final IViewHolderPopulator<ListItem> mPopulator;

    public ListAdapter(@NonNull final IAdapterInteractor<ListItem> interactor,
                       @NonNull final IViewHolderFactory instantiator,
                       @NonNull final IViewHolderPopulator<ListItem> populator) {
        mInteractor = get(interactor);
        mInstantiator = get(instantiator);
        mPopulator = get(populator);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return mInstantiator.createViewHolder(viewType);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mInteractor.getItem(position)
                   .ifSome(item -> mPopulator.populateViewHolder(ofObj(holder), item));
    }

    @Override
    public int getItemCount() {
        return mInteractor.getCount();
    }

    @Override
    public int getItemViewType(final int position) {
        return mInteractor.getItem(position)
                          .match(ListItem::type,
                                 () -> INVALID_VIEW_TYPE);
    }
}
