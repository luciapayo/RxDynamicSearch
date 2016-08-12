package com.lucilu.rxdynamicsearch.ui.adapter.viewholder;

import com.lucilu.rxdynamicsearch.StaticCounter;
import com.lucilu.rxdynamicsearch.viewmodel.base.ViewModel;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import polanski.option.Option;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

import static polanski.option.Option.none;
import static polanski.option.Option.ofObj;

public abstract class BindingViewHolder<T extends ViewModel> extends ViewHolder {

    @NonNull
    private Option<T> mViewModel = none();

    @NonNull
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();

    public BindingViewHolder(@NonNull final View itemView) {
        super(itemView);
    }

    @CallSuper
    public void bind(@NonNull final T viewModel) {
        mViewModel = ofObj(viewModel);
        subscribe();
    }

    private void subscribe() {
        mViewModel.ifSome(ViewModel::subscribeToDataStore);
        subscribeViewHolder(mSubscriptions);
        StaticCounter.incrementDataSubscriptions();
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
        StaticCounter.decrementDataSubscriptions();
        Timber.d(">>>> Unsubscribing %s", hashCode());
    }

    @NonNull
    Option<T> getViewModel() {
        return mViewModel;
    }

}
