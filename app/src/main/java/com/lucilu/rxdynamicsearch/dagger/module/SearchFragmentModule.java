package com.lucilu.rxdynamicsearch.dagger.module;

import com.lucilu.rxdynamicsearch.dagger.Scopes.FragmentScope;
import com.lucilu.rxdynamicsearch.fragments.SearchFragment;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

/**
 * Provides {@link SearchFragment} specific objects.
 */
@Module
public final class SearchFragmentModule {

    @NonNull
    private final Observable<CharSequence> mQueryStream;

    public SearchFragmentModule(@NonNull final Observable<CharSequence> queryStream) {
        mQueryStream = get(queryStream);
    }

    @FragmentScope
    @Provides
    Observable<CharSequence> providesQueryStream() {
        return mQueryStream;
    }
}
