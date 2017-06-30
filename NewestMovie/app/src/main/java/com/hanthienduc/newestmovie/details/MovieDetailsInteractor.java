package com.hanthienduc.newestmovie.details;

import com.hanthienduc.newestmovie.models.Review;
import com.hanthienduc.newestmovie.models.Video;

import java.util.List;

import rx.Observable;

public interface MovieDetailsInteractor {
    Observable<List<Video>> getTrailers(String id);

    Observable<List<Review>> getReviews(String id);
}
