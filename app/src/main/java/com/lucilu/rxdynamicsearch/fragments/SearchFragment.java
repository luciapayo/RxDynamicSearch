package com.lucilu.rxdynamicsearch.fragments;

import com.jakewharton.rxbinding.widget.RxSearchView;
import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.activities.MainActivity;
import com.lucilu.rxdynamicsearch.dagger.component.SearchFragmentComponent;
import com.lucilu.rxdynamicsearch.dagger.module.SearchFragmentModule;
import com.lucilu.rxdynamicsearch.fragments.base.BaseFragment;
import com.lucilu.rxdynamicsearch.utils.ViewUtils;
import com.lucilu.rxdynamicsearch.viewmodel.SearchFragmentViewModel;
import com.lucilu.rxdynamicsearch.viewmodel.base.ViewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import javax.inject.Inject;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public final class SearchFragment extends BaseFragment {

    @Nullable
    @Inject
    SearchFragmentViewModel mViewModel;

    @Nullable
    private SearchView mSearchView;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSearchView = ViewUtils.find(view, R.id.searchView_countryName);
    }

    @Override
    protected void onBind(@NonNull final CompositeSubscription subscription) {

    }

    @NonNull
    @Override
    protected ViewModel getViewModel() {
        return get(mViewModel);
    }

    @Override
    public void onInject() {
        createComponent().inject(this);
    }

    private SearchFragmentComponent createComponent() {
        MainActivity activity = (MainActivity) getActivity();
        SearchFragmentModule searchFragmentModule = new SearchFragmentModule(getQueryStream());

        return activity.getComponent().plusSearchFragment(searchFragmentModule);
    }

    private Observable<CharSequence> getQueryStream() {
        return RxSearchView.queryTextChanges(get(mSearchView));
    }
}
