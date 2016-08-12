package com.lucilu.rxdynamicsearch.activities;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.StaticCounter;
import com.lucilu.rxdynamicsearch.activities.base.BaseActivity;
import com.lucilu.rxdynamicsearch.utils.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.TextView;

import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class EntryActivity extends BaseActivity {

    private TextView dataCounter;
    private Button navigateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        dataCounter = ViewUtils.find(this, R.id.tv_subscriptions_data);
        navigateButton = ViewUtils.find(this, R.id.button_navigate);

        navigateButton.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
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
}
