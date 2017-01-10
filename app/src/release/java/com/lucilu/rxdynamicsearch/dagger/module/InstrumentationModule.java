package com.lucilu.rxdynamicsearch.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

/**
 * Provides the instrumentation objects for release dependency injection.
 */
@Module
public class InstrumentationModule {

    @Provides
    @Singleton
    Timber.Tree provideTimberTree() {
        return new Timber.Tree() {
            @Override
            protected void log(final int priority,
                               final String tag,
                               final String message,
                               final Throwable t) {
                // DO NOTHING
            }
        };
    }
}
