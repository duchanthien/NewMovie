package com.hanthienduc.newestmovie.listing;

import com.hanthienduc.newestmovie.models.Movie;
import com.hanthienduc.newestmovie.util.RxUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MoviesListingPresenterImpl implements MoviesListingPresenter {

    private MoviesListingView view;
    private MoviesListingInteractor moviesListingInteractor;
    private Subscription fetchSubscription;

    MoviesListingPresenterImpl(MoviesListingInteractor interactor) {
        moviesListingInteractor = interactor;
    }

    @Override
    public void displayMovies() {
        showLoading();
        fetchSubscription = moviesListingInteractor.fetchMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onMovieFetchSuccess, this::onMovieFetchFailed);
    }

    @Override
    public void setView(MoviesListingView view) {
        this.view = view;
        displayMovies();
    }

    @Override
    public void destroy() {
        view = null;
        RxUtils.unsubscribe(fetchSubscription);
    }

    private void showLoading() {
        if (isViewAttached()) {
            view.loadingStarted();
        }
    }

    private void onMovieFetchSuccess(List<Movie> movies) {
        if (isViewAttached()) {
            view.showMovies(movies);
        }
    }

    private void onMovieFetchFailed(Throwable e) {
        view.loadingFailed(e.getMessage());
    }

    private boolean isViewAttached() {
        return view != null;
    }
}
