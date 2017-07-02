package com.hanthienduc.newestmovie;

public class Api {
    private static final String API_KEY = BuildConfig.TMDB_API_KEY;

    public static final String GET_UPCOMING_MOVIES =
            "https://api.themoviedb.org/3/movie/upcoming?api_key=" + API_KEY + "&language=en-US";

    public static final String GET_POPULAR_MOVIES =
            "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY + "&language=en-US";

    public static final String GET_TOP_RATED_MOVIES =
            "https://api.themoviedb.org/3/movie/top_rated?api_key=" + API_KEY + "&language=en-US";

    public static final String GET_NOW_PLAYING =
            "https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY + "&language=en-US";

    public static final String GET_POPULAR_TV =
            "https://api.themoviedb.org/3/tv/popular?api_key="+API_KEY+"&language=en-US";

    public static final String GET_TOP_RATED_TV =
            "https://api.themoviedb.org/3/tv/top_rated?api_key="+API_KEY+"&language=en-US";

    public static final String GET_TV_ON_THE_AIR =
            "https://api.themoviedb.org/3/tv/on_the_air?api_key="+API_KEY+"&language=en-US";

    public static final String GET_TV_AIRING_TODAY =
            "https://api.themoviedb.org/3/tv/airing_today?api_key="+API_KEY+"&language=en-US";

    public static final String GET_TRAILERS =
            "http://api.themoviedb.org/3/movie/%s/videos?api_key=" + API_KEY;

    public static final String GET_REVIEWS =
            "http://api.themoviedb.org/3/movie/%s/reviews?api_key=" + API_KEY;

    public static final String POSTER_PATH = "http://image.tmdb.org/t/p/w342";

    public static final String BACKDROP_PATH = "http://image.tmdb.org/t/p/w780";

    public static final String YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%1$s";

    public static final String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%1$s/0.jpg";



    private Api() {
        // hide implicit public constructor
    }
}
