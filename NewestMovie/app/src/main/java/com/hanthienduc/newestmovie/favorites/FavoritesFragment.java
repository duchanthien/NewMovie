package com.hanthienduc.newestmovie.favorites;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanthienduc.newestmovie.BaseApplication;
import com.hanthienduc.newestmovie.R;
import com.hanthienduc.newestmovie.models.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FavoritesFragment extends Fragment implements FavoritesView {


    @Inject
    FavoritesPresenter presenter;

    @Bind(R.id.favorites_listing)
    RecyclerView favoritesListing;

    private RecyclerView.Adapter adapter;

    private List<Movie> movies = new ArrayList<>();

    private Callback callback;

    public FavoritesFragment() {

    }

    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback) getContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        setRetainInstance(true);
        ((BaseApplication) getActivity().getApplicationContext()).createFavoritesComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, rootView);
        initLayoutResources();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
    }

    private void initLayoutResources() {
        favoritesListing.setHasFixedSize(true);

        int columns;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 2;
        } else {
            columns = getResources().getInteger(R.integer.no_of_columns);
        }
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), columns);

        favoritesListing.setLayoutManager(layoutManager);
        adapter = new FavoritesListingAdapter(movies, this);
        favoritesListing.setAdapter(adapter);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        favoritesListing.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onMovieClicked(Movie movie) {
        callback.onFavoriteMovieClicked(movie);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.destroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication()).releaseFavoritesComponent();
    }

    public interface Callback {

        void onFavoriteMovieClicked(Movie movie);
    }
}
