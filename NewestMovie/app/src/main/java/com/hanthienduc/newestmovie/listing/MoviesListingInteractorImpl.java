package com.hanthienduc.newestmovie.listing;

import android.support.annotation.NonNull;

import com.hanthienduc.newestmovie.Api;
import com.hanthienduc.newestmovie.favorites.FavoritesInteractor;
import com.hanthienduc.newestmovie.listing.sorting.SortType;
import com.hanthienduc.newestmovie.listing.sorting.SortingOptionStore;
import com.hanthienduc.newestmovie.models.Movie;
import com.hanthienduc.newestmovie.network.RequestGenerator;
import com.hanthienduc.newestmovie.network.RequestHandler;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;
import rx.Observable;

public class MoviesListingInteractorImpl implements MoviesListingInteractor {

    private RequestHandler requestHandler;
    private SortingOptionStore sortingOptionStore;
    private FavoritesInteractor favoritesInteractor;

    MoviesListingInteractorImpl(RequestHandler requestHandler,
                                SortingOptionStore store, FavoritesInteractor favoritesInteractor) {
        this.requestHandler = requestHandler;
        this.sortingOptionStore = store;
        this.favoritesInteractor = favoritesInteractor;
    }

    @Override
    public Observable<List<Movie>> fetchMovies() {
        return Observable.fromCallable(this::getMoviesList);
    }

    private List<Movie> getMoviesList() throws IOException, JSONException {
        int selectedOption = sortingOptionStore.getSelectedOption();
        if (selectedOption == SortType.UPCOMING.getValue()) {
            return fetMovieList(Api.GET_UPCOMING_MOVIES);
        } else if (selectedOption == SortType.MOST_POPULAR.getValue()) {
            return fetMovieList(Api.GET_POPULAR_MOVIES);
        } else if (selectedOption == SortType.NOW_PLAYING.getValue()) {
            return fetMovieList(Api.GET_NOW_PLAYING);
        } else if (selectedOption == SortType.TOP_RATED.getValue()) {
            return fetMovieList(Api.GET_TOP_RATED_MOVIES);
        }
        return favoritesInteractor.getFavorites();

    }

    @NonNull
    private List<Movie> fetMovieList(String url) throws IOException, JSONException {
        Request request = RequestGenerator.get(url);
        String response = requestHandler.request(request);
        return MoviesListingParser.parse(response);
    }
}
