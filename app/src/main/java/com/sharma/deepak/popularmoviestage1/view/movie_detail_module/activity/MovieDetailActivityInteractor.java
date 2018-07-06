package com.sharma.deepak.popularmoviestage1.view.movie_detail_module.activity;

import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.bean.reviews.Review;
import com.sharma.deepak.popularmoviestage1.bean.videos.Video;

import java.util.List;

public interface MovieDetailActivityInteractor {
    void setVideoListAdapter(List<Video> videoList);

    void setSimilarVideoListAdapter(List<Movie> similarMovieList);

    void setReviewAdapter(List<Review> reviewList);
}
