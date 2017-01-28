package com.lucilu.rxdynamicsearch.viewmodel;

import com.lucilu.rxdynamicsearch.dagger.Scopes.FragmentScope;
import com.lucilu.rxdynamicsearch.data.pojo.Ad;
import com.lucilu.rxdynamicsearch.data.pojo.Country;
import com.lucilu.rxdynamicsearch.repository.base.ICountryRepository;
import com.lucilu.rxdynamicsearch.viewmodel.base.ViewModel;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.DisplayableItem;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

import static com.lucilu.rxdynamicsearch.Constants.ListItem.AD;
import static com.lucilu.rxdynamicsearch.Constants.ListItem.COUNTRY;
import static com.lucilu.rxdynamicsearch.rx.Transformers.choose;
import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

@FragmentScope
public final class CountryListViewModel extends ViewModel {

    @NonNull
    private final Observable<CharSequence> mQueryStream;

    @NonNull
    private final ICountryRepository mCountryRepository;

    @Inject
    CountryListViewModel(@NonNull final Observable<CharSequence> queryStream,
                         @NonNull final ICountryRepository countryRepository) {
        mQueryStream = get(queryStream);
        mCountryRepository = get(countryRepository);
    }

    @Override
    protected void subscribeToData(@NonNull final CompositeSubscription s) {

    }

    @NonNull
    public Observable<List<DisplayableItem>> getListItemStream() {
        return mCountryRepository.getAllCountries()
                                 .compose(choose())
                                 .switchMap(this::transform)
                                 .map(this::insertAds);
    }

    @NonNull
    private Observable<List<DisplayableItem>> transform(@NonNull final List<Country> countries) {
        return Observable.from(countries)
                         .map(country -> toListItem(country, COUNTRY))
                         .toList();
    }

    private List<DisplayableItem> insertAds(
            @NonNull final List<DisplayableItem> countryDisplayableItems) {
        countryDisplayableItems.add(10, toListItem(Ad.createAd("Put your ad here"), AD));
        countryDisplayableItems.add(20, toListItem(Ad.createAd("Put your ad here"), AD));
        countryDisplayableItems.add(30, toListItem(Ad.createAd("Put your ad here"), AD));

        return countryDisplayableItems;
    }

    private static DisplayableItem toListItem(@NonNull final Object model, final int type) {
        return DisplayableItem.builder().type(type).model(model).build();
    }
}
