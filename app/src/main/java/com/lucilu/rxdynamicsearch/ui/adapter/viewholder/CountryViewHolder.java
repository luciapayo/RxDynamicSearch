package com.lucilu.rxdynamicsearch.ui.adapter.viewholder;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.data.pojo.Country;
import com.lucilu.rxdynamicsearch.utils.ViewUtils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public final class CountryViewHolder extends RecyclerView.ViewHolder {

    @NonNull
    private final TextView mCountryName;

    @NonNull
    private final TextView mCapital;

    public CountryViewHolder(final View itemView) {
        super(itemView);

        mCountryName = ViewUtils.find(itemView, R.id.item_textView_country);
        mCapital = ViewUtils.find(itemView, R.id.item_textView_capital);
    }

    public void bindToModel(@NonNull final Country country) {
        mCountryName.setText(country.getName());
        mCapital.setText(country.getCapital());
    }
}
