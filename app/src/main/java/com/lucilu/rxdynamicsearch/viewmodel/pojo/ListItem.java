package com.lucilu.rxdynamicsearch.viewmodel.pojo;

import com.google.auto.value.AutoValue;

import android.support.annotation.NonNull;

import polanski.option.Option;

@AutoValue
public abstract class ListItem {

    public abstract int type();

    @NonNull
    public abstract Option<Object> model();

    @SuppressWarnings("NullableProblems")
    @AutoValue.Builder
    public abstract static class Builder {

        @NonNull
        public abstract Builder type(@NonNull int type);

        @NonNull
        public abstract Builder model(@NonNull Option<Object> model);

        @NonNull
        public abstract ListItem build();
    }

    @NonNull
    public static Builder builder() {
        return new AutoValue_ListItem.Builder();
    }
}
