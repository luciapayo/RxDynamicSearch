package com.lucilu.rxdynamicsearch.dagger.module;

import com.lucilu.rxdynamicsearch.dagger.Qualifiers.ForActivity;
import com.lucilu.rxdynamicsearch.ui.adapter.AdapterInteractor;
import com.lucilu.rxdynamicsearch.ui.adapter.ListItemViewHolderFactory;
import com.lucilu.rxdynamicsearch.ui.adapter.ListItemViewHolderPopulator;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IAdapterInteractor;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderFactory;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderPopulator;
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
        return new ListItemViewHolderFactory(context);
    }

    @Provides
    IViewHolderPopulator<ListItem> provideViewHodelPopulator() {
        return new ListItemViewHolderPopulator();
    }
}
