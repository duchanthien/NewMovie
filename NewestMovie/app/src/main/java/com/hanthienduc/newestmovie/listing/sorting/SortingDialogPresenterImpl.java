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
            if (selectedOption == SortType.MOST_POPULAR.getValue()) {
                view.setPopularChecked();
            } else if (selectedOption == SortType.HIGHEST_RATED.getValue()) {
                view.setHighestRatedChecked();
            } else {
                view.setFavoritesChecked();
            }
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
    public void onHighestRatedMoviesSelected() {
        if (isViewAttached()) {
            sortingDialogInteractor.setSortingOption(SortType.HIGHEST_RATED);
            view.setDimissDialog();
        }
    }

    @Override
    public void onFavoritesSelected() {
        if (isViewAttached()) {
            sortingDialogInteractor.setSortingOption(SortType.FAVORITES);
            view.setDimissDialog();
        }
    }

    @Override
    public void destroy() {
        this.view = null;
    }
}
