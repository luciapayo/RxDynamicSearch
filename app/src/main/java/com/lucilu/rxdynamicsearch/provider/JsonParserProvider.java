package com.lucilu.rxdynamicsearch.provider;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.lucilu.rxdynamicsearch.provider.base.IJsonParserProvider;
import com.lucilu.rxdynamicsearch.provider.base.IResourceProvider;
import com.lucilu.rxdynamicsearch.utils.Preconditions;

import android.support.annotation.NonNull;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import polanski.option.Option;
import timber.log.Timber;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;
import static polanski.option.Option.none;
import static polanski.option.Option.ofObj;

public final class JsonParserProvider implements IJsonParserProvider {

    @NonNull
    private final Gson mGson;

    @NonNull
    private final IResourceProvider mResourceProvider;

    public JsonParserProvider(@NonNull final Gson gson,
                              @NonNull final IResourceProvider resourceProvider) {
        mGson = get(gson);
        mResourceProvider = get(resourceProvider);
    }

    @Override
    public <T> Option<List<T>> parseListFromRawJsonFile(final int rawResourceId,
                                                        @NonNull final Class<T> classOfType) {
        Preconditions.assertWorkerThread();

        Type listType = new ListOfJsonType<>(classOfType);
        InputStreamReader isr = new InputStreamReader(
                mResourceProvider.openRawResource(rawResourceId));
        try {
            List<T> list = mGson.fromJson(isr, listType);
            return ofObj(list);
        } catch (JsonSyntaxException e) {
            Timber.e(e, "Error parsing from json %s", listType);
            return none();
        }
    }
}
