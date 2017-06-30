package com.hanthienduc.newestmovie.favorites;

import com.hanthienduc.newestmovie.AppModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = AppModule.class)
public class FavoritesModule {

    @Provides
    @Singleton
    FavoritesInteractor provideFavoritesInteractor(FavoritesStore store) {
        return new FavoritesInteractorImpl(store);
    }
}
