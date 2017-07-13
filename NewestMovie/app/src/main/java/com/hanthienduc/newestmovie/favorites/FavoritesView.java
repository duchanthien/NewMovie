package com.hanthienduc.newestmovie.favorites;

import com.hanthienduc.newestmovie.models.Movie;

import java.util.List;

public interface FavoritesView {
    void showMovies(List<Movie> movies);

    void onMovieClicked(Movie movie);
}
