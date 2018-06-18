package com.sharma.deepak.popularmoviestage1.view.movie_list_module;

import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;

import java.util.List;

/**
 * Created by deepak on 15-06-2018.
 */

public interface MovieListActivityInteractor {

    void showDataView();

    void showErrorView();

    void setMoviesAdapter(List<Movie> moviesList);
}
