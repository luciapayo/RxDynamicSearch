package com.lucilu.rxdynamicsearch.data.pojo;

import com.google.gson.annotations.SerializedName;

import android.support.annotation.NonNull;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public final class Country {

    @NonNull
    @SerializedName("name")
    private final String mName;

    @NonNull
    @SerializedName("capital")
    private final String mCapital;

    @NonNull
    @SerializedName("region")
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Country country = (Country) o;

        if (!mName.equals(country.mName)) {
            return false;
        }
        if (!mCapital.equals(country.mCapital)) {
            return false;
        }
        return mRegion.equals(country.mRegion);

    }

    @Override
    public int hashCode() {
        int result = mName.hashCode();
        result = 31 * result + mCapital.hashCode();
        result = 31 * result + mRegion.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
               "mName='" + mName + '\'' +
               ", mCapital='" + mCapital + '\'' +
               ", mRegion='" + mRegion + '\'' +
               '}';
    }
}
