package com.hanthienduc.newestmovie.listing.sorting;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hanthienduc.newestmovie.BaseApplication;
import com.hanthienduc.newestmovie.R;
import com.hanthienduc.newestmovie.listing.MoviesListingPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SortingDialogFragment extends DialogFragment implements SortingDialogView, RadioGroup.OnCheckedChangeListener {

    @Inject
    SortingDialogPresenter sortingDialogPresenter;
    @Bind(R.id.now_playing)
    RadioButton nowPlaying;
    @Bind(R.id.upcoming)
    RadioButton upcoming;
    @Bind(R.id.most_popular)
    RadioButton mostPopular;
    @Bind(R.id.top_rated)
    RadioButton topRated;
    @Bind(R.id.favorites)
    RadioButton favorites;
    @Bind(R.id.sorting_group)
    RadioGroup sortingOptionsGroup;

    private static MoviesListingPresenter moviesListingPresenter;

    public static SortingDialogFragment newInstance(MoviesListingPresenter moviesListingPresenter) {
        SortingDialogFragment.moviesListingPresenter = moviesListingPresenter;
        return new SortingDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseApplication) getActivity().getApplication()).getListingComponent().inject(this);
        sortingDialogPresenter.setView(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.sorting_options, null);
        ButterKnife.bind(this, dialogView);
        initViews();

        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(dialogView);
        dialog.setTitle(R.string.sort_by);
        dialog.show();
        return dialog;
    }

    private void initViews() {
        sortingDialogPresenter.setLastSavedOption();
        sortingOptionsGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void setNowPlayingChecked() {
        nowPlaying.setChecked(true);
    }

    @Override
    public void setUpcomingChecked() {
        upcoming.setChecked(true);
    }

    @Override
    public void setPopularChecked() {
        mostPopular.setChecked(true);
    }

    @Override
    public void setTopRatedChecked() {
        topRated.setChecked(true);
    }

    @Override
    public void setFavoritesChecked() {
        favorites.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.now_playing:
                sortingDialogPresenter.onNowPlayingMoviesSelected();
                moviesListingPresenter.displayMovies();
                break;
            case R.id.upcoming:
                sortingDialogPresenter.onUpcomingMoviesSelected();
                moviesListingPresenter.displayMovies();
                break;
            case R.id.most_popular:
                sortingDialogPresenter.onPopularMoviesSelected();
                moviesListingPresenter.displayMovies();
                break;
            case R.id.top_rated:
                sortingDialogPresenter.onTopRatedMoviesSelected();
                moviesListingPresenter.displayMovies();
                break;
            case R.id.favorites:
                sortingDialogPresenter.onFavoritesSelected();
                moviesListingPresenter.displayMovies();
                break;
        }
    }

    @Override
    public void setDimissDialog() {
        dismiss();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sortingDialogPresenter.destroy();
        ButterKnife.unbind(this);
    }
}
