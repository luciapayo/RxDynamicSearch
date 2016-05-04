package com.lucilu.rxdynamicsearch.provider;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.data.pojo.Country;
import com.lucilu.rxdynamicsearch.provider.base.IJsonParserProvider;
import com.lucilu.rxdynamicsearch.provider.base.IResourceProvider;

import android.support.annotation.NonNull;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

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
    @NonNull
    public List<Country> parseListOfCountriesFromJson() {
        Type listType = new TypeToken<List<Country>>() {}.getType();
        Reader reader = new InputStreamReader(mResourceProvider.openRawResource(R.raw.countries));

        return mGson.fromJson(reader, listType);
    }

    public <T> Option<List<T>> parseListFromJson(String json) {
        Type listType = new TypeToken<List<T>>() {}.getType();

        try {
            mGson.fromJson(json, listType);
        } catch (JsonSyntaxException e) {
            // LOG
            return Option.NONE;
        }
    }

    public <T> Option<T> parseFromJson(String string, Class<T> classOfT) {
        try {
            mGson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            // LOG
            return Option.NONE;
        }
    }
}
