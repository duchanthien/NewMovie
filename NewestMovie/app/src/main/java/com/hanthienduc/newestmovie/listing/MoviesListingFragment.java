package com.hanthienduc.newestmovie.listing;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hanthienduc.newestmovie.BaseApplication;
import com.hanthienduc.newestmovie.R;
import com.hanthienduc.newestmovie.listing.sorting.SortingDialogFragment;
import com.hanthienduc.newestmovie.models.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoviesListingFragment extends Fragment implements MoviesListingView {

    @Inject
    MoviesListingPresenter moviesPresenter;

    @Bind(R.id.movies_listing)
    RecyclerView moviesListing;

    private RecyclerView.Adapter adapter;
    private List<Movie> movies = new ArrayList<>();
    private Callback callback;

    BaseApplication baseApplication;

    public MoviesListingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseApplication = (BaseApplication) getActivity().getApplication();
        setHasOptionsMenu(true);
        setRetainInstance(true);
        baseApplication.createListingComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
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
            case R.id.action_settings:
                Snackbar.make(moviesListing, "Action Settings is clicked", Snackbar.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displaySortingOptions() {
        DialogFragment sortingDialogFragment = SortingDialogFragment.newInstance(moviesPresenter);
        sortingDialogFragment.show(getFragmentManager(), "Select Quantity");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesPresenter.setView(this);
    }


    private void initLayoutReferences() {
        moviesListing.setHasFixedSize(true);

        int columns;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 2;
        } else {
            columns = getResources().getInteger(R.integer.no_of_columns);
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), columns);

        moviesListing.setLayoutManager(layoutManager);
        adapter = new MoviesListingAdapter(movies, this);
        moviesListing.setAdapter(adapter);
    }


    @Override
    public void showMovies(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        moviesListing.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        callback.onMoviesLoaded(movies.get(0));
    }

    @Override
    public void loadingStarted() {
        Snackbar.make(moviesListing, getString(R.string.loading_movies), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadingFailed(String errorMessage) {
        Snackbar.make(moviesListing, errorMessage, Snackbar.LENGTH_INDEFINITE).show();
        Log.e(MoviesListingFragment.class.getSimpleName(), errorMessage);
    }

    @Override
    public void onMovieClicked(Movie movie) {
        callback.onMovieClicked(movie);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        moviesPresenter.destroy();
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
        baseApplication.releaseListingComponent();
    }

    public interface Callback {
        void onMoviesLoaded(Movie movie);

        void onMovieClicked(Movie movie);
    }
}
