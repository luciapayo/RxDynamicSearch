package com.lucilu.rxdynamicsearch.activities.base;

import com.lucilu.rxdynamicsearch.IInjectable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 *
 */
public abstract class BaseActivity extends AppCompatActivity implements IInjectable {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onInject();
    }
}
