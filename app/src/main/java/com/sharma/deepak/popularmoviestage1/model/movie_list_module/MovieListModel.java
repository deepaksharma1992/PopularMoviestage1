package com.sharma.deepak.popularmoviestage1.model.movie_list_module;

import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.bean.movies.MovieListResponse;
import com.sharma.deepak.popularmoviestage1.network.ApiClient;
import com.sharma.deepak.popularmoviestage1.network.ApiInterface;
import com.sharma.deepak.popularmoviestage1.presenter.movie_list_module.MovieListPresenterInteractor;
import com.sharma.deepak.popularmoviestage1.utility.GlobalConstant;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by deepak on 15-06-2018.
 */

public class MovieListModel implements MovieListModelInteractor {

    private Disposable mDisposable;
    private MovieListPresenterInteractor mPresenterInteractor;

    public MovieListModel(MovieListPresenterInteractor presenterInteractor) {
        this.mPresenterInteractor = presenterInteractor;
    }

    /**
     * @param movieType the movie type web API to call
     * @author deepaks
     * @date 15 june 2018
     * @description method ot call the movie list
     */
    @Override
    public void callMovieListWebAPI(String movieType) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Single<MovieListResponse> movieListResponseObservable = apiInterface.getMoviesListResponse(movieType
                , GlobalConstant.API_KEY);

        movieListResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getMovieListObserver());
    }

    /**
     * @return the single observer
     * @author deepaks
     * @date 15 june 2018
     * @description method to get the movie list single observer
     */
    public SingleObserver<MovieListResponse> getMovieListObserver() {
        return new SingleObserver<MovieListResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onSuccess(MovieListResponse movieListResponse) {
                List<Movie> movieList = movieListResponse.getResults();
                mPresenterInteractor.setMovieListData(movieList);
                mDisposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                mPresenterInteractor.showError();
                mDisposable.dispose();
            }
        };
    }
}
