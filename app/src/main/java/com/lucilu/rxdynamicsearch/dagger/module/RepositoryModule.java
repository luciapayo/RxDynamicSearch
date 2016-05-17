package com.lucilu.rxdynamicsearch.dagger.module;

import com.lucilu.rxdynamicsearch.provider.base.IJsonParserProvider;
import com.lucilu.rxdynamicsearch.provider.base.IResourceProvider;
import com.lucilu.rxdynamicsearch.repository.CountryRepository;
import com.lucilu.rxdynamicsearch.repository.base.ICountryRepository;

import dagger.Module;
import dagger.Provides;

@Module
public final class RepositoryModule {

    @Provides
    ICountryRepository provideCountryRepository(final IJsonParserProvider jsonParserProvider,
                                                final IResourceProvider resourceProvider) {
        return new CountryRepository(jsonParserProvider, resourceProvider);
    }
}
