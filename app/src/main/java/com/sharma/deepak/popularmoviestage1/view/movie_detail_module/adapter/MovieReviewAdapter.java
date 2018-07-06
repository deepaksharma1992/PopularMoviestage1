package com.sharma.deepak.popularmoviestage1.view.movie_detail_module.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sharma.deepak.popularmoviestage1.R;
import com.sharma.deepak.popularmoviestage1.bean.reviews.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.MovieReviewHolderPattern> {
    private final List<Review> reviewList;

    public MovieReviewAdapter(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public MovieReviewHolderPattern onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_list_item, parent, false);

        return new MovieReviewHolderPattern(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewHolderPattern holder, int position) {
        Review review = reviewList.get(position);
        holder.reviewerNameText.setText(review.getAuthor());
        holder.movieReviewText.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    class MovieReviewHolderPattern extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_review)
        TextView movieReviewText;
        @BindView(R.id.tv_reviewer_name)
        TextView reviewerNameText;

        MovieReviewHolderPattern(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


