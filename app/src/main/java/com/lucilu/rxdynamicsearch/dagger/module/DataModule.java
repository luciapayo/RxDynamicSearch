package com.lucilu.rxdynamicsearch.dagger.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

@Module
public final class DataModule {

    @Provides
    GsonBuilder providesGsonBuilder() {
        return new GsonBuilder();
    }

    @Provides
    Gson providesGson(GsonBuilder gsonBuilder) {
        return gsonBuilder.create();
    }
}
