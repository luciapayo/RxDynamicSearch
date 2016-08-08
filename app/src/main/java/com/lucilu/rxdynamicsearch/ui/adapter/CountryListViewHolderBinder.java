package com.lucilu.rxdynamicsearch.ui.adapter;

import com.lucilu.rxdynamicsearch.data.pojo.Ad;
import com.lucilu.rxdynamicsearch.data.pojo.Country;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderBinder;
import com.lucilu.rxdynamicsearch.ui.adapter.viewholder.AdViewHolder;
import com.lucilu.rxdynamicsearch.ui.adapter.viewholder.BindingViewHolder;
import com.lucilu.rxdynamicsearch.ui.adapter.viewholder.CountryViewHolder;
import com.lucilu.rxdynamicsearch.viewmodel.CountryItemViewModel;
import com.lucilu.rxdynamicsearch.viewmodel.CountryItemViewModel_Factory;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;

import polanski.option.Option;

import static com.lucilu.rxdynamicsearch.Constants.ListItem.AD;
import static com.lucilu.rxdynamicsearch.Constants.ListItem.COUNTRY;

public final class CountryListViewHolderBinder implements IViewHolderBinder<ListItem> {

    @NonNull
    private final CountryItemViewModel_Factory mViewModelFactory;

    public CountryListViewHolderBinder(
            @NonNull final CountryItemViewModel_Factory viewModelFactory) {
        mViewModelFactory = viewModelFactory;
    }

    @Override
    public void bind(@NonNull final Option<ViewHolder> viewHolder,
                     @NonNull final ListItem listItem) {
        switch (listItem.type()) {
            case COUNTRY:
                CountryItemViewModel viewModel = mViewModelFactory
                        .create((Country) listItem.model());
                viewHolder.ofType(CountryViewHolder.class)
                          .ifSome(holder -> holder.bind(viewModel));
                break;
            case AD:
                viewHolder.ofType(AdViewHolder.class)
                          .ifSome(holder -> holder.bind((Ad) listItem.model()));
                break;
            default:
                throw new IllegalStateException("The type is not supported "+ listItem.type());
        }
    }

    @Override
    public void unbind(@NonNull final Option<ViewHolder> viewHolder) {
        viewHolder.ofType(BindingViewHolder.class)
                  .ifSome(BindingViewHolder::unbind);
    }
}
