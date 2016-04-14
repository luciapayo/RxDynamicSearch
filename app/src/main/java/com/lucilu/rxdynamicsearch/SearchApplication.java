package com.lucilu.rxdynamicsearch;

import com.lucilu.rxdynamicsearch.dagger.component.AppComponent;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public class SearchApplication extends Application {

    @Nullable
    private AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        onInject();
    }

    private void onInject() {
        mComponent = AppComponent.Initializer.init(this);
    }

    @NonNull
    public AppComponent getAppComponent() {
        return get(mComponent);
    }
}
