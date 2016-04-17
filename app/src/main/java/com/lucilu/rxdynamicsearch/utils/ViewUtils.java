package com.lucilu.rxdynamicsearch.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.checkNotNull;
import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public final class ViewUtils {

    @SuppressWarnings("unchecked")
    @NonNull
    public static <T extends View> T find(@NonNull final Activity activity, int id) {
        checkNotNull(activity, "The activity cannot be null");

        return (T) get(activity.findViewById(id));
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public static <T extends View> T find(@NonNull final View view, int id) {
        return (T) get(view.findViewById(id));
    }
}
