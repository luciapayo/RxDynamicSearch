package com.lucilu.rxdynamicsearch.service;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import static com.lucilu.rxdynamicsearch.Constants.DYNAMIC_SEARCH_DELAY_MILLIS;
import static com.lucilu.rxdynamicsearch.Constants.EMPTY_QUERY;
import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public class SearchService {

    private final PublishSubject<String> dynamicQueryStream = PublishSubject.create();
    private final PublishSubject<String> submittedQueryStream = PublishSubject.create();
    private final Observable<String> searchQueryStream;

    public SearchService() {
        searchQueryStream = defineSearchQueryStream();
    }

    private Observable<String> defineSearchQueryStream() {
        return Observable
                .merge(defineDynamicQueryStream(), defineSubmittedQueryStream())
                .startWith(EMPTY_QUERY)
                .distinctUntilChanged();
    }

    private Observable<String> defineDynamicQueryStream() {
        return dynamicQueryStream
                .debounce(DYNAMIC_SEARCH_DELAY_MILLIS,
                          TimeUnit.MILLISECONDS,
                          Schedulers.computation())
                .map(String::trim);
    }

    private Observable<String> defineSubmittedQueryStream() {
        return submittedQueryStream
                .map(String::trim);
    }

    public void queryTextChanged(@NonNull final String query) {
        dynamicQueryStream.onNext(get(query));
    }

    public void onQueryTextSubmit(@NonNull final String query) {
        submittedQueryStream.onNext(get(query));
    }

    public Observable<String> getSearchQueryStream() {
        return searchQueryStream;
    }
}
