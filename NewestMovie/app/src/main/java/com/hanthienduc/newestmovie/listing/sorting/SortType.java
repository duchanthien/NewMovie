package com.hanthienduc.newestmovie.listing.sorting;

public enum SortType {

    UPCOMING(0), MOST_POPULAR(1), NOW_PLAYING(2), TOP_RATED(3), FAVORITES(4);

    private final int value;

    SortType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
