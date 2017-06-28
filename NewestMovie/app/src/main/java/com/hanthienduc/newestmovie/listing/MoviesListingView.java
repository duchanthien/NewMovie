package com.hanthienduc.newestmovie.listing;

import com.hanthienduc.newestmovie.models.Movie;

import java.util.List;

public interface MoviesListingView {

    void showMovies(List<Movie> movies);

    void loadingStarted();

    void loadingFailed(String errorMessage);

    void onMovieClicked(Movie movie);
}
