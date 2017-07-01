package com.hanthienduc.newestmovie.listing.sorting;

public interface SortingDialogPresenter {
    void setLastSavedOption();

    void onUpcomingMoviesSelected();

    void onPopularMoviesSelected();

    void onNowPlayingMoviesSelected();

    void onTopRatedMoviesSelected();

    void onFavoritesSelected();

    void setView(SortingDialogView view);

    void destroy();
}
