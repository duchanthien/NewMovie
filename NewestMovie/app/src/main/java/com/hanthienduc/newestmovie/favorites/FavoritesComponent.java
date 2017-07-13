package com.hanthienduc.newestmovie.favorites;

import dagger.Subcomponent;

@FavoritesScope
@Subcomponent(modules = FavoritesModule.class)
public interface FavoritesComponent {
    FavoritesFragment inject(FavoritesFragment fragment);
}
