package com.hanthienduc.newestmovie.listing;

import com.hanthienduc.newestmovie.network.RequestHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class ListingModule {

    @Provides
    MoviesListingInteractor provideMovieListingInteractor(RequestHandler requestHandler) {
        return new MoviesListingInteractorImpl(requestHandler);
    }

    @Provides
    MoviesListingPresenter provideMovieListingPresenter(MoviesListingInteractor interactor) {
        return new MoviesListingPresenterImpl(interactor);
    }
}
