package com.hanthienduc.newestmovie.tvshow.sorting;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class TVSortingOptionStore {
    private SharedPreferences pref;

    private static final String SELECTED_OPTION = "selectedTVOption";
    private static final String PREF_NAME = "SortingTVOptionStore";

    @Inject
    public TVSortingOptionStore(Context context) {
        pref = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setSelectedOption(TVSortType sortType) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(SELECTED_OPTION, sortType.getValue());
        editor.apply();
    }

    public int getSelectedOption() {
        return pref.getInt(SELECTED_OPTION, 0);
    }
}
