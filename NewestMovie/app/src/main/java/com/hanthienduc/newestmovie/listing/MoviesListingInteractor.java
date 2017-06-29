package com.hanthienduc.newestmovie.listing;

import com.hanthienduc.newestmovie.models.Movie;

import java.util.List;

import rx.Observable;

public interface MoviesListingInteractor {
    Observable<List<Movie>> fetchMovies();
}
