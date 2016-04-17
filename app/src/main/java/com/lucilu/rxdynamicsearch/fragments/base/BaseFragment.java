package com.lucilu.rxdynamicsearch.fragments.base;

import com.lucilu.rxdynamicsearch.IInjectable;
import com.soundcloud.lightcycle.LightCycleSupportFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;

import rx.subscriptions.CompositeSubscription;

/**
 * This base fragment provides the common functionality to all fragments in the app.
 */
public abstract class BaseFragment
        extends LightCycleSupportFragment<BaseFragment> implements IInjectable {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInject();
    }

    protected abstract void onBind(@NonNull final CompositeSubscription subscription);
}
