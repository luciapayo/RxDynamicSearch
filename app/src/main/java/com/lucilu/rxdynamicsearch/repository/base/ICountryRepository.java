package com.lucilu.rxdynamicsearch.repository.base;

import com.lucilu.rxdynamicsearch.data.pojo.Country;

import android.support.annotation.NonNull;

import java.util.List;

import polanski.option.Option;
import rx.Observable;

/**
 * Countries repository.
 */
public interface ICountryRepository {

    /**
     * Gets all the countries.
     *
     * @return list with all the countries.
     */
    @NonNull
    Observable<Option<List<Country>>> getAllCountries();
}
