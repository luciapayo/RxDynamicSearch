package com.lucilu.rxdynamicsearch.viewmodel;

import com.lucilu.rxdynamicsearch.dagger.Scopes.FragmentScope;
import com.lucilu.rxdynamicsearch.repository.base.ICountryRepository;
import com.lucilu.rxdynamicsearch.viewmodel.base.ViewModel;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

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
        s.add(mCountryRepository.getAllCountries()
                                .compose(choose())
                                .flatMap(Observable::from)
                                .doOnNext(country -> Timber.d("%s", country))
                                .subscribe(country -> Timber.d("%s", country),
                                           error -> Timber.e(error, "ERROR")));
    }
}
