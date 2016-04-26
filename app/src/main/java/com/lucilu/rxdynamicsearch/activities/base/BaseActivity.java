package com.lucilu.rxdynamicsearch.activities.base;

import com.lucilu.rxdynamicsearch.IInjectable;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import rx.subscriptions.CompositeSubscription;

/**
 * This base activity provides the common functionality to all activities in the app.
 */
public abstract class BaseActivity extends AppCompatActivity implements IInjectable {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInject();
    }

    protected abstract void onBind(@NonNull final CompositeSubscription subscription);
}
