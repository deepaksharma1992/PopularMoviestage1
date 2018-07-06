package com.sharma.deepak.popularmoviestage1.presenter.movie_detail_module;

import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.bean.reviews.Review;
import com.sharma.deepak.popularmoviestage1.bean.videos.Video;
import com.sharma.deepak.popularmoviestage1.database.AppDatabase;
import com.sharma.deepak.popularmoviestage1.model.movie_detail_module.MovieDetailDbDbModel;
import com.sharma.deepak.popularmoviestage1.model.movie_detail_module.MovieDetailDbModelInteractor;
import com.sharma.deepak.popularmoviestage1.model.movie_detail_module.MovieReviewModel;
import com.sharma.deepak.popularmoviestage1.model.movie_detail_module.MovieReviewModelInteractor;
import com.sharma.deepak.popularmoviestage1.model.movie_detail_module.MovieVideosModel;
import com.sharma.deepak.popularmoviestage1.model.movie_detail_module.MovieVideosModelInteractor;
import com.sharma.deepak.popularmoviestage1.model.movie_detail_module.SimilarMovieModel;
import com.sharma.deepak.popularmoviestage1.model.movie_detail_module.SimilarMovieModelInteractor;
import com.sharma.deepak.popularmoviestage1.view.movie_detail_module.activity.MovieDetailActivityInteractor;

import java.util.List;

public class MovieDetailPresenter implements MovieDetailPresenterInteractor {
    private final MovieDetailActivityInteractor mActivityInteractor;
    private final MovieReviewModelInteractor mReviewModelInteractor;
    private final SimilarMovieModelInteractor mSimilarModelInteractor;
    private final MovieVideosModelInteractor mVideoModelInteractor;
    private final MovieDetailDbModelInteractor mDbModelInteractor;

    public MovieDetailPresenter(MovieDetailActivityInteractor activityInteractor) {
        this.mActivityInteractor = activityInteractor;
        mReviewModelInteractor = new MovieReviewModel(this);
        mSimilarModelInteractor = new SimilarMovieModel(this);
        mVideoModelInteractor = new MovieVideosModel(this);
        mDbModelInteractor = new MovieDetailDbDbModel(this);
    }

    /**
     * @param movieId      the movie id
     * @param languageCode the language code
     * @author deepaks
     * @date 27 june 2018
     * @description call the videos web API
     */
    @Override
    public void callVideosWebAPI(String movieId, String languageCode) {
        mVideoModelInteractor.callVideosWebAPi(movieId, languageCode);
    }

    /**
     * @param movieId      the movie id
     * @param languageCode the language code
     * @param pageNumber   the page number
     * @author deepaks
     * @date 27 june 2018
     * @description method for calling the similar movies web API
     */
    @Override
    public void callSimilarMoviesWebAPI(String movieId, String languageCode, int pageNumber) {
        mSimilarModelInteractor.callSimilarMovieWebAPI(movieId, languageCode, pageNumber);
    }

    /**
     * @param movieId      the movie id
     * @param languageCode the language code for review web API
     * @param pageNumber   the page number for the web API
     * @author deepaks
     * @date 1 july 2018
     * @description method will call the review web API
     */
    @Override
    public void callMovieReviewsWebAPI(String movieId, String languageCode, int pageNumber) {
        mReviewModelInteractor.callReviewWebAPI(movieId, languageCode, pageNumber);
    }

    /**
     * @param videoList the video list
     * @author deepaks
     * @date 27 june 2018
     * @description method to get the video movie list
     */
    @Override
    public void getVideoList(List<Video> videoList) {
        mActivityInteractor.setVideoListAdapter(videoList);
    }

    /**
     * @param similarMoviesList the similar movie list
     * @author deepaks
     * @date 27 june 2018
     * @description method to get teh similar movie list
     */
    @Override
    public void getSimilarMovieList(List<Movie> similarMoviesList) {
        mActivityInteractor.setSimilarVideoListAdapter(similarMoviesList);
    }

    /**
     * @param reviewList the review list
     * @author deepaks
     * @date 27 june 2018
     * @description method for getting the review list
     */
    @Override
    public void getReviewList(List<Review> reviewList) {
        mActivityInteractor.setReviewAdapter(reviewList);
    }

    /**
     * @param db    the database object
     * @param movie the movie object
     * @author deepaks
     * @date 3 july 2018
     * @description method to insert the movie object in database
     */
    @Override
    public void insertMovie(AppDatabase db, Movie movie) {
        mDbModelInteractor.insertMovie(db, movie);
    }

    /**
     * @param db    the database object
     * @param movie the movie object
     * @author deepaks
     * @date 3 july 2018
     * @description method to delete the movie object in database
     */
    @Override
    public void deleteMovie(AppDatabase db, Movie movie) {
        mDbModelInteractor.deleteMovie(db, movie);
    }
}
