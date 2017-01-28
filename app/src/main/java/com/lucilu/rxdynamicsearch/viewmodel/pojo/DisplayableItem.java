package com.lucilu.rxdynamicsearch.viewmodel.pojo;

import com.google.auto.value.AutoValue;

import android.support.annotation.NonNull;

@AutoValue
public abstract class DisplayableItem<T> {

    public abstract int type();

    @NonNull
    public abstract T model();

    @SuppressWarnings("NullableProblems")
    @AutoValue.Builder
    public abstract static class Builder<T> {

        @NonNull
        public abstract Builder<T> type(@NonNull int type);

        @NonNull
        public abstract Builder<T> model(@NonNull T model);

        @NonNull
        public abstract DisplayableItem<T> build();
    }

    @NonNull
    public static <T> Builder<T> builder() {
        return new AutoValue_DisplayableItem.Builder<>();
    }
}
