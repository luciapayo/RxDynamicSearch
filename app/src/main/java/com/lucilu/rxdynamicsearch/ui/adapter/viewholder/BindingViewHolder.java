package com.lucilu.rxdynamicsearch.ui.adapter.viewholder;

import com.lucilu.rxdynamicsearch.service.LifecycleService;
import com.lucilu.rxdynamicsearch.service.LifecycleService.Lifecycle;
import com.lucilu.rxdynamicsearch.viewmodel.base.ViewModel;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import polanski.option.Option;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

import static polanski.option.Option.none;
import static polanski.option.Option.ofObj;

public abstract class BindingViewHolder<T extends ViewModel> extends ViewHolder {

    @NonNull
    private Option<T> mViewModel = none();

    @NonNull
    private final LifecycleService mLifecycleService;

    @NonNull
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();

    @NonNull
    private final Subscription mLifecycleSubscription;

    public BindingViewHolder(@NonNull final View itemView,
                             @NonNull final LifecycleService lifecycleService) {
        super(itemView);
        mLifecycleService = lifecycleService;
        mLifecycleSubscription = init();
    }

    private Subscription init() {
        return mLifecycleService.getLifecycleStream()
                                .distinctUntilChanged()
                                .subscribe(this::actOnLifecycleEvent,
                                           error -> Timber
                                                   .d("Error dispatching the lifecycle event",
                                                      error));
    }

    private void actOnLifecycleEvent(@NonNull final Lifecycle event) {
        switch (event) {
            case ON_RESUME:
                subscribe();
                break;
            case ON_PAUSE:
                unsubscribe();
                break;
            case ON_DESTROY:
                unsubscribeLifecycle();
                break;
        }
    }

    @CallSuper
    public void bind(@NonNull final T viewModel) {
        mViewModel = ofObj(viewModel);
        subscribe();
    }

    private void subscribe() {
        mViewModel.ifSome(ViewModel::subscribeToDataStore);
        subscribeViewHolder(mSubscriptions);
        Timber.d(">>>> Subscribing %s", hashCode());
    }

    protected abstract void subscribeViewHolder(@NonNull final CompositeSubscription subscriptions);

    @CallSuper
    public void unbind() {
        unsubscribe();
        mViewModel = none();
    }

    private void unsubscribe() {
        mSubscriptions.clear();
        getViewModel().ifSome(ViewModel::dispose);
        Timber.d(">>>> Unsubscribing %s", hashCode());
    }

    @NonNull
    Option<T> getViewModel() {
        return mViewModel;
    }

    private void unsubscribeLifecycle() {
        Timber.d(">>>> Unsubscribing from lifecycle " + hashCode());
        if (!mLifecycleSubscription.isUnsubscribed()) {
            mLifecycleSubscription.unsubscribe();
        }
    }
}
