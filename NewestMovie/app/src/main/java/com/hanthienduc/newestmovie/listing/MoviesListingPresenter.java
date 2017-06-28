package com.hanthienduc.newestmovie.listing;

public interface MoviesListingPresenter
{
    void displayMovies();

    void setView(MoviesListingView view);

    void destroy();
}
