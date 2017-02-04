package com.lucilu.rxdynamicsearch.ui.adapter;

import com.lucilu.rxdynamicsearch.dagger.Scopes.FragmentScope;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IListItemComparator;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderBinder;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderFactory;
import com.lucilu.rxdynamicsearch.utils.Preconditions;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.DisplayableItem;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Implementation of {@link android.support.v7.widget.RecyclerView.Adapter} for {@link
 * DisplayableItem}.
 */
@FragmentScope
public final class RecyclerViewAdapter extends Adapter {

    @NonNull
    private final List<DisplayableItem> mItems = new ArrayList<>();

    @NonNull
    private final IListItemComparator mComparator;

    @NonNull
    private final IViewHolderFactory mInstantiator;

    @NonNull
    private final IViewHolderBinder<DisplayableItem> mBinder;

    @Inject
    RecyclerViewAdapter(@NonNull final IListItemComparator comparator,
                        @NonNull final IViewHolderFactory instantiator,
                        @NonNull final IViewHolderBinder<DisplayableItem> binder) {
        mComparator = comparator;
        mInstantiator = instantiator;
        mBinder = binder;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return mInstantiator.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mBinder.bind(holder, mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return mItems.get(position).type();
    }

    /**
     * Updates mItems currently stored in adapter with the new mItems.
     *
     * @param items collection to update the previous values
     */
    public void update(@NonNull final List<DisplayableItem> items) {
        Observable.fromCallable(() -> calculateDiff(items))
                  .subscribeOn(Schedulers.computation())
                  .observeOn(AndroidSchedulers.mainThread())
                  .doOnCompleted(() -> updateItems(items))
                  .subscribe(this::updateAdapterWithDiffResult);
    }

    private void updateItems(@NonNull final List<DisplayableItem> items) {
        Preconditions.assertUiThread();

        mItems.clear();
        mItems.addAll(items);
    }

    private void updateAdapterWithDiffResult(@NonNull final DiffUtil.DiffResult result) {
        Preconditions.assertUiThread();

        result.dispatchUpdatesTo(this);
    }

    private DiffUtil.DiffResult calculateDiff(@NonNull final List<DisplayableItem> newItems) {
        Preconditions.assertWorkerThread();

        return DiffUtil.calculateDiff(new DiffUtilCallback(mItems, newItems, mComparator));
    }
}
