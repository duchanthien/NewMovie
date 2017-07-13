package com.hanthienduc.newestmovie.favorites;

public interface FavoritesPresenter {
    void displayMovies();
    void setView(FavoritesView view);

    void destroy();
}
