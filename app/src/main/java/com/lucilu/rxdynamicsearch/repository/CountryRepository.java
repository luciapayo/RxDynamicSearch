package com.lucilu.rxdynamicsearch.repository;

import com.lucilu.rxdynamicsearch.data.pojo.Country;
import com.lucilu.rxdynamicsearch.provider.base.IJsonParserProvider;
import com.lucilu.rxdynamicsearch.repository.base.ICountryRepository;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public class CountryRepository implements ICountryRepository {

    @NonNull
    private final IJsonParserProvider mJsonParserProvider;

    public CountryRepository(@NonNull final IJsonParserProvider jsonParserProvider) {
        mJsonParserProvider = get(jsonParserProvider);
    }

    @NonNull
    public Observable<List<Country>> getAllCountries() {
        return Observable.create(new Observable.OnSubscribe<List<Country>>() {
            @Override
            public void call(final Subscriber<? super List<Country>> subscriber) {
                List<Country> countries = mJsonParserProvider.parseListOfCountriesFromJson();
                subscriber.onNext(countries);
                subscriber.onCompleted();
            }
        });
    }
}
