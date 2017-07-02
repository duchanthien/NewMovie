package com.hanthienduc.newestmovie.tvshow;

import com.hanthienduc.newestmovie.models.Movie;

import java.util.List;

import rx.Observable;

public interface TVShowListingInteractor {
    Observable<List<Movie>> fetchTVShows();
}
