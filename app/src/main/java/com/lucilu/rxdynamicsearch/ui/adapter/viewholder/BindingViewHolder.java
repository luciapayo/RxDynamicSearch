package com.lucilu.rxdynamicsearch.ui.adapter.viewholder;

import com.lucilu.rxdynamicsearch.viewmodel.base.ViewModel;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;

import rx.subscriptions.CompositeSubscription;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.checkNotNull;

public abstract class BindingViewHolder<T extends ViewModel> extends ViewHolder {

    @Nullable
    private T mViewModel;

    @NonNull
    private final CompositeSubscription subscriptions = new CompositeSubscription();

    public BindingViewHolder(@NonNull final View itemView) {
        super(itemView);
    }

    @CallSuper
    public void bind(@NonNull final T viewModel) {
        mViewModel = viewModel;
        mViewModel.subscribeToDataStore();
        subscribeViewHolder(subscriptions);
    }

    protected abstract void subscribeViewHolder(@NonNull final CompositeSubscription subscriptions);

    @CallSuper
    public void unbind() {
        subscriptions.clear();
        disposeViewModel();
    }

    @NonNull
    T getViewModel() {
        checkNotNull(mViewModel, "View model is null! Did you forget to bind?");
        return mViewModel;
    }

    private void disposeViewModel() {
        checkNotNull(mViewModel, "View model is null when disposing");
        mViewModel.dispose();
        mViewModel = null;
    }
}
