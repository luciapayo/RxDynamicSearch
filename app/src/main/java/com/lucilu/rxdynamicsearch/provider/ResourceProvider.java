package com.lucilu.rxdynamicsearch.provider;

import com.lucilu.rxdynamicsearch.provider.base.IResourceProvider;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.InputStream;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public final class ResourceProvider implements IResourceProvider {

    @NonNull
    private final Context mContext;

    public ResourceProvider(@NonNull final Context context) {
        mContext = get(context);
    }

    @NonNull
    @Override
    public InputStream openRawResource(final int id) {
        return mContext.getResources().openRawResource(id);
    }
}
