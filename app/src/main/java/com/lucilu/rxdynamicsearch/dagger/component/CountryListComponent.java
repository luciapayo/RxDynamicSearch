package com.lucilu.rxdynamicsearch.dagger.component;

import com.lucilu.rxdynamicsearch.dagger.Scopes.FragmentScope;
import com.lucilu.rxdynamicsearch.dagger.module.CountryListModule;
import com.lucilu.rxdynamicsearch.dagger.module.SearchFragmentModule;
import com.lucilu.rxdynamicsearch.fragments.CountryListFragment;

import dagger.Subcomponent;

/**
 * {@link CountryListFragment} component.
 */
@FragmentScope
@Subcomponent(modules = {SearchFragmentModule.class, CountryListModule.class})
public interface CountryListComponent {

    void inject(CountryListFragment fragment);
}
