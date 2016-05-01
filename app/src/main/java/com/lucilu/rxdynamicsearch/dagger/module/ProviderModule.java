package com.lucilu.rxdynamicsearch.dagger.module;

import com.google.gson.Gson;

import com.lucilu.rxdynamicsearch.dagger.Qualifiers.ForApplication;
import com.lucilu.rxdynamicsearch.provider.JsonParserProvider;
import com.lucilu.rxdynamicsearch.provider.ResourceProvider;
import com.lucilu.rxdynamicsearch.provider.base.IJsonParserProvider;
import com.lucilu.rxdynamicsearch.provider.base.IResourceProvider;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module(includes = DataModule.class)
public final class ProviderModule {

    @Provides
    IResourceProvider provideResourceProvider(@ForApplication final Context context) {
        return new ResourceProvider(context);
    }

    @Provides
    IJsonParserProvider provideJsonParserProvider(final Gson gson,
                                                  final IResourceProvider resourceProvider) {
        return new JsonParserProvider(gson, resourceProvider);
    }
}
