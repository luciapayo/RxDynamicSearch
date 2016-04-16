package com.lucilu.rxdynamicsearch.dagger.module;

import com.lucilu.rxdynamicsearch.activities.MainActivity;
import com.lucilu.rxdynamicsearch.dagger.Scopes.ActivityScope;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

/**
 * Provides {@link MainActivity} specific objects.
 */
@Module
public final class MainActivityModule {

    @NonNull
    private final Observable<CharSequence> mQueryStream;

    public MainActivityModule(@NonNull final Observable<CharSequence> queryStream) {
        mQueryStream = get(queryStream);
    }

    @ActivityScope
    @Provides
    Observable<CharSequence> providesQueryStream() {
        return mQueryStream;
    }
}
