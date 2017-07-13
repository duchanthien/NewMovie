package com.hanthienduc.newestmovie.favorites;

public class FavoritesPresenterImpl implements FavoritesPresenter {

    FavoritesView view;
    FavoritesInteractor favoritesInteractor;

    FavoritesPresenterImpl(FavoritesInteractor favoritesInteractor) {
        this.favoritesInteractor = favoritesInteractor;
    }

    @Override
    public void displayMovies() {
        view.showMovies(favoritesInteractor.getFavorites());
    }

    @Override
    public void setView(FavoritesView view) {
        this.view = view;
        displayMovies();
    }

    @Override
    public void destroy() {
        view = null;
    }

}
