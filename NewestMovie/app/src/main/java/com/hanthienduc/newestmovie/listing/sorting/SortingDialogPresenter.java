package com.hanthienduc.newestmovie.listing.sorting;

public interface SortingDialogPresenter {
    void setLastSavedOption();

    void onPopularMoviesSelected();

    void onHighestRatedMoviesSelected();

    void onFavoritesSelected();

    void setView(SortingDialogView view);

    void destroy();
}
