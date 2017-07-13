package com.hanthienduc.newestmovie;

import android.app.Application;
import android.os.StrictMode;

import com.hanthienduc.newestmovie.details.DetailsComponent;
import com.hanthienduc.newestmovie.details.DetailsModule;
import com.hanthienduc.newestmovie.favorites.FavoritesComponent;
import com.hanthienduc.newestmovie.favorites.FavoritesModule;
import com.hanthienduc.newestmovie.listing.ListingComponent;
import com.hanthienduc.newestmovie.listing.ListingModule;
import com.hanthienduc.newestmovie.network.NetworkModule;
import com.hanthienduc.newestmovie.tvshow.TVShowComponent;
import com.hanthienduc.newestmovie.tvshow.TVShowModule;

public class BaseApplication extends Application {
    private AppComponent appComponent;
    private ListingComponent listingComponent;
    private DetailsComponent detailsComponent;
    private FavoritesComponent favoritesComponent;
    private TVShowComponent tvShowComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
        appComponent = createAppComponent();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
               // .favoritesModule(new FavoritesModule())
                .build();
    }

    public ListingComponent createListingComponent() {
        listingComponent = appComponent.plus(new ListingModule());
        return listingComponent;
    }

    public void releaseListingComponent() {
        listingComponent = null;
    }

    public FavoritesComponent createFavoritesComponent() {
        favoritesComponent = appComponent.plus(new FavoritesModule());
        return favoritesComponent;
    }

    public void releaseFavoritesComponent() {
        favoritesComponent = null;
    }

    public TVShowComponent createTVShowComponent() {
        tvShowComponent = appComponent.plus(new TVShowModule());
        return tvShowComponent;
    }

    public void releaseTVShowComponent() {
        tvShowComponent = null;
    }

    public ListingComponent getListingComponent() {
        return listingComponent;
    }

    public TVShowComponent getTvShowComponent() {
        return tvShowComponent;
    }

    /* public FavoritesComponent getFavoritesComponent() {
         return favoritesComponent;
     }
 */
    public DetailsComponent createDetailsComponent() {
        detailsComponent = appComponent.plus(new DetailsModule());
        return detailsComponent;
    }

    public void releaseDetailsComponent() {
        detailsComponent = null;
    }
}
