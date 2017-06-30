package com.hanthienduc.newestmovie.favorites;

import com.hanthienduc.newestmovie.models.Movie;

import java.util.List;

public interface FavoritesInteractor {
    void setFavorite(Movie movie);

    boolean isFavorite(String id);

    List<Movie> getFavorites();

    void unFavorite(String id);
}
