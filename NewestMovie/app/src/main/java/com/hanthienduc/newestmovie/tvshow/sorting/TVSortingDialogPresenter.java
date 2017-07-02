package com.hanthienduc.newestmovie.tvshow.sorting;

public interface TVSortingDialogPresenter {
    void setLastSavedOption();

    void onAiringTodaySelected();

    void onOnTheAirSelected();

    void onPopularSelected();

    void onTopRatedSelected();

    void setView(TVSortingDialogView view);

    void destroy();
}
