package com.lucilu.rxdynamicsearch.viewmodel;

import com.lucilu.rxdynamicsearch.data.pojo.Country;

import android.support.annotation.NonNull;

public class CountryItemViewModel_Factory {

    public CountryItemViewModel create(@NonNull final Country country) {
        return new CountryItemViewModel(country);
    }
}
