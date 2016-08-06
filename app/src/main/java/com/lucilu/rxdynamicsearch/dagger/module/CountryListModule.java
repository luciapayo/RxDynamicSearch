package com.lucilu.rxdynamicsearch.dagger.module;

import com.lucilu.rxdynamicsearch.dagger.Qualifiers.ForActivity;
import com.lucilu.rxdynamicsearch.ui.adapter.CountryListViewHolderBinder;
import com.lucilu.rxdynamicsearch.ui.adapter.CountryListViewHolderFactory;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderBinder;
import com.lucilu.rxdynamicsearch.ui.adapter.base.IViewHolderFactory;
import com.lucilu.rxdynamicsearch.viewmodel.CountryItemViewModel_Factory;
import com.lucilu.rxdynamicsearch.viewmodel.pojo.ListItem;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module(includes = ListModule.class)
public final class CountryListModule {

    @Provides
    IViewHolderFactory provideViewHoldeFactory(@ForActivity Context context) {
        return new CountryListViewHolderFactory(context);
    }

    @Provides
    IViewHolderBinder<ListItem> provideViewHodelBinder() {
        return new CountryListViewHolderBinder(new CountryItemViewModel_Factory());
    }
}
