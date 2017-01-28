package com.lucilu.rxdynamicsearch.dagger.module;

import com.lucilu.rxdynamicsearch.dagger.Scopes.FragmentScope;
import com.lucilu.rxdynamicsearch.ui.adapter.AdapterInteractor;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IAdapterInteractor;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.DisplayableItem;

import dagger.Module;
import dagger.Provides;

@Module
public final class ListModule {

    @Provides
    @FragmentScope
    IAdapterInteractor<DisplayableItem> provideAdapterInteractor() {
        return new AdapterInteractor<>();
    }
}
