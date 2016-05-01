package com.lucilu.rxdynamicsearch.activities;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.SearchApplication;
import com.lucilu.rxdynamicsearch.activities.base.BaseActivity;
import com.lucilu.rxdynamicsearch.dagger.component.MainActivityComponent;
import com.lucilu.rxdynamicsearch.dagger.module.ActivityModule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.subscriptions.CompositeSubscription;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public final class MainActivity extends BaseActivity {

    @Nullable
    private MainActivityComponent mComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onBind(@NonNull final CompositeSubscription subscription) {
        // DO nothing (for now)
    }

    @Override
    public void onInject() {
        mComponent = createComponent();
        mComponent.inject(this);
    }

    @NonNull
    public MainActivityComponent getComponent() {
        return get(mComponent);
    }

    private MainActivityComponent createComponent() {
        SearchApplication app = (SearchApplication) getApplication();
        ActivityModule activityModule = new ActivityModule(this);

        return app.getAppComponent().plusMainActivity(activityModule);
    }
}
