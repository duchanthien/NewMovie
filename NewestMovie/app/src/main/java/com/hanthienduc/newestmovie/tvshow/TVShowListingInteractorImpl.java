package com.hanthienduc.newestmovie.tvshow;

import com.hanthienduc.newestmovie.Api;
import com.hanthienduc.newestmovie.models.Movie;
import com.hanthienduc.newestmovie.network.RequestGenerator;
import com.hanthienduc.newestmovie.network.RequestHandler;
import com.hanthienduc.newestmovie.tvshow.sorting.TVSortType;
import com.hanthienduc.newestmovie.tvshow.sorting.TVSortingOptionStore;
import com.hanthienduc.newestmovie.util.ListingParser;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;
import rx.Observable;

public class TVShowListingInteractorImpl implements TVShowListingInteractor {

    private RequestHandler requestHandler;
    private TVSortingOptionStore sortingOptionStore;

    TVShowListingInteractorImpl(RequestHandler requestHandler, TVSortingOptionStore store) {
        this.requestHandler = requestHandler;
        this.sortingOptionStore = store;
    }

    @Override
    public Observable<List<Movie>> fetchTVShows() {
        return Observable.fromCallable(this::getTVShowList);
    }

    private List<Movie> getTVShowList() throws IOException, JSONException {
        int selectedOption = sortingOptionStore.getSelectedOption();
        if (selectedOption == TVSortType.AIRING_TODAY.getValue()) {
            return fetTVShowsList(Api.GET_TV_AIRING_TODAY);
        } else if (selectedOption == TVSortType.ON_THE_AIR.getValue()) {
            return fetTVShowsList(Api.GET_TV_ON_THE_AIR);
        } else if (selectedOption == TVSortType.POPULAR.getValue()) {
            return fetTVShowsList(Api.GET_POPULAR_TV);
        } else if (selectedOption == TVSortType.TOP_RATED.getValue()) {
            return fetTVShowsList(Api.GET_TOP_RATED_TV);
        }
        return fetTVShowsList(Api.GET_POPULAR_TV);
    }

    private List<Movie> fetTVShowsList(String url) throws IOException, JSONException {
        Request request = RequestGenerator.get(url);
        String response = requestHandler.request(request);
        return ListingParser.parseMovie(response);
    }
}
