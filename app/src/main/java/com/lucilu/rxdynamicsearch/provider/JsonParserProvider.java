package com.lucilu.rxdynamicsearch.provider;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import com.lucilu.rxdynamicsearch.provider.base.IJsonParserProvider;
import com.lucilu.rxdynamicsearch.provider.base.IResourceProvider;
import com.lucilu.rxdynamicsearch.utils.option.Option;

import android.support.annotation.NonNull;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import timber.log.Timber;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;
import static com.lucilu.rxdynamicsearch.utils.option.Option.none;
import static com.lucilu.rxdynamicsearch.utils.option.Option.ofObj;

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

    public <T> Option<List<T>> parseListFromJson(@NonNull final String json) {
        Type listType = new TypeToken<List<T>>() {}.getType();

        try {
            return ofObj(mGson.fromJson(json, listType));
        } catch (JsonSyntaxException e) {
            Timber.e(e, "Error parsing from json %s", listType);
            return none();
        }
    }

    @Override
    public <T> Option<List<T>> parseListFromRawJsonFile(final int rawResourceId) {
        Type listType = new TypeToken<List<T>>() {}.getType();
        InputStreamReader isr = new InputStreamReader(
                mResourceProvider.openRawResource(rawResourceId));

        try {
            return ofObj(mGson.fromJson(isr, listType));
        } catch (JsonSyntaxException e) {
            Timber.e(e, "Error parsing from json %s", listType);
            return none();
        }
    }

    public <T> Option<T> parseFromJson(@NonNull final String json,
                                       @NonNull final Class<T> classOfT) {
        try {
            return ofObj(mGson.fromJson(json, classOfT));
        } catch (JsonSyntaxException e) {
            Timber.e(e, "Error parsing from json %s", classOfT);
            return none();
        }
    }
}
