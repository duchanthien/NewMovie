package com.hanthienduc.newestmovie;


import com.hanthienduc.newestmovie.details.DetailsComponent;
import com.hanthienduc.newestmovie.details.DetailsModule;
import com.hanthienduc.newestmovie.favorites.FavoritesComponent;
import com.hanthienduc.newestmovie.favorites.FavoritesModule;
import com.hanthienduc.newestmovie.listing.ListingComponent;
import com.hanthienduc.newestmovie.listing.ListingModule;
import com.hanthienduc.newestmovie.network.NetworkModule;
import com.hanthienduc.newestmovie.tvshow.TVShowComponent;
import com.hanthienduc.newestmovie.tvshow.TVShowModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class
})
public interface AppComponent {

    ListingComponent plus(ListingModule listingModule);

    TVShowComponent plus(TVShowModule tvShowModule);

    FavoritesComponent plus(FavoritesModule favoritesModule);

    DetailsComponent plus(DetailsModule detailsModule);
}
