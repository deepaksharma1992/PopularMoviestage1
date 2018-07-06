package com.sharma.deepak.popularmoviestage1.presenter.movie_detail_module;

import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.bean.reviews.Review;
import com.sharma.deepak.popularmoviestage1.bean.videos.Video;
import com.sharma.deepak.popularmoviestage1.database.AppDatabase;

import java.util.List;

public interface MovieDetailPresenterInteractor {

    void callVideosWebAPI(String movieId, String languageCode);

    void callSimilarMoviesWebAPI(String movieId, String languageCode, int pageNumber);

    void callMovieReviewsWebAPI(String movieId, String languageCode, int pageNumber);

    void getVideoList(List<Video> videoList);

    void getSimilarMovieList(List<Movie> similarMoviesList);

    void getReviewList(List<Review> getReviewList);

    void insertMovie(AppDatabase db, Movie movie);

    void deleteMovie(AppDatabase db, Movie movie);
}
