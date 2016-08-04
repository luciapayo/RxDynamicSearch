package com.lucilu.rxdynamicsearch.fragments;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.activities.MainActivity;
import com.lucilu.rxdynamicsearch.dagger.component.SearchFragmentComponent;
import com.lucilu.rxdynamicsearch.dagger.module.SearchFragmentModule;
import com.lucilu.rxdynamicsearch.fragments.base.BaseFragment;
import com.lucilu.rxdynamicsearch.ui.adapter.ListAdapter;
import com.lucilu.rxdynamicsearch.utils.ViewUtils;
import com.lucilu.rxdynamicsearch.viewmodel.SearchFragmentViewModel;
import com.lucilu.rxdynamicsearch.viewmodel.base.ViewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

import static com.lucilu.rxdynamicsearch.utils.Preconditions.get;

public final class CountryListFragment extends BaseFragment {

    @Nullable
    @Inject
    SearchFragmentViewModel mViewModel;

    @Nullable
    private SearchView mSearchView;

    @Nullable
    private RecyclerView mCountryList;

    @Nullable
    @Inject
    ListAdapter mAdapter;

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
        mCountryList = ViewUtils.find(view, R.id.list_country);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        get(mCountryList).setAdapter(mAdapter);
        get(mCountryList).setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void onBind(@NonNull final CompositeSubscription s) {
        s.add(get(mViewModel).getListItemStream()
                             .subscribeOn(Schedulers.computation())
                             .observeOn(AndroidSchedulers.mainThread())
                             .subscribe(get(mAdapter)::update,
                                        e -> Timber.e(e, "Error updating adapter with model")));
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
        return Observable.never();
    }
}
