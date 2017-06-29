package com.hanthienduc.newestmovie;

import android.app.Application;
import android.os.StrictMode;

import com.hanthienduc.newestmovie.listing.ListingComponent;
import com.hanthienduc.newestmovie.listing.ListingModule;
import com.hanthienduc.newestmovie.network.NetworkModule;

public class BaseApplication extends Application {
    private AppComponent appComponent;
    private ListingComponent listingComponent;

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
                .build();
    }

    public ListingComponent createListingComponent() {
        listingComponent = appComponent.plus(new ListingModule());
        return listingComponent;
    }

    public void releaseListingComponent() {
        listingComponent = null;
    }

    public ListingComponent getListingComponent() {
        return listingComponent;
    }

}
