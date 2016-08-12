package com.lucilu.rxdynamicsearch.fragments.base;

import com.lucilu.rxdynamicsearch.IInjectable;
import com.lucilu.rxdynamicsearch.viewmodel.base.ViewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import rx.subscriptions.CompositeSubscription;

/**
 * This base fragment provides the common functionality to all fragments in the app.
 */
public abstract class BaseFragment extends Fragment implements IInjectable {

    @NonNull
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onInject();

        getViewModel().subscribeToDataStore();
    }

    @Override
    public void onResume() {
        super.onResume();
        onBind(mSubscription);
    }

    @Override
    public void onPause() {
        mSubscription.clear();
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        getViewModel().dispose();
        super.onDestroyView();
    }

    protected abstract void onBind(@NonNull final CompositeSubscription subscription);

    @NonNull
    protected abstract ViewModel getViewModel();
}
