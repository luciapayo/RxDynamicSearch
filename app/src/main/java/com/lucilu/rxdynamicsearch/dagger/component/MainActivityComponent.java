package com.lucilu.rxdynamicsearch.dagger.component;

import com.lucilu.rxdynamicsearch.activities.MainActivity;
import com.lucilu.rxdynamicsearch.dagger.Scopes.ActivityScope;
import com.lucilu.rxdynamicsearch.dagger.module.ActivityModule;
import com.lucilu.rxdynamicsearch.dagger.module.RepositoryModule;
import com.lucilu.rxdynamicsearch.dagger.module.SearchFragmentModule;

import dagger.Subcomponent;

/**
 * {@link MainActivity} component.
 */
@ActivityScope
@Subcomponent(modules = {ActivityModule.class, RepositoryModule.class})
public interface MainActivityComponent {

    void inject(MainActivity activity);

    SearchFragmentComponent plusSearchFragment(SearchFragmentModule module);
}
