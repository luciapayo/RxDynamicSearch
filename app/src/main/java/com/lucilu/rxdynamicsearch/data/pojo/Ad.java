package com.lucilu.rxdynamicsearch.data.pojo;

import com.google.auto.value.AutoValue;

import android.support.annotation.NonNull;

@AutoValue
public abstract class Ad {

    public abstract String header();

    public static Ad createAd(@NonNull final String header) {
        return new AutoValue_Ad(header);
    }
}
