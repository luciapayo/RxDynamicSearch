package com.lucilu.rxdynamicsearch.fragments.base;

import com.lucilu.rxdynamicsearch.IInjectable;
import com.lucilu.rxdynamicsearch.service.LifecycleService;
import com.lucilu.rxdynamicsearch.viewmodel.base.ViewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

import static com.lucilu.rxdynamicsearch.service.LifecycleService.Lifecycle.ON_CREATE;
import static com.lucilu.rxdynamicsearch.service.LifecycleService.Lifecycle.ON_DESTROY;
import static com.lucilu.rxdynamicsearch.service.LifecycleService.Lifecycle.ON_PAUSE;
import static com.lucilu.rxdynamicsearch.service.LifecycleService.Lifecycle.ON_RESUME;
import static com.lucilu.rxdynamicsearch.service.LifecycleService.Lifecycle.ON_START;
import static com.lucilu.rxdynamicsearch.service.LifecycleService.Lifecycle.ON_STOP;

/**
 * This base fragment provides the common functionality to all fragments in the app.
 */
public abstract class BaseFragment extends Fragment implements IInjectable {

    @Inject
    LifecycleService mLifecycleService;

    @NonNull
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onInject();

        getViewModel().subscribeToDataStore();

        mLifecycleService.notifyLifecyle(ON_CREATE);
    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecycleService.notifyLifecyle(ON_START);
    }

    @Override
    public void onResume() {
        super.onResume();
        onBind(mSubscription);
        mLifecycleService.notifyLifecyle(ON_RESUME);
    }

    @Override
    public void onPause() {
        mSubscription.clear();
        super.onPause();
        mLifecycleService.notifyLifecyle(ON_PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();
        mLifecycleService.notifyLifecyle(ON_STOP);
    }

    @Override
    public void onDestroyView() {
        getViewModel().dispose();
        mLifecycleService.notifyLifecyle(ON_DESTROY);
        super.onDestroyView();
    }

    protected abstract void onBind(@NonNull final CompositeSubscription subscription);

    @NonNull
    protected abstract ViewModel getViewModel();
}
