package com.lucilu.rxdynamicsearch;

import com.lucilu.rxdynamicsearch.utils.Preconditions;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by Futurice
 */
public class StaticCounter {

    private static int dataSubscriptions = 0;

    private static BehaviorSubject<Integer> dataSubscriptionsStream = BehaviorSubject
            .create(dataSubscriptions);

    public static void incrementDataSubscriptions() {
        Preconditions.assertUiThread();
        dataSubscriptions++;
        dataSubscriptionsStream.onNext(dataSubscriptions);
    }

    public static void decrementDataSubscriptions() {
        Preconditions.assertUiThread();
        if (dataSubscriptions > 0) {
            dataSubscriptions--;
        }
        dataSubscriptionsStream.onNext(dataSubscriptions);
    }

    public static Observable<Integer> getDataSubscriptionsStream() {
        Preconditions.assertUiThread();
        return dataSubscriptionsStream.asObservable();
    }
}
