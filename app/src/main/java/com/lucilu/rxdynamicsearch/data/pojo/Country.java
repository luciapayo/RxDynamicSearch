package com.lucilu.rxdynamicsearch.data.pojo;

import android.support.annotation.NonNull;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public final class Country {

    @NonNull
    private final String mName;

    @NonNull
    private final String mCapital;

    @NonNull
    private final String mRegion;

    public Country(@NonNull final String name,
                   @NonNull final String capital,
                   @NonNull final String region) {
        mName = get(name);
        mCapital = get(capital);
        mRegion = get(region);
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getCapital() {
        return mCapital;
    }

    @NonNull
    public String getRegion() {
        return mRegion;
    }
}
