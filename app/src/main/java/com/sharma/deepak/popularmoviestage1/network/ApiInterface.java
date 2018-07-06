package com.sharma.deepak.popularmoviestage1.network;

import com.sharma.deepak.popularmoviestage1.bean.movies.MovieListResponse;
import com.sharma.deepak.popularmoviestage1.bean.reviews.ReviewListResponse;
import com.sharma.deepak.popularmoviestage1.bean.similar.SimilarListResponse;
import com.sharma.deepak.popularmoviestage1.bean.videos.VideoListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by deepak on 26-05-2018.
 */

public interface ApiInterface {
    String MOVIE_TYPE_PATH = "movieType";
    String MOVIE_ID = "movieId";
    String API_KEY = "api_key";
    String LANGUAGE_QUERY = "language";
    String PAGE_QUERY = "page";

    @GET("movie/{" + MOVIE_TYPE_PATH + "}/")
    Single<MovieListResponse> getMoviesListResponse(@Path(MOVIE_TYPE_PATH) String movieType
            , @Query(API_KEY) String apiKey);

    @GET("movie/{" + MOVIE_ID + "}/videos")
    Single<VideoListResponse> getVideosResponse(@Path(MOVIE_ID) String movieId
            , @Query(API_KEY) String apiKey, @Query(LANGUAGE_QUERY) String languageCode);

    @GET("movie/{" + MOVIE_ID + "}/similar")
    Single<SimilarListResponse> getSimilarMovieResponse(@Path(MOVIE_ID) String movieId
            , @Query(API_KEY) String apiKey, @Query(LANGUAGE_QUERY) String languageCode
            , @Query(PAGE_QUERY) int page
    );

    @GET("movie/{" + MOVIE_ID + "}/reviews")
    Single<ReviewListResponse> getReviewsResponse(@Path(MOVIE_ID) String movieId
            , @Query(API_KEY) String apiKey, @Query(LANGUAGE_QUERY) String languageCode
            , @Query(PAGE_QUERY) int page
    );
}
