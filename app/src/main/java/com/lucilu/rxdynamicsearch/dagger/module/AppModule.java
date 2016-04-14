package com.lucilu.rxdynamicsearch.dagger.module;

import com.lucilu.rxdynamicsearch.dagger.Qualifiers.ForApplication;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides application object and application context.
 */
@Module
public class AppModule {

    @NonNull
    private final Application mApplication;

    public AppModule(@NonNull final Application app) {
        mApplication = app;
    }

    @Singleton
    @Provides
    Application providesApplication() {
        return mApplication;
    }

    @Singleton
    @ForApplication
    @Provides
    Context providesApplicationContext() {
        return mApplication.getApplicationContext();
    }
}
