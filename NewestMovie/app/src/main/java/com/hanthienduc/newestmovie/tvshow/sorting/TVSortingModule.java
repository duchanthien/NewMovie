package com.hanthienduc.newestmovie.tvshow.sorting;

import dagger.Module;
import dagger.Provides;

@Module
public class TVSortingModule {

    @Provides
    TVSortingDialogInteractor provideTVSortingDialogInteractor(TVSortingOptionStore store) {
        return new TVSortingDialogInteractorImpl(store);
    }

    @Provides
    TVSortingDialogPresenter provideTVSortingDialogPresenter(TVSortingDialogInteractor interactor) {
        return new TVSortingDialogPresenterImpl(interactor);
    }
}
