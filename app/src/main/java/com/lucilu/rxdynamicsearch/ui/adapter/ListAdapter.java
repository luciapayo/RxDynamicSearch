package com.lucilu.rxdynamicsearch.ui.adapter;

import com.lucilu.rxdynamicsearch.dagger.Scopes.FragmentScope;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IAdapterInteractor;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderFactory;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderPopulator;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import java.util.Collection;

import javax.inject.Inject;

import polanski.option.Option;
import polanski.option.function.Func0;

import static com.lucilu.rxdynamicsearch.Constants.ListItem.INVALID_VIEW_TYPE;
import static polanski.option.Option.ofObj;

/**
 * Implementation of {@link android.support.v7.widget.RecyclerView.Adapter} for {@link ListItem}.
 */
@FragmentScope
public final class ListAdapter extends Adapter {

    @NonNull
    private final IAdapterInteractor<ListItem> mInteractor;

    @NonNull
    private final IViewHolderFactory mInstantiator;

    @NonNull
    private final IViewHolderPopulator<ListItem> mPopulator;

    @Inject
    public ListAdapter(@NonNull final IAdapterInteractor<ListItem> interactor,
                       @NonNull final IViewHolderFactory instantiator,
                       @NonNull final IViewHolderPopulator<ListItem> populator) {
        mInteractor = interactor;
        mInstantiator = instantiator;
        mPopulator = populator;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return mInstantiator.createViewHolder(viewType);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mInteractor.getItem(position)
                   .ifSome(item -> mPopulator.populateViewHolder(ofObj(holder), item));
    }

    @Override
    public int getItemCount() {
        return mInteractor.getCount();
    }

    @Override
    public int getItemViewType(final int position) {
        return mInteractor.getItem(position)
                          .match(ListItem::type,
                                 () -> INVALID_VIEW_TYPE);
    }

    /**
     * Updates items currently stored in adapter with the new items.
     *
     * @param items collection to update the previous values
     */
    public void update(@NonNull final Collection<ListItem> items) {
        applyChanges(() -> mInteractor.update(items));
    }

    /**
     * Appends new items to currently existing ones.
     *
     * @param items collection to append
     */
    public void append(@NonNull final Collection<ListItem> items) {
        applyChanges(() -> mInteractor.append(items));
    }

    /**
     * Removes an item at the specified position.
     *
     * @param position of the item to be removed
     */
    public void remove(@IntRange(from = 0) final int position) {
        applyChanges(() -> mInteractor.remove(position));
    }

    /**
     * Removes specific item.
     *
     * @param item to be removed
     */
    public void remove(@NonNull final ListItem item) {
        applyChanges(() -> mInteractor.remove(item));
    }

    /**
     * Removes a collection of items.
     *
     * @param items to be removed
     */
    public void removeAll(@NonNull final Collection<ListItem> items) {
        applyChanges(() -> mInteractor.removeAll(items));
    }

    /**
     * Returns an option of the {@link ListItem} at the position.
     *
     * @param position of the item
     * @return option of the {@link ListItem} at the position or {@link Option#NONE} if wasn't found
     */
    @NonNull
    public Option<ListItem> getItem(final int position) {
        return mInteractor.getItem(position);
    }

    /**
     * Returns an option of the index where the {@link ListItem} exists.
     *
     * @param item of item to be found
     * @return option of index of item or {@link Option#NONE} if wasn't found
     */
    public Option<Integer> getItemPosition(@NonNull final ListItem item) {
        return mInteractor.getItemPosition(item);
    }

    private void applyChanges(@NonNull final Func0<Boolean> applyFunction) {
        if (applyFunction.call()) {
            notifyDataSetChanged();
        }
    }
}
