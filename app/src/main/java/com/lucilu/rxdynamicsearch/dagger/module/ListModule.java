package com.lucilu.rxdynamicsearch.dagger.module;

import com.lucilu.rxdynamicsearch.dagger.Scopes.FragmentScope;
import com.lucilu.rxdynamicsearch.ui.adapter.AdapterInteractor;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IAdapterInteractor;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;

import dagger.Module;
import dagger.Provides;

@Module
public final class ListModule {

    @Provides
    @FragmentScope
    IAdapterInteractor<ListItem> provideAdapterInteractor() {
        return new AdapterInteractor<>();
    }
}
