package com.sharma.deepak.popularmoviestage1.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;


import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.bean.reviews.Review;
import com.sharma.deepak.popularmoviestage1.bean.videos.Video;
import com.sharma.deepak.popularmoviestage1.database.AppDatabase;

import java.util.List;

public class AddMovieViewModel extends ViewModel {

    private final LiveData<Movie> movie;
    private List<Video> mVideoList;
    private List<Movie> mSimilarMovieList;
    private List<Review> mReviewList;

    public AddMovieViewModel(AppDatabase database, String movieID) {
        movie = database.movieDao().loadMovieById(movieID);
    }

    public LiveData<Movie> getMovie() {
        return movie;
    }

    public List<Video> getVideoList() {
        return mVideoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.mVideoList = videoList;
    }


    public List<Movie> getSimilarMovieList() {
        return mSimilarMovieList;
    }

    public void setSimilarMovieList(List<Movie> similarMovieList) {
        this.mSimilarMovieList = similarMovieList;
    }

    public List<Review> getReviewList() {
        return mReviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.mReviewList = reviewList;
    }
}
