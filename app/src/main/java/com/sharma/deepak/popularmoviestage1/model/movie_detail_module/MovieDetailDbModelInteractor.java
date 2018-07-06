package com.sharma.deepak.popularmoviestage1.model.movie_detail_module;

import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.database.AppDatabase;

public interface MovieDetailDbModelInteractor {

    void insertMovie(AppDatabase db, Movie movie);

    void deleteMovie(AppDatabase db, Movie movie);

}
