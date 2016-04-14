package com.lucilu.rxdynamicsearch.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

/**
 * Provides the instrumentation objects for debug dependency injection.
 */
@Module
public class InstrumentationModule {

    @Singleton
    @Provides
    Timber.Tree provideTimberTree() {
        return new Timber.DebugTree();
    }
}
