package com.lucilu.rxdynamicsearch.ui.adapter.viewholder;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.activities.CountryDetailsActivity;
import com.lucilu.rxdynamicsearch.data.pojo.Country;
import com.lucilu.rxdynamicsearch.utils.ViewUtils;
import com.lucilu.rxdynamicsearch.viewmodel.CountryItemViewModel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public final class CountryViewHolder extends BindingViewHolder<CountryItemViewModel> {

    @NonNull
    private final TextView mCountryName;

    @NonNull
    private final TextView mCapital;

    public CountryViewHolder(@NonNull final View itemView) {
        super(itemView);

        mCountryName = ViewUtils.find(itemView, R.id.item_textView_country);
        mCapital = ViewUtils.find(itemView, R.id.item_textView_capital);

        itemView.setOnClickListener(v -> v.getContext()
                                          .startActivity(new Intent(v.getContext(),
                                                                    CountryDetailsActivity.class)));
    }

    @Override
    protected void subscribeViewHolder(@NonNull final CompositeSubscription s) {
        getViewModel().ifSome(viewModel -> subscribeToCountry(s, viewModel));
    }

    private void subscribeToCountry(@NonNull final CompositeSubscription s,
                                    @NonNull final CountryItemViewModel viewModel) {
        s.add(viewModel.getCountryOnce()
                       .subscribeOn(Schedulers.computation())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(this::populate,
                                  e -> Timber.e(e,
                                                "It was not possible to show the country in the list")));
    }

    private void populate(@NonNull final Country country) {
        mCountryName.setText(country.getName());
        mCapital.setText(country.getCapital());
    }
}
