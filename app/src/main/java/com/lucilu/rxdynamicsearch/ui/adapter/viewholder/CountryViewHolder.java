package com.lucilu.rxdynamicsearch.ui.adapter.viewholder;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.data.pojo.Country;
import com.lucilu.rxdynamicsearch.ui.adapter.base.ListItemViewHolder;
import com.lucilu.rxdynamicsearch.utils.ViewUtils;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import static com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem.Type.COUNTRY;

public final class CountryViewHolder extends ListItemViewHolder {

    @NonNull
    private final TextView mCountryName;

    @NonNull
    private final TextView mCapital;

    public CountryViewHolder(final View itemView) {
        super(itemView);

        mCountryName = ViewUtils.find(itemView, R.id.item_textView_country);
        mCapital = ViewUtils.find(itemView, R.id.item_textView_capital);
    }

    @Override
    public void bindToListItem(@NonNull final ListItem item) {
        assertListItemType(item, COUNTRY);

        item.model()
            .ofType(Country.class)
            .ifSome(this::bindToModel);
    }

    private void bindToModel(@NonNull final Country country) {
        mCountryName.setText(country.getName());
        mCapital.setText(country.getCapital());
    }
}
