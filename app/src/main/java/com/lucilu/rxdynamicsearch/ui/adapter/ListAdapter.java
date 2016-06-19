package com.lucilu.rxdynamicsearch.ui.adapter;

import com.lucilu.rxdynamicsearch.ui.adapter.base.IAdapterInteractor;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderInstantiator;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderPopulator;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem.Type;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import static com.lucilu.rxdynamicsearch.Constants.ListAdapter.INVALID_VIEW_TYPE;
import static polanski.option.Option.ofObj;

public final class ListAdapter extends Adapter {

    @NonNull
    private IAdapterInteractor<ListItem> mAdapterInteractor;

    @NonNull
    private IViewHolderInstantiator<ListItem.Type> mInstantiator;

    @NonNull
    private IViewHolderPopulator<ListItem> mPopulator;

    public ListAdapter(
            @NonNull final IAdapterInteractor<ListItem> adapterInteractor,
            @NonNull final IViewHolderInstantiator<Type> instantiator,
            @NonNull final IViewHolderPopulator<ListItem> populator) {
        mAdapterInteractor = adapterInteractor;
        mInstantiator = instantiator;
        mPopulator = populator;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return mInstantiator.createViewHolder(Type.typeFromOrdinal(viewType));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mAdapterInteractor.getItem(position)
                          .ifSome(item -> mPopulator.populateViewHolder(ofObj(holder), item));
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
