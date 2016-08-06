package com.lucilu.rxdynamicsearch.viewmodel;

import com.lucilu.rxdynamicsearch.data.pojo.Country;
import com.lucilu.rxdynamicsearch.viewmodel.base.ViewModel;

import android.support.annotation.NonNull;

import rx.subscriptions.CompositeSubscription;

public class CountryItemViewModel extends ViewModel {

    @NonNull
    private final Country mCountry;

    public CountryItemViewModel(@NonNull final Country country) {
        mCountry = country;
    }

    @Override
    protected void subscribeToData(@NonNull final CompositeSubscription subscription) {
        // Do stuff
    }
}
