package com.lucilu.rxdynamicsearch.activities.base;

import com.lucilu.rxdynamicsearch.IInjectable;
import com.soundcloud.lightcycle.LightCycleAppCompatActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import rx.subscriptions.CompositeSubscription;

/**
 * This base activity provides the common functionality to all activities in the app.
 */
public abstract class BaseActivity
        extends LightCycleAppCompatActivity<BaseActivity> implements IInjectable {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInject();
    }

    protected void displayFragment(final int fragmentContainerId,
                                   @NonNull final Fragment fragment,
                                   @NonNull final String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!fragment.isAdded()) {
            transaction.replace(fragmentContainerId, fragment, tag);
            transaction.commit();
        }
    }

    protected abstract void onBind(@NonNull final CompositeSubscription subscription);
}
