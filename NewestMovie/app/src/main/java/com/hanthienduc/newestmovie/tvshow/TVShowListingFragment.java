package com.hanthienduc.newestmovie.tvshow;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hanthienduc.newestmovie.BaseApplication;
import com.hanthienduc.newestmovie.R;
import com.hanthienduc.newestmovie.models.Movie;
import com.hanthienduc.newestmovie.tvshow.sorting.TVSortingDialogFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TVShowListingFragment extends Fragment implements TVShowListingView {

    @Inject
    TVShowListingPresenter tvShowPresenter;

    @Bind(R.id.tvshow_listing)
    RecyclerView tvShowsListing;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private RecyclerView.Adapter adapter;
    private List<Movie> tvShowModels = new ArrayList<>();
    private Callback callback;

    BaseApplication baseApplication;

    public TVShowListingFragment() {

    }

    public static TVShowListingFragment newInstance() {
        TVShowListingFragment TVShowListingFragment = new TVShowListingFragment();
        return TVShowListingFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseApplication = (BaseApplication) getActivity().getApplicationContext();
        setHasOptionsMenu(true);
        setRetainInstance(true);
        baseApplication.createTVShowComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tvshow, container, false);
        ButterKnife.bind(this, rootView);
        initLayoutReferences();
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                displaySortingOptions();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displaySortingOptions() {
        TVSortingDialogFragment dialogFragment = TVSortingDialogFragment.newInstance(tvShowPresenter);
        dialogFragment.show(getFragmentManager(),"Select Quantity");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvShowPresenter.setView(this);
    }

    private void initLayoutReferences() {
        tvShowsListing.setHasFixedSize(true);
        int columns;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 2;
        } else {
            columns = getResources().getInteger(R.integer.no_of_columns);
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), columns);
        tvShowsListing.setLayoutManager(layoutManager);
        adapter = new TVShowListingAdapter(tvShowModels, this);
        tvShowsListing.setAdapter(adapter);
    }

    @Override
    public void showTVShows(List<Movie> tvShowModels) {
        Log.d("Show views", tvShowModels.toString());
        this.tvShowModels.clear();
        this.tvShowModels.addAll(tvShowModels);
        tvShowsListing.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        callback.onTVShowLoaded(this.tvShowModels.get(0));
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loadingStarted() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadingFailed(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTVShowClicked(Movie tvShowModel) {
        callback.onTVShowClicked(tvShowModel);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tvShowPresenter.destroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDetach() {
        callback = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        baseApplication.releaseTVShowComponent();
    }

    public interface Callback {
        void onTVShowLoaded(Movie movie);

        void onTVShowClicked(Movie movie);
    }
}
