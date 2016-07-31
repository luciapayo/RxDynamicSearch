package com.lucilu.rxdynamicsearch.viewmodel;

import com.lucilu.rxdynamicsearch.dagger.Scopes.FragmentScope;
import com.lucilu.rxdynamicsearch.data.pojo.Country;
import com.lucilu.rxdynamicsearch.repository.base.ICountryRepository;
import com.lucilu.rxdynamicsearch.viewmodel.base.ViewModel;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import polanski.option.Option;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

import static com.lucilu.rxdynamicsearch.Constants.ListItem.COUNTRY;
import static com.lucilu.rxdynamicsearch.rx.Transformers.choose;
import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

@FragmentScope
public final class SearchFragmentViewModel extends ViewModel {

    @NonNull
    private final Observable<CharSequence> mQueryStream;

    @NonNull
    private final ICountryRepository mCountryRepository;

    @Inject
    public SearchFragmentViewModel(@NonNull final Observable<CharSequence> queryStream,
                                   @NonNull final ICountryRepository countryRepository) {
        mQueryStream = get(queryStream);
        mCountryRepository = get(countryRepository);
    }

    @Override
    protected void subscribeToData(@NonNull final CompositeSubscription s) {

    }

    @NonNull
    public Observable<List<ListItem>> getListItemStream() {
        return mCountryRepository.getAllCountries()
                                 .compose(choose())
                                 .switchMap(this::transform);
    }

    @NonNull
    private Observable<List<ListItem>> transform(List<Country> countries) {
        return Observable.from(countries)
                         .map(country -> Option.ofObj((Object) country))
                         .map(option -> ListItem.builder().type(COUNTRY).model(option).build())
                         .toList();
    }
}
