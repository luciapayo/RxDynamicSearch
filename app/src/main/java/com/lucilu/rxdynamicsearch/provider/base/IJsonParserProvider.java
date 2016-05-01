package com.lucilu.rxdynamicsearch.provider.base;

import com.lucilu.rxdynamicsearch.data.pojo.Country;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Parsing from json files.
 */
public interface IJsonParserProvider {

    /**
     * Parses the list of countries from json file.
     *
     * @return the list of countries.
     */
    @NonNull
    List<Country> parseListOfCountriesFromJson();
}
