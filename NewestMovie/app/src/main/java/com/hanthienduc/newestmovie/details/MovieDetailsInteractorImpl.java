package com.hanthienduc.newestmovie.details;

import com.hanthienduc.newestmovie.Api;
import com.hanthienduc.newestmovie.models.Review;
import com.hanthienduc.newestmovie.models.Video;
import com.hanthienduc.newestmovie.network.RequestGenerator;
import com.hanthienduc.newestmovie.network.RequestHandler;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;
import rx.Observable;

public class MovieDetailsInteractorImpl implements MovieDetailsInteractor {

    private RequestHandler requestHandler;

    MovieDetailsInteractorImpl(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public Observable<List<Video>> getTrailers(String id) {

        return Observable.fromCallable(() -> getVideoList(id));
    }

    private List<Video> getVideoList(String id) throws IOException, JSONException {
        String url = String.format(Api.GET_TRAILERS, id);
        Request request = RequestGenerator.get(url);
        String body = requestHandler.request(request);
        return MovieDetailsParser.parseTrailers(body);
    }

    @Override
    public Observable<List<Review>> getReviews(String id) {
        return Observable.fromCallable(() -> getReviewList(id));
    }

    private List<Review> getReviewList(String id) throws IOException, JSONException {
        String url = String.format(Api.GET_REVIEWS, id);
        Request request = RequestGenerator.get(url);
        String body = requestHandler.request(request);
        return MovieDetailsParser.parseReviews(body);
    }
}
