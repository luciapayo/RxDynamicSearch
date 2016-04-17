package com.lucilu.rxdynamicsearch.viewmodel;

import com.lucilu.rxdynamicsearch.dagger.Scopes.FragmentScope;
import com.lucilu.rxdynamicsearch.viewmodel.base.ViewModel;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

@FragmentScope
public final class SearchFragmentViewModel extends ViewModel {

    private final Observable<CharSequence> mQueryStream;

    @Inject
    public SearchFragmentViewModel(@NonNull final Observable<CharSequence> queryStream) {
        mQueryStream = queryStream;
    }

    @Override
    protected void subscribeToData(@NonNull final CompositeSubscription subscription) {

    }
}
