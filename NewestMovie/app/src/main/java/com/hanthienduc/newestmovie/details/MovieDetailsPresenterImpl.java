package com.hanthienduc.newestmovie.details;

import com.hanthienduc.newestmovie.models.Movie;
import com.hanthienduc.newestmovie.models.Review;
import com.hanthienduc.newestmovie.models.Video;
import com.hanthienduc.newestmovie.util.RxUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieDetailsPresenterImpl implements MovieDetailsPresenter {

    private MovieDetailsView view;
    private MovieDetailsInteractor movieDetailsInteractor;
    private Subscription trailersSubscription;
    private Subscription reviewSubscription;

    MovieDetailsPresenterImpl(MovieDetailsInteractor movieDetailsInteractor) {
        this.movieDetailsInteractor = movieDetailsInteractor;
    }

    @Override
    public void setView(MovieDetailsView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        view = null;
        RxUtils.unsubscribe(trailersSubscription, reviewSubscription);
    }

    @Override
    public void showDetails(Movie movie) {
        if (isViewAttached()) {
            view.showDetails(movie);
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void showTrailers(Movie movie) {
        trailersSubscription = movieDetailsInteractor.getTrailers(movie.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetTrailersSuccess, t -> onGetTrailersFailure());
    }

    private void onGetTrailersSuccess(List<Video> videos) {
        if (isViewAttached()) {
            view.showTrailers(videos);
        }
    }

    private void onGetTrailersFailure() {
        // Do nothing
    }

    @Override
    public void showReviews(Movie movie) {
        reviewSubscription = movieDetailsInteractor.getReviews(movie.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetReviewsSuccess, t -> onGetReviewsFailure());
    }

    private void onGetReviewsSuccess(List<Review> reviews) {
        if (isViewAttached()) {
            view.showReviews(reviews);
        }
    }

    private void onGetReviewsFailure() {
        // Do nothing
    }

    @Override
    public void showFavoriteButton(Movie movie) {
       /* boolean isFavorite = favoritesInteractor.isFavorite(movie.getId());
        if(isViewAttached()){
            if(isFavorite){
                view.showFavorited();
            }else{
                view.showUnFavorited();
            }
        }*/
    }

    @Override
    public void onFavoriteClick(Movie movie) {
        /*if(isViewAttached()){
            boolean isFavorite = favoritesInteractor.isFavorite(movie.getId());
            if(isFavorite){
                favoritesInteractor.unFavorite(movie.getId());
                view.showUnFavorited();
            }else {
                favoritesInteractor.setFavorite(movie);
                view.showFavorited();
            }
        }*/
    }

}
