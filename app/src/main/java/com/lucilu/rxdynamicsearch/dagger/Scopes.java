package com.lucilu.rxdynamicsearch.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

/**
 * Definition of dagger scopes besides {@link Singleton} that is available by default.
 */
public final class Scopes {

    @Scope
    @Retention(RetentionPolicy.SOURCE)
    public @interface ActivityScope {
    }
}
