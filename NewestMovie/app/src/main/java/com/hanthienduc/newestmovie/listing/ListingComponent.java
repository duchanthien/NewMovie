package com.hanthienduc.newestmovie.listing;

import dagger.Subcomponent;

@ListingScope
@Subcomponent(modules = {ListingModule.class})
public interface ListingComponent {

    MoviesListingFragment inject(MoviesListingFragment fragment);
}
