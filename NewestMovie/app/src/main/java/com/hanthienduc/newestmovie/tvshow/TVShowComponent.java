package com.hanthienduc.newestmovie.tvshow;

import com.hanthienduc.newestmovie.tvshow.sorting.TVSortingDialogFragment;
import com.hanthienduc.newestmovie.tvshow.sorting.TVSortingModule;

import dagger.Subcomponent;

@TVShowScope
@Subcomponent(modules = {TVShowModule.class, TVSortingModule.class})
public interface TVShowComponent {
    TVShowListingFragment inject(TVShowListingFragment fragment);

    TVSortingDialogFragment inject(TVSortingDialogFragment fragment);
}
