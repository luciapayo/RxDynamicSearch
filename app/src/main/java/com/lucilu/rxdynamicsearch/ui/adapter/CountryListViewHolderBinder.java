package com.lucilu.rxdynamicsearch.ui.adapter;

import com.lucilu.rxdynamicsearch.data.pojo.Ad;
import com.lucilu.rxdynamicsearch.data.pojo.Country;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderBinder;
import com.lucilu.rxdynamicsearch.ui.adapter.viewholder.AdViewHolder;
import com.lucilu.rxdynamicsearch.ui.adapter.viewholder.CountryViewHolder;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.DisplayableItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;

import static com.lucilu.rxdynamicsearch.Constants.ListItem.AD;
import static com.lucilu.rxdynamicsearch.Constants.ListItem.COUNTRY;

public final class CountryListViewHolderBinder implements IViewHolderBinder<DisplayableItem> {

    public CountryListViewHolderBinder() {
    }

    @Override
    public void bind(@NonNull final ViewHolder viewHolder,
                     @NonNull final DisplayableItem displayableItem) {
        switch (displayableItem.type()) {
            case COUNTRY:
                CountryViewHolder countryViewHolder = CountryViewHolder.class.cast(viewHolder);
                Country country = Country.class.cast(displayableItem.model());
                countryViewHolder.bind(country);
                break;
            case AD:
                AdViewHolder AdViewHolder = AdViewHolder.class.cast(viewHolder);
                Ad ad = Ad.class.cast(displayableItem.model());
                AdViewHolder.bind(ad);
                break;
            default:
                throw new IllegalStateException("The type is not supported " + displayableItem
                        .type());
        }
    }
}
