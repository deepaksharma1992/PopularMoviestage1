package com.sharma.deepak.popularmoviestage1.model.movie_detail;

import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.bean.similar.SimilarListResponse;
import com.sharma.deepak.popularmoviestage1.network.ApiClient;
import com.sharma.deepak.popularmoviestage1.network.ApiInterface;
import com.sharma.deepak.popularmoviestage1.presenter.movie_detail.MovieDetailPresenterInteractor;
import com.sharma.deepak.popularmoviestage1.utility.GlobalConstant;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SimilarMovieModel implements SimilarMovieModelInteractor {
    private final MovieDetailPresenterInteractor mMovieDetailPresenterInteractor;
    private Disposable mDisposable;

    public SimilarMovieModel(MovieDetailPresenterInteractor movieDetailInteractor) {
        this.mMovieDetailPresenterInteractor = movieDetailInteractor;
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
    public void callSimilarMovieWebAPI(String movieId, String languageCode, int pageNumber) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Single<SimilarListResponse> similarListObservable =
                apiInterface.getSimilarMovieResponse(
                        movieId, GlobalConstant.API_KEY, languageCode, pageNumber
                );

        similarListObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getReviewsObserver());

    }

    /**
     * @return the single observer for the reviews
     * @author deepaks
     * @date 27 july 2018
     */
    private SingleObserver<SimilarListResponse> getReviewsObserver() {
        return new SingleObserver<SimilarListResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onSuccess(SimilarListResponse similarListResponse) {
                List<Movie> similarMovieList = similarListResponse.getResults();
                mMovieDetailPresenterInteractor.getSimilarMovieList(similarMovieList);
                mDisposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                mDisposable.dispose();
            }
        };
    }
}
