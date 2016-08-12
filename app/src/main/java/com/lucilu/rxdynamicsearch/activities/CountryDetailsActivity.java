package com.lucilu.rxdynamicsearch.activities;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.StaticCounter;
import com.lucilu.rxdynamicsearch.activities.base.BaseActivity;
import com.lucilu.rxdynamicsearch.utils.ViewUtils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public final class CountryDetailsActivity extends BaseActivity {

    private TextView dataCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d(">>>> DESTROYING Details activity");
    }
}
