package com.lucilu.rxdynamicsearch.dagger.module;

import com.lucilu.rxdynamicsearch.dagger.Qualifiers.ForActivity;
import com.lucilu.rxdynamicsearch.dagger.Scopes.ActivityScope;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

/**
 * Provides activity object and activity context.
 */
@Module
public final class ActivityModule {

    @NonNull
    private final Activity mActivity;

    public ActivityModule(@NonNull final Activity activity) {
        mActivity = get(activity);
    }

    @ActivityScope
    @Provides
    Activity providesActivity() {
        return mActivity;
    }

    @ActivityScope
    @ForActivity
    @Provides
    Context providesActivityContext() {
        return mActivity;
    }
}
