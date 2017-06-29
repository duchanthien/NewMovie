package com.hanthienduc.newestmovie.listing;

import android.support.annotation.NonNull;

import com.hanthienduc.newestmovie.Api;
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


    MoviesListingInteractorImpl(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public Observable<List<Movie>> fetchMovies() {
        return Observable.fromCallable(this::getMoviesList);
    }

    private List<Movie> getMoviesList() throws IOException, JSONException {
        return fetMovieList(Api.GET_POPULAR_MOVIES);
    }

    @NonNull
    private List<Movie> fetMovieList(String url) throws IOException, JSONException {
        Request request = RequestGenerator.get(url);
        String response = requestHandler.request(request);
        return MoviesListingParser.parse(response);
    }
}
