package com.hanthienduc.newestmovie.listing;

import android.support.annotation.NonNull;

import com.hanthienduc.newestmovie.Api;
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

    MoviesListingInteractorImpl(RequestHandler requestHandler,
                                SortingOptionStore store) {
        this.requestHandler = requestHandler;
        this.sortingOptionStore = store;
    }

    @Override
    public Observable<List<Movie>> fetchMovies() {
        return Observable.fromCallable(this::getMoviesList);
    }

    private List<Movie> getMoviesList() throws IOException, JSONException {
        int selectedOption = sortingOptionStore.getSelectedOption();
        if (selectedOption == SortType.MOST_POPULAR.getValue()) {
            return fetMovieList(Api.GET_POPULAR_MOVIES);
        } else if (selectedOption == SortType.HIGHEST_RATED.getValue()) {
            return fetMovieList(Api.GET_HIGHEST_RATED_MOVIES);
        }
        return fetMovieList(Api.GET_POPULAR_MOVIES);

    }

    @NonNull
    private List<Movie> fetMovieList(String url) throws IOException, JSONException {
        Request request = RequestGenerator.get(url);
        String response = requestHandler.request(request);
        return MoviesListingParser.parse(response);
    }
}
