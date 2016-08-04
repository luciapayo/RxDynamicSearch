package com.lucilu.rxdynamicsearch.dagger.module;

import com.lucilu.rxdynamicsearch.dagger.Qualifiers.ForActivity;
import com.lucilu.rxdynamicsearch.ui.adapter.AdapterInteractor;
import com.lucilu.rxdynamicsearch.ui.adapter.CountryListViewHolderFactory;
import com.lucilu.rxdynamicsearch.ui.adapter.CountryListViewHolderBinder;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IAdapterInteractor;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderBinder;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderFactory;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public final class ListModule {

    @Provides
    IAdapterInteractor<ListItem> provideAdapterInteractor() {
        return new AdapterInteractor<>();
    }

    @Provides
    IViewHolderFactory provideViewHoldeFactory(@ForActivity Context context) {
        return new CountryListViewHolderFactory(context);
    }

    @Provides
    IViewHolderBinder<ListItem> provideViewHodelPopulator() {
        return new CountryListViewHolderBinder();
    }
}
