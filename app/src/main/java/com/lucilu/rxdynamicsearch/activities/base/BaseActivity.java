package com.lucilu.rxdynamicsearch.activities.base;

import com.lucilu.rxdynamicsearch.IInjectable;
import com.soundcloud.lightcycle.LightCycleAppCompatActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.subscriptions.CompositeSubscription;

/**
 *
 */
public abstract class BaseActivity extends LightCycleAppCompatActivity<BaseActivity>
        implements IInjectable {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInject();
    }

    protected abstract void onBind(@NonNull final CompositeSubscription subscription);
}
