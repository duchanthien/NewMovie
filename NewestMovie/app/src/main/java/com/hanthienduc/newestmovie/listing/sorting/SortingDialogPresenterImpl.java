package com.hanthienduc.newestmovie.listing.sorting;

class SortingDialogPresenterImpl implements SortingDialogPresenter {

    private SortingDialogView view;
    private SortingDialogInteractor sortingDialogInteractor;

    SortingDialogPresenterImpl(SortingDialogInteractor interactor) {
        sortingDialogInteractor = interactor;
    }

    @Override
    public void setView(SortingDialogView view) {
        this.view = view;
    }

    @Override
    public void setLastSavedOption() {
        if (isViewAttached()) {
            int selectedOption = sortingDialogInteractor.getSelectedSortingOption();
            if (selectedOption == SortType.NOW_PLAYING.getValue()) {
                view.setNowPlayingChecked();
            } else if (selectedOption == SortType.UPCOMING.getValue()) {
                view.setUpcomingChecked();
            } else if (selectedOption == SortType.MOST_POPULAR.getValue()) {
                view.setPopularChecked();
            } else if (selectedOption == SortType.TOP_RATED.getValue()) {
                view.setTopRatedChecked();
            } else {
                view.setPopularChecked();
            }
        }
    }

    @Override
    public void onNowPlayingMoviesSelected() {
        if (isViewAttached()) {
            sortingDialogInteractor.setSortingOption(SortType.NOW_PLAYING);
            view.setDimissDialog();
        }
    }

    @Override
    public void onUpcomingMoviesSelected() {
        if (isViewAttached()) {
            sortingDialogInteractor.setSortingOption(SortType.UPCOMING);
            view.setDimissDialog();
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void onPopularMoviesSelected() {
        if (isViewAttached()) {
            sortingDialogInteractor.setSortingOption(SortType.MOST_POPULAR);
            view.setDimissDialog();
        }
    }

    @Override
    public void onTopRatedMoviesSelected() {
        if (isViewAttached()) {
            sortingDialogInteractor.setSortingOption(SortType.TOP_RATED);
            view.setDimissDialog();
        }
    }

    @Override
    public void destroy() {
        this.view = null;
    }
}
