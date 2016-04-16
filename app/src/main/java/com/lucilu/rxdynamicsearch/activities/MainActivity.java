package com.lucilu.rxdynamicsearch.activities;

import com.jakewharton.rxbinding.widget.RxSearchView;
import com.lucilu.baseapp.R;
import com.lucilu.rxdynamicsearch.SearchApplication;
import com.lucilu.rxdynamicsearch.activities.base.BaseActivity;
import com.lucilu.rxdynamicsearch.dagger.component.MainActivityComponent;
import com.lucilu.rxdynamicsearch.dagger.module.ActivityModule;
import com.lucilu.rxdynamicsearch.dagger.module.MainActivityModule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.SearchView;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;
import static com.lucilu.rxdynamicsearch.utils.ViewUtils.find;

public final class MainActivity extends BaseActivity {

    @Nullable
    private MainActivityComponent mComponent;

    @Nullable
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchView = find(this, R.id.searchView_countryName);
    }

    @Override
    protected void setActivityContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onBind(@NonNull final CompositeSubscription subscription) {

    }

    @Override
    public void onInject() {
        mComponent = createComponent();
        mComponent.inject(this);
    }

    @NonNull
    public MainActivityComponent getComponent() {
        return get(mComponent);
    }

    private MainActivityComponent createComponent() {
        SearchApplication app = (SearchApplication) getApplication();
        ActivityModule activityModule = new ActivityModule(this);
        MainActivityModule mainActivityModule = new MainActivityModule(getQueryStream());

        return app.getAppComponent().plusMainActivity(activityModule, mainActivityModule);
    }

    private Observable<CharSequence> getQueryStream() {
        return RxSearchView.queryTextChanges(get(mSearchView));
    }
}
