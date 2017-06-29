package com.hanthienduc.newestmovie.listing.sorting;

public enum SortType {

    MOST_POPULAR(0), HIGHEST_RATED(1), FAVORITES(3);

    private final int value;

    SortType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
