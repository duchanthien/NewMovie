package com.hanthienduc.newestmovie.listing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.hanthienduc.newestmovie.R;
import com.hanthienduc.newestmovie.models.Movie;

public class MoviesListingActivity extends AppCompatActivity implements MoviesListingFragment.Callback {

    public static final String DETAILS_FRAGMENT = "DetailsFragment";
    private boolean twoPaneMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

       /* if (findViewById(R.id.movie_details_container) != null) {
            twoPaneMode = true;
           *//* if(savedInstanceState == null){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_details_container,new Mov)
            }*//*
        } else {
            twoPaneMode = false;
        }*/
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.movie_new));
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onMoviesLoaded(Movie movie) {
        /*if (twoPaneMode) {
            loadMovieFragment(movie);
        } else {

        }*/
    }

    @Override
    public void onMovieClicked(Movie movie) {
       /* if (twoPaneMode) {
            loadMovieFragment(movie);
        } else {
            startMovieActivity(movie);
        }*/
    }

    private void loadMovieFragment(Movie movie) {
        /*MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.getInstance(movie);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.movie_details_container, movieDetailsFragment, DETAILS_FRAGMENT)
                .commit();*/
    }

    private void startMovieActivity(Movie movie) {
        /*Intent intent = new Intent(this, MovieDetailActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.MOVIE, movie);
        intent.putExtras(extras);
        startActivity(intent);*/
    }


}