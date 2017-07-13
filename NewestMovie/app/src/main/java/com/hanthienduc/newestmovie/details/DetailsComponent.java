package com.hanthienduc.newestmovie.details;

import com.hanthienduc.newestmovie.favorites.FavoritesModule;

import dagger.Subcomponent;

@DetailsScope
@Subcomponent(modules = {DetailsModule.class, FavoritesModule.class})
public interface DetailsComponent {
    void inject(MovieDetailsFragment fragment);
}
