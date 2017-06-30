package com.hanthienduc.newestmovie.details;

import com.hanthienduc.newestmovie.models.Movie;
import com.hanthienduc.newestmovie.models.Review;
import com.hanthienduc.newestmovie.models.Video;

import java.util.List;

public interface MovieDetailsView {
    void showDetails(Movie movie);

    void showTrailers(List<Video> trailers);

    void showReviews(List<Review> reviews);

    void showFavorited();

    void showUnFavorited();
}
