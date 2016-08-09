package com.lucilu.rxdynamicsearch.service;

import com.lucilu.rxdynamicsearch.dagger.Scopes.FragmentScope;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Futurice
 */
@FragmentScope
public class LifecycleService {

    public enum Lifecycle {ON_CREATE, ON_START, ON_RESUME, ON_PAUSE, ON_STOP, ON_DESTROY}

    private final PublishSubject<Lifecycle> mLifecycleStream;

    @Inject
    public LifecycleService() {
        mLifecycleStream = PublishSubject.create();
    }

    public void notifyLifecyle(@NonNull final Lifecycle lifecycleEvent) {
        mLifecycleStream.onNext(lifecycleEvent);
    }

    public Observable<Lifecycle> getLifecycleStream() {
        return mLifecycleStream.asObservable();
    }
}
