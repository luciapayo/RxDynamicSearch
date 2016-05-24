package com.lucilu.rxdynamicsearch.provider.base;

import android.support.annotation.NonNull;

import java.util.List;

import polanski.option.Option;

/**
 * Parsing from json files.
 */
public interface IJsonParserProvider {

    /**
     * Parses from
     *
     * @param rawResourceId      the id to the raw json file in resources
     * @param classOfType the class of the type of the objects in the list
     * @param <T>                the type of the objects in the list
     * @return option of the list. Can be {@link Option#NONE} if the parsing fails.
     */
    <T> Option<List<T>> parseListFromRawJsonFile(final int rawResourceId,
                                                 @NonNull final Class<T> classOfType);
}
