package com.sharma.deepak.popularmoviestage1.network;

import com.sharma.deepak.popularmoviestage1.bean.movies.MovieListResponse;
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

    @GET("movie/{" + MOVIE_TYPE_PATH + "}/")
    Single<MovieListResponse> getMoviesListResponse(@Path(MOVIE_TYPE_PATH) String movieType
            , @Query(API_KEY) String apiKey);

    //     movie details
//    https://api.themoviedb.org/3/movie/351286?api_key=b27eb13363d494dea756f2639056df68&language=en-US

    //    Get vidios
//    https://api.themoviedb.org/3/movie/351286/videos?api_key=b27eb13363d494dea756f2639056df68&language=en-US
    @GET("movie/{" + MOVIE_ID + "}/videos")
    Single<VideoListResponse> getVideosResponse(@Path(MOVIE_ID) String movieId
            , @Query(API_KEY) String apiKey, @Query(LANGUAGE_QUERY) String languageCode);
//    Get similar movies
//    https://api.themoviedb.org/3/movie/351286/similar?api_key=b27eb13363d494dea756f2639056df68&language=en-US&page=1


    // you tube movie image
    //https://img.youtube.com/vi/youtube-key/hqdefault.jpg

//    reviews
//    https://api.themoviedb.org/3/movie/{movie_id}/reviews?api_key=<<api_key>>&language=en-US&page=1
}
