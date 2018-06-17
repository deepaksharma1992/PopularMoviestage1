package com.sharma.deepak.popularmoviestage1.presenter.movie_list_module;

import com.sharma.deepak.popularmoviestage1.bean.Movie;

import java.util.List;

/**
 * Created by deepak on 15-06-2018.
 */

public interface MovieListPresenterInteractor {

    void callMovieListAPI(String moviePreference);

    void setMovieListData(List<Movie> movieList);

    void showError();

}
