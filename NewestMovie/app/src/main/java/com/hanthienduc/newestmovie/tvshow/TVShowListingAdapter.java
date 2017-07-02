package com.hanthienduc.newestmovie.tvshow;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.hanthienduc.newestmovie.R;
import com.hanthienduc.newestmovie.models.Movie;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TVShowListingAdapter extends RecyclerView.Adapter<TVShowListingAdapter.ViewHolder> {

    private List<Movie> tvShowModels;
    private Context context;
    private TVShowListingView view;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.movie_poster)
        ImageView poster;
        @Bind(R.id.title_background)
        View titleBackground;
        @Bind(R.id.movie_name)
        TextView name;

        public Movie tvShowModel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            TVShowListingAdapter.this.view.onTVShowClicked(tvShowModel);
        }
    }

    TVShowListingAdapter(List<Movie> tvShowModels, TVShowListingView view) {
        this.tvShowModels = tvShowModels;
        this.view = view;
    }

    @Override
    public TVShowListingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.movie_grid_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(TVShowListingAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.tvShowModel = tvShowModels.get(position);
        holder.name.setText(holder.tvShowModel.getTitle());
        Glide.with(context).load(holder.tvShowModel
                .getPosterPath()).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new BitmapImageViewTarget(holder.poster) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                holder.titleBackground.setBackgroundColor(palette.getVibrantColor(context
                                        .getResources().getColor(R.color.blue_translucent_60)));
                            }
                        });
                    }
                });
    }

    @Override
    public int getItemCount() {
        return tvShowModels.size();
    }

}
