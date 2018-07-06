package com.sharma.deepak.popularmoviestage1.view.movie_detail_module.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sharma.deepak.popularmoviestage1.R;
import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.utility.GlobalConstant;
import com.sharma.deepak.popularmoviestage1.utility.NetworkUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimilarMovieAdapter extends RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieHolderPattern> {
    private final List<Movie> mMovieList;
    private final Context mContext;
    private final SimilarMovieItemClickListener mItemClickListener;

    public SimilarMovieAdapter(List<Movie> movieList, Context context, SimilarMovieItemClickListener itemClickListener) {
        this.mContext = context;
        this.mMovieList = movieList;
        this.mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public SimilarMovieHolderPattern onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.similar_movie_list_item, parent, false);

        return new SimilarMovieHolderPattern(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarMovieHolderPattern holder, int position) {
        Movie movie = mMovieList.get(position);
        holder.movieTitle.setText(movie.getTitle());
        Glide
                .with(mContext)
                .load(NetworkUtil.moviePath(GlobalConstant.POSTER_URL, movie.getPoster_path()))
                .apply(new RequestOptions()
                        .centerCrop()
                        .error(R.drawable.movie_default)
                        .placeholder(R.drawable.movie_default))
                .into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class SimilarMovieHolderPattern extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_movie)
        ImageView movieImage;
        @BindView(R.id.tv_movie_name)
        TextView movieTitle;

        SimilarMovieHolderPattern(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mItemClickListener
                    .similarMovieClick(mMovieList.get(getAdapterPosition()));
        }
    }

    public interface SimilarMovieItemClickListener {
        void similarMovieClick(Movie movie);
    }
}

