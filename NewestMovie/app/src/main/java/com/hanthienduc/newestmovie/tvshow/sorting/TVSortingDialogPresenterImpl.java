package com.hanthienduc.newestmovie.tvshow.sorting;

public class TVSortingDialogPresenterImpl implements TVSortingDialogPresenter {

    private TVSortingDialogView view;
    private TVSortingDialogInteractor tvSortingDialogInteractor;

    TVSortingDialogPresenterImpl(TVSortingDialogInteractor tvSortingDialogInteractor) {
        this.tvSortingDialogInteractor = tvSortingDialogInteractor;
    }

    @Override
    public void setView(TVSortingDialogView view) {
        this.view = view;
    }

    @Override
    public void setLastSavedOption() {
        if (isViewAttached()) {
            int selectedTVOption = tvSortingDialogInteractor.selectedSortingOption();
            if (selectedTVOption == TVSortType.AIRING_TODAY.getValue()) {
                view.setAiringTodayChecked();
            } else if (selectedTVOption == TVSortType.ON_THE_AIR.getValue()) {
                view.setOnTheAirChecked();
            } else if (selectedTVOption == TVSortType.POPULAR.getValue()) {
                view.setPopularChecked();
            } else if (selectedTVOption == TVSortType.TOP_RATED.getValue()) {
                view.setTopRatedChecked();
            } else {
                view.setPopularChecked();
            }
        }
    }

    @Override
    public void onAiringTodaySelected() {
        if (isViewAttached()) {
            tvSortingDialogInteractor.setSortingOption(TVSortType.AIRING_TODAY);
            view.setDimissDialog();
        }
    }

    @Override
    public void onOnTheAirSelected() {
        if (isViewAttached()) {
            tvSortingDialogInteractor.setSortingOption(TVSortType.ON_THE_AIR);
            view.setDimissDialog();
        }
    }

    @Override
    public void onPopularSelected() {
        if (isViewAttached()) {
            tvSortingDialogInteractor.setSortingOption(TVSortType.POPULAR);
            view.setDimissDialog();
        }
    }

    @Override
    public void onTopRatedSelected() {
        if (isViewAttached()) {
            tvSortingDialogInteractor.setSortingOption(TVSortType.TOP_RATED);
            view.setDimissDialog();
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void destroy() {
        this.view = null;
    }
}
