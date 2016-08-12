package com.lucilu.rxdynamicsearch.activities;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.SearchApplication;
import com.lucilu.rxdynamicsearch.StaticCounter;
import com.lucilu.rxdynamicsearch.activities.base.BaseActivity;
import com.lucilu.rxdynamicsearch.dagger.component.MainActivityComponent;
import com.lucilu.rxdynamicsearch.dagger.module.ActivityModule;
import com.lucilu.rxdynamicsearch.utils.ViewUtils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public final class MainActivity extends BaseActivity {

    @Nullable
    private MainActivityComponent mComponent;

    private TextView dataCounter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataCounter = ViewUtils.find(this, R.id.tv_subscriptions_data);
    }

    @Override
    protected void onBind(@NonNull final CompositeSubscription s) {
        s.add(StaticCounter.getDataSubscriptionsStream()
                           .subscribe(it -> dataCounter
                                              .setText(String.format(getString(R.string.data_counter), it)),
                                      error -> Timber.d("Error setting data counter", error)));
    }

    @Override
    public void onInject() {
        mComponent = createComponent();
        mComponent.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d(">>>> DESTROYING Main activity");
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
