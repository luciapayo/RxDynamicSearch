package com.lucilu.rxdynamicsearch.dagger.component;

import com.lucilu.rxdynamicsearch.SearchApplication;
import com.lucilu.rxdynamicsearch.dagger.module.ActivityModule;
import com.lucilu.rxdynamicsearch.dagger.module.AppModule;
import com.lucilu.rxdynamicsearch.dagger.module.InstrumentationModule;
import com.lucilu.rxdynamicsearch.dagger.module.ProviderModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Top level injection component.
 */
@Singleton
@Component(modules = {InstrumentationModule.class, AppModule.class, ProviderModule.class})
public interface AppComponent {

    void inject(SearchApplication app);

    MainActivityComponent plusMainActivity(ActivityModule activityModule);

    class Initializer {

        private Initializer() {
            throw new AssertionError("Do not create an instance!");
        }

        /**
         * Creates the top level injection component.
         *
         * @param app application object
         * @return component object
         */
        public static AppComponent init(final SearchApplication app) {
            return DaggerAppComponent.builder()
                                     .appModule(new AppModule(app))
                                     .build();
        }
    }
}
