package com.hanthienduc.newestmovie.listing.sorting;

public enum SortType {

    NOW_PLAYING(0),UPCOMING(1), MOST_POPULAR(2), TOP_RATED(3);

    private final int value;

    SortType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
