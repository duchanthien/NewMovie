package com.hanthienduc.newestmovie.util;

import android.support.annotation.NonNull;

import com.hanthienduc.newestmovie.Api;
import com.hanthienduc.newestmovie.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListingParser {

    public static final String RESULTS = "results";
    public static final String OVERVIEW = "overview";
    public static final String RELEASE_DATE = "release_date";
    public static final String FIRST_AIR_DATE = "first_air_date";
    public static final String POSTER_PATH = "poster_path";
    public static final String TITLE = "title";
    public static final String NAME = "name";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String BACKDROP_PATH = "backdrop_path";
    public static final String ID = "id";

    @NonNull
    public static List<Movie> parseMovie(String json) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        JSONObject response = new JSONObject(json);

        if (!response.isNull(RESULTS)) {
            JSONArray results = response.getJSONArray(RESULTS);

            for (int i = 0; i < results.length(); i++) {
                movies.add(getMovie(results.getJSONObject(i)));
            }
        } else {
            // no results
        }

        return movies;
    }

    private static Movie getMovie(JSONObject result) throws JSONException {
        Movie movie = new Movie();

        if (!result.isNull(ID)) {
            movie.setId(result.getString(ID));
        }
        if (!result.isNull(OVERVIEW)) {
            movie.setOverview(result.getString(OVERVIEW));
        }
        if (!result.isNull(RELEASE_DATE)) {
            movie.setReleaseDate(String.valueOf(result.getString(RELEASE_DATE)));
        } else if (!result.isNull(FIRST_AIR_DATE)) {
            movie.setReleaseDate(String.valueOf(result.getString(FIRST_AIR_DATE)));
        }
        if (!result.isNull(POSTER_PATH)) {
            movie.setPosterPath(Api.POSTER_PATH + result.getString(POSTER_PATH));
        }
        if (!result.isNull(BACKDROP_PATH)) {
            movie.setBackdropPath(Api.BACKDROP_PATH + result.getString(BACKDROP_PATH));
        }
        if (!result.isNull(TITLE)) {
            movie.setTitle(result.getString(TITLE));
        } else if (!result.isNull(NAME)) {
            movie.setTitle(result.getString(NAME));
        }
        if (!result.isNull(VOTE_AVERAGE)) {
            movie.setVoteAverage(result.getDouble(VOTE_AVERAGE));
        }

        return movie;
    }
}
