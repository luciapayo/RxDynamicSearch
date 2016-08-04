package com.lucilu.rxdynamicsearch.dagger.component;

import com.lucilu.rxdynamicsearch.dagger.Scopes.FragmentScope;
import com.lucilu.rxdynamicsearch.dagger.module.ListModule;
import com.lucilu.rxdynamicsearch.dagger.module.SearchFragmentModule;
import com.lucilu.rxdynamicsearch.fragments.CountryListFragment;

import dagger.Subcomponent;

/**
 * {@link CountryListFragment} component.
 */
@FragmentScope
@Subcomponent(modules = {SearchFragmentModule.class, ListModule.class})
public interface SearchFragmentComponent {

    void inject(CountryListFragment fragment);
}
