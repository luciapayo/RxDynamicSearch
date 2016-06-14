package com.lucilu.rxdynamicsearch.ui.adapter;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.data.pojo.Country;
import com.lucilu.rxdynamicsearch.utils.ViewUtils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public final class CountryViewInstantiator implements IViewInstantiator<Country> {

    @NonNull
    private final Context mContext;

    public CountryViewInstantiator(@NonNull final Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public View createView(@NonNull final Country model) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_country, null);
        TextView countryName = ViewUtils.find(view, R.id.item_textView_country);
        TextView capitalName = ViewUtils.find(view, R.id.item_textView_capital);

        countryName.setText(model.getName());
        capitalName.setText(model.getCapital());

        return view;
    }
}
