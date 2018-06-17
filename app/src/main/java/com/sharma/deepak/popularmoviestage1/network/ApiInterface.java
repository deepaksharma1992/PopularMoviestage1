package com.sharma.deepak.popularmoviestage1.network;

import com.sharma.deepak.popularmoviestage1.bean.MovieListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by deepak on 26-05-2018.
 */

public interface ApiInterface {
    String MOVIE_TYPE_PATH = "movieType";

//    http://api.themoviedb.org/3/movie/popular?api_key=b27eb13363d494dea756f2639056df68

    @GET("movie/{" + MOVIE_TYPE_PATH + "}/")
    Single<MovieListResponse> getMoviesListResponse(@Path(MOVIE_TYPE_PATH) String movieType
            , @Query("api_key") String apiKey);
}
