package com.lucilu.rxdynamicsearch.dagger.module;

import com.lucilu.rxdynamicsearch.dagger.Qualifiers.ForActivity;
import com.lucilu.rxdynamicsearch.ui.adapter.CountryListItemComparator;
import com.lucilu.rxdynamicsearch.ui.adapter.CountryListViewHolderBinder;
import com.lucilu.rxdynamicsearch.ui.adapter.CountryListViewHolderFactory;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IListItemComparator;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderBinder;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderFactory;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.DisplayableItem;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public final class CountryListModule {

    @Provides
    IViewHolderFactory provideViewHolderFactory(@ForActivity Context context) {
        return new CountryListViewHolderFactory(context);
    }

    @Provides
    IViewHolderBinder<DisplayableItem> provideViewHolderBinder() {
        return new CountryListViewHolderBinder();
    }

    @Provides
    IListItemComparator provideListItemComparator() {
        return new CountryListItemComparator();
    }
}
