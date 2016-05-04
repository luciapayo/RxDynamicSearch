package com.lucilu.rxdynamicsearch.provider.base;

import android.support.annotation.NonNull;

import java.util.List;

import polanski.option.Option;

/**
 * Parsing from json files.
 */
public interface IJsonParserProvider {

    /**
     * Parses from json to a list of objects of type T.
     *
     * @param json the json to parse from
     * @param <T>  the type of the objects in the list
     * @return option of the list. Can be {@link Option#NONE} if the parsing fails.
     */
    <T> Option<List<T>> parseListFromJson(@NonNull final String json);

    /**
     * Parses from
     *
     * @param rawResourceId the id to the raw json file in resources
     * @param <T>           the type of the objects in the list
     * @return option of the list. Can be {@link Option#NONE} if the parsing fails.
     */
    <T> Option<List<T>> parseListFromRawJsonFile(final int rawResourceId);

    /**
     * Parses from json to object of type T.
     *
     * @param json the json to parse from
     * @param <T>  the type of the object
     * @return option of the object. Can be {@link Option#NONE} if the parsing fails.
     */
    <T> Option<T> parseFromJson(@NonNull final String json, @NonNull final Class<T> classOfT);
}
