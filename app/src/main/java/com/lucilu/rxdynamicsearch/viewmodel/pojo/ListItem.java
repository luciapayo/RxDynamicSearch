package com.lucilu.rxdynamicsearch.viewmodel.pojo;

import com.google.auto.value.AutoValue;

import android.support.annotation.NonNull;

import polanski.option.Option;

import static com.google.common.base.Preconditions.checkArgument;

@AutoValue
public abstract class ListItem {

    public enum Type {
        COUNTRY;

        /**
         * Converts an ordinal into a {@link Type}.
         *
         * @param ordinal to convert
         * @return the {@link Type} corresponding to the ordinal
         * @throws IllegalArgumentException when the ordinal is not valid
         */
        @NonNull
        public static Type typeFromOrdinal(final int ordinal) throws IllegalArgumentException {
            assertOrdinal(ordinal);

            return Type.values()[ordinal];
        }

        private static void assertOrdinal(final int ordinal) {
            checkArgument(ordinal >= 0, "The ordinal is smaller than 0: " + ordinal);
            checkArgument(ordinal < Type.values().length, "The ordinal is too big: " + ordinal);
        }
    }

    @NonNull
    public abstract Type type();

    @NonNull
    public abstract Option<Object> model();

    @SuppressWarnings("NullableProblems")
    @AutoValue.Builder
    public abstract static class Builder {

        @NonNull
        public abstract Builder type(@NonNull Type type);

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
