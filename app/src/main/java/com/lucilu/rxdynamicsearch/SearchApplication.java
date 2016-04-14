package com.lucilu.rxdynamicsearch;

import com.lucilu.rxdynamicsearch.dagger.component.AppComponent;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import timber.log.Timber;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public class SearchApplication extends Application {

    @Inject
    Timber.Tree mTimberTree;

    @Nullable
    private AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        onInject();
        initializeLogging();
    }

    private void onInject() {
        mComponent = AppComponent.Initializer.init(this);
        mComponent.inject(this);
    }

    private void initializeLogging() {
        Timber.plant(mTimberTree);
    }

    @NonNull
    public AppComponent getAppComponent() {
        return get(mComponent);
    }
}
