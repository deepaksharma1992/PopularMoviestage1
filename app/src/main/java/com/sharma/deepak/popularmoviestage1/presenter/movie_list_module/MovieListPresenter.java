package com.sharma.deepak.popularmoviestage1.presenter.movie_list_module;

import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.model.movie_list_module.MovieListModel;
import com.sharma.deepak.popularmoviestage1.model.movie_list_module.MovieListModelInteractor;
import com.sharma.deepak.popularmoviestage1.view.movie_list_module.MovieListActivityInteractor;

import java.util.List;

/**
 * Created by deepak on 15-06-2018.
 */

public class MovieListPresenter implements MovieListPresenterInteractor {

    private final MovieListActivityInteractor mActivityInteractor;
    private final MovieListModelInteractor mMovieListModelInteractor;

    public MovieListPresenter(MovieListActivityInteractor activityInteractor) {
        this.mActivityInteractor = activityInteractor;
        mMovieListModelInteractor = new MovieListModel(this);
    }

    /**
     * @param moviePreference the movies that needed to fetch
     * @author deepaks
     * @date 15 june 2018
     * @description method to call the movies list web API
     */
    @Override
    public void callMovieListAPI(String moviePreference) {
        mMovieListModelInteractor.callMovieListWebAPI(moviePreference);
    }

    /**
     * @param movieList the movies list data
     * @author deepaks
     * @date 15 june 2018
     * @description method to set the movies adapter
     */
    @Override
    public void setMovieListData(List<Movie> movieList) {
        mActivityInteractor.showDataView();
        mActivityInteractor.setMoviesAdapter(movieList);
    }

    /**
     * @author deepaks
     * @date 15 june 2018
     * @description method to call the error method of activity
     */
    @Override
    public void showError() {
        mActivityInteractor.showErrorView();
    }


}
