package com.sharma.deepak.popularmoviestage1.view.movie_list_module.adapter;

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

/**
 * Created by deepak on 18-04-2017.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieHolderPattern> {
    private final List<Movie> movieListDetails;
    private final Context context;
    private final MovieItemClickInterface movieInterface;

    public MovieListAdapter(List<Movie> movieListDetails, Context context, MovieItemClickInterface movieInterface) {
        this.context = context;
        this.movieListDetails = movieListDetails;
        this.movieInterface = movieInterface;
    }

    @NonNull
    @Override
    public MovieHolderPattern onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);

        return new MovieHolderPattern(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolderPattern holder, int position) {
        Movie movie = movieListDetails.get(position);
        holder.movieTitle.setText(movie.getTitle());
        String movieDetailString = "(" + movie.getOriginal_language() + ")" + "(" + movie.getVote_average() + "/10)";
        holder.movieDetail.setText(movieDetailString);

        Glide
                .with(context)
                .load(NetworkUtil.moviePath(GlobalConstant.POSTER_URL, movie.getPoster_path()))
                .apply(new RequestOptions()
                        .centerCrop()
                        .error(R.drawable.movie_default)
                        .placeholder(R.drawable.movie_default))
                .into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        return movieListDetails.size();
    }

    class MovieHolderPattern extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_movie)
        ImageView movieImage;
        @BindView(R.id.tv_movie_name)
        TextView movieTitle;
        @BindView(R.id.tv_movie_detail)
        TextView movieDetail;

        MovieHolderPattern(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            movieInterface.movieClick(position);
        }
    }


    public interface MovieItemClickInterface {
        void movieClick(int position);
    }
}
