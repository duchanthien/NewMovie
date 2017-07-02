package com.hanthienduc.newestmovie.tvshow;


import com.hanthienduc.newestmovie.models.Movie;
import com.hanthienduc.newestmovie.util.RxUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TVShowListingPresenterImpl implements TVShowListingPresenter {

    TVShowListingView view;
    TVShowListingInteractor tvShowListingInteractor;
    Subscription fetchSubscription;

    TVShowListingPresenterImpl(TVShowListingInteractor tvShowListingInteractor) {
        this.tvShowListingInteractor = tvShowListingInteractor;
    }

    @Override
    public void setView(TVShowListingView view) {
        this.view = view;
        displayTVshows();
    }

    @Override
    public void displayTVshows() {
        showLoading();
        fetchSubscription = tvShowListingInteractor.fetchTVShows()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTVShowFetchSuccess, this::onTVShowFetchFailed);
    }

    private void showLoading() {
        if (isViewAttached()) {
            view.loadingStarted();
        }
    }

    private void onTVShowFetchSuccess(List<Movie> tvShowModels) {
        if (isViewAttached()) {
            view.showTVShows(tvShowModels);
        }
    }

    private void onTVShowFetchFailed(Throwable e) {
        view.loadingFailed(e.getMessage());
    }

    private boolean isViewAttached() {
        return view != null;
    }


    @Override
    public void destroy() {
        view = null;
        RxUtils.unsubscribe(fetchSubscription);
    }
}
