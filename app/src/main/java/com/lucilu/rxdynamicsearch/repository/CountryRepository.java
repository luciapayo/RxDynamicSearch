package com.lucilu.rxdynamicsearch.repository;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.data.pojo.Country;
import com.lucilu.rxdynamicsearch.provider.base.IJsonParserProvider;
import com.lucilu.rxdynamicsearch.provider.base.IResourceProvider;
import com.lucilu.rxdynamicsearch.repository.base.ICountryRepository;
import com.lucilu.rxdynamicsearch.utils.option.Option;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public class CountryRepository implements ICountryRepository {

    @NonNull
    private final IJsonParserProvider mJsonParserProvider;

    @NonNull
    private final IResourceProvider mResourceProvider;

    public CountryRepository(@NonNull final IJsonParserProvider jsonParserProvider,
                             @NonNull final IResourceProvider resourceProvider) {
        mJsonParserProvider = get(jsonParserProvider);
        mResourceProvider = get(resourceProvider);
    }

    @NonNull
    public Observable<List<Country>> getAllCountries() {
        return Observable.create(new Observable.OnSubscribe<List<Country>>() {
            @Override
            public void call(final Subscriber<? super List<Country>> subscriber) {
                Option<List<Country>>
                        countries = mJsonParserProvider.parseListFromRawJsonFile(R.raw.countries);

                subscriber.onNext(countries);
                subscriber.onCompleted();
            }
        });
    }
}
