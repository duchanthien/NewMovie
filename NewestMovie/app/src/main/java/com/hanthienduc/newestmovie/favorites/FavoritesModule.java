package com.hanthienduc.newestmovie.favorites;

import dagger.Module;
import dagger.Provides;

@Module
public class FavoritesModule {

    @Provides
    FavoritesInteractor provideFavoritesInteractor(FavoritesStore store) {
        return new FavoritesInteractorImpl(store);
    }

    @Provides
    FavoritesPresenter provideFavoritesPresenter(FavoritesInteractor favoritesInteractor) {
        return new FavoritesPresenterImpl(favoritesInteractor);
    }

}
