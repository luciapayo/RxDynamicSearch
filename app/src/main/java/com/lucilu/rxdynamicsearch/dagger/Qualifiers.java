package com.lucilu.rxdynamicsearch.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Dagger qualifiers.
 */
public class Qualifiers {

    @Qualifier
    @Retention(RetentionPolicy.SOURCE)
    public @interface ForApplication {
    }

    @Qualifier
    @Retention(RetentionPolicy.SOURCE)
    public @interface ForActivity {
    }
}
