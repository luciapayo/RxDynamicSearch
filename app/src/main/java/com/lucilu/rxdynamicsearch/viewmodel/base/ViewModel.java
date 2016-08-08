package com.lucilu.rxdynamicsearch.viewmodel.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Base view model.
 */
public abstract class ViewModel {

    @Nullable
    private CompositeSubscription mSubscriptions;

    /**
     * Cleans up the view model
     */
    public void dispose() {
        unsubscribeFromDataStore();
    }

    /**
     * Cleans up current subscriptions and subscribes to new events.
     */
    public void subscribeToDataStore() {
        unsubscribeFromDataStore();
        mSubscriptions = new CompositeSubscription();
        subscribeToData(mSubscriptions);
    }

    private void unsubscribeFromDataStore() {
        if (mSubscriptions != null) {
            mSubscriptions.clear();
            mSubscriptions = null;
        }
    }

    /**
     * Provides {@link CompositeSubscription} that all bindings should be registered to.
     *
     * @param subscription that holds the subscriptions created by view model
     */
    protected abstract void subscribeToData(@NonNull final CompositeSubscription subscription);
}
