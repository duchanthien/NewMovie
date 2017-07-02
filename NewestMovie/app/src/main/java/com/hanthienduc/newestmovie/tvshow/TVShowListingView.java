package com.hanthienduc.newestmovie.tvshow;

import com.hanthienduc.newestmovie.models.Movie;

import java.util.List;

public interface TVShowListingView {

    void showTVShows(List<Movie> tvShowModels);

    void loadingStarted();

    void loadingFailed(String errorMessage);

    void onTVShowClicked(Movie tvShowModel);
}
