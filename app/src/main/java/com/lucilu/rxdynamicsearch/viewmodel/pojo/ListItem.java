package com.lucilu.rxdynamicsearch.viewmodel.pojo;

import com.google.auto.value.AutoValue;

import android.support.annotation.NonNull;

import polanski.option.Option;

@AutoValue
public abstract class ListItem<T> {

    public abstract int type();

    @NonNull
    public abstract Option<T> model();

    @SuppressWarnings("NullableProblems")
    @AutoValue.Builder
    public abstract static class Builder<T> {

        @NonNull
        public abstract Builder<T> type(@NonNull int type);

        @NonNull
        public abstract Builder<T> model(@NonNull Option<T> model);

        @NonNull
        public abstract ListItem<T> build();
    }

    @NonNull
    public static <T> Builder<T> builder() {
        return new AutoValue_ListItem.Builder<>();
    }
}
