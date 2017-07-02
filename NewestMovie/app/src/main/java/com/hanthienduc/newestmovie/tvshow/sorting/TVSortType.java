package com.hanthienduc.newestmovie.tvshow.sorting;


public enum TVSortType {

    AIRING_TODAY(0), ON_THE_AIR(1), POPULAR(2), TOP_RATED(3);

    private final int value;

    TVSortType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
