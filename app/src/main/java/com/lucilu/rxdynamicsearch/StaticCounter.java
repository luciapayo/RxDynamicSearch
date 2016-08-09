package com.lucilu.rxdynamicsearch;

import com.lucilu.rxdynamicsearch.utils.Preconditions;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by Futurice
 */
public class StaticCounter {

    private static int dataSubscriptions = 0;
    private static int lifecycleSubscriptions = 0;

    private static BehaviorSubject<Integer> dataSubscriptionsStream = BehaviorSubject
            .create(dataSubscriptions);
    private static BehaviorSubject<Integer> lifecycleSubscriptionsStream = BehaviorSubject
            .create(lifecycleSubscriptions);

    public static void incrementDataSubscriptions() {
        Preconditions.assertUiThread();
        dataSubscriptions++;
        dataSubscriptionsStream.onNext(dataSubscriptions);
    }

    public static void decrementDataSubscriptions() {
        Preconditions.assertUiThread();
        dataSubscriptions--;
        dataSubscriptionsStream.onNext(dataSubscriptions);
    }

    public static void incrementLifecycleSubscriptions() {
        Preconditions.assertUiThread();
        lifecycleSubscriptions++;
        lifecycleSubscriptionsStream.onNext(lifecycleSubscriptions);
    }

    public static void decrementLifecylceSubcriptions() {
        Preconditions.assertUiThread();
        lifecycleSubscriptions--;
        lifecycleSubscriptionsStream.onNext(lifecycleSubscriptions);
    }

    public static Observable<Integer> getDataSubscriptionsStream() {
        Preconditions.assertUiThread();
        return dataSubscriptionsStream.asObservable();
    }

    public static Observable<Integer> getLifecycleSubscriptionsStream() {
        Preconditions.assertUiThread();
        return lifecycleSubscriptionsStream.asObservable();
    }
}
