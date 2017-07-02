package com.hanthienduc.newestmovie.tvshow.sorting;

public class TVSortingDialogInteractorImpl implements TVSortingDialogInteractor {
    TVSortingOptionStore sortingOptionStore;

    TVSortingDialogInteractorImpl(TVSortingOptionStore sortingOptionStore) {
        this.sortingOptionStore = sortingOptionStore;
    }

    @Override
    public int selectedSortingOption() {
        return sortingOptionStore.getSelectedOption();
    }

    @Override
    public void setSortingOption(TVSortType sortType) {
        sortingOptionStore.setSelectedOption(sortType);
    }
}
