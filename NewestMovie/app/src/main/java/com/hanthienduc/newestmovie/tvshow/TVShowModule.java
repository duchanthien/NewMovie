package com.hanthienduc.newestmovie.tvshow;

import com.hanthienduc.newestmovie.network.RequestHandler;
import com.hanthienduc.newestmovie.tvshow.sorting.TVSortingOptionStore;

import dagger.Module;
import dagger.Provides;

@Module
public class TVShowModule {

    @TVShowScope
    @Provides
    TVShowListingInteractor provideTVShowListingInteractor(RequestHandler requestHandler, TVSortingOptionStore store) {
        return new TVShowListingInteractorImpl(requestHandler, store);
    }

    @TVShowScope
    @Provides
    TVShowListingPresenter provideTVShowListingPresenter(TVShowListingInteractor tvShowListingInteractor) {
        return new TVShowListingPresenterImpl(tvShowListingInteractor);
    }
}
