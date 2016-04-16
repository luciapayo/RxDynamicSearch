package com.lucilu.rxdynamicsearch.dagger.component;

import com.lucilu.rxdynamicsearch.activities.MainActivity;
import com.lucilu.rxdynamicsearch.dagger.module.ActivityModule;
import com.lucilu.rxdynamicsearch.dagger.module.MainActivityModule;

import dagger.Subcomponent;

/**
 *
 */
@Subcomponent(modules = {ActivityModule.class, MainActivityModule.class})
public interface MainActivityComponent {

    void inject(MainActivity activity);
}
