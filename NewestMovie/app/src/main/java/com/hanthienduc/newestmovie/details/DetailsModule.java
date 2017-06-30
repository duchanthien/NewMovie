package com.hanthienduc.newestmovie.details;

import com.hanthienduc.newestmovie.network.RequestHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailsModule {

    @Provides
    @DetailsScope
    MovieDetailsInteractor provideInteractor(RequestHandler requestHandler) {
        return new MovieDetailsInteractorImpl(requestHandler);
    }

    @Provides
    @DetailsScope
    MovieDetailsPresenter providePresenter(MovieDetailsInteractor detailsInteractor) {
        return new MovieDetailsPresenterImpl(detailsInteractor);
    }
}
