package com.hanthienduc.newestmovie.listing;

import com.hanthienduc.newestmovie.listing.sorting.SortingDialogFragment;
import com.hanthienduc.newestmovie.listing.sorting.SortingModule;

import dagger.Subcomponent;

@ListingScope
@Subcomponent(modules = {ListingModule.class, SortingModule.class})
public interface ListingComponent {

    MoviesListingFragment inject(MoviesListingFragment fragment);

    SortingDialogFragment inject(SortingDialogFragment fragment);
}
