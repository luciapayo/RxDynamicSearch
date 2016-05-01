package com.lucilu.rxdynamicsearch.provider.base;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import java.io.InputStream;

/**
 * Wraps the android {@link Resources} for testing purposes.
 */
public interface IResourceProvider {

    /**
     * {@link Resources#openRawResource(int)}
     */
    @NonNull
    InputStream openRawResource(final int id);
}
