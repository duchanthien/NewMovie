package com.hanthienduc.newestmovie.tvshow.sorting;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hanthienduc.newestmovie.BaseApplication;
import com.hanthienduc.newestmovie.R;
import com.hanthienduc.newestmovie.tvshow.TVShowListingPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TVSortingDialogFragment extends DialogFragment implements
        TVSortingDialogView, RadioGroup.OnCheckedChangeListener {

    @Inject
    TVSortingDialogPresenter sortingDialogPresenter;

    @Bind(R.id.airing_today)
    RadioButton airingToday;
    @Bind(R.id.on_the_air)
    RadioButton onTheAir;
    @Bind(R.id.most_popular)
    RadioButton popular;
    @Bind(R.id.top_rated)
    RadioButton topRated;
    @Bind(R.id.sorting_group)
    RadioGroup sortingGroup;

    private static TVShowListingPresenter listingPresenter;

    public static TVSortingDialogFragment newInstance(TVShowListingPresenter presenter) {
        TVSortingDialogFragment.listingPresenter = presenter;
        return new TVSortingDialogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseApplication) getActivity().getApplicationContext()).getTvShowComponent().inject(this);
        sortingDialogPresenter.setView(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.tv_sorting_options, null);
        ButterKnife.bind(this, dialogView);
        initViews();

        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(dialogView);
        dialog.setTitle(R.string.sort_by);
        dialog.show();
        return dialog;
    }

    private void initViews() {
        sortingDialogPresenter.setLastSavedOption();
        sortingGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.airing_today:
                sortingDialogPresenter.onAiringTodaySelected();
                listingPresenter.displayTVshows();
                break;
            case R.id.on_the_air:
                sortingDialogPresenter.onOnTheAirSelected();
                listingPresenter.displayTVshows();
                break;
            case R.id.most_popular:
                sortingDialogPresenter.onPopularSelected();
                listingPresenter.displayTVshows();
                break;
            case R.id.top_rated:
                sortingDialogPresenter.onTopRatedSelected();
                listingPresenter.displayTVshows();
                break;
        }
    }

    @Override
    public void setAiringTodayChecked() {
        airingToday.setChecked(true);
    }

    @Override
    public void setOnTheAirChecked() {
        onTheAir.setChecked(true);
    }

    @Override
    public void setPopularChecked() {
        popular.setChecked(true);
    }

    @Override
    public void setTopRatedChecked() {
        topRated.setChecked(true);
    }

    @Override
    public void setDimissDialog() {
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sortingDialogPresenter.destroy();
        ButterKnife.unbind(this);
    }
}
