package com.sharma.deepak.popularmoviestage1.model.movie_detail;

import com.sharma.deepak.popularmoviestage1.bean.reviews.Review;
import com.sharma.deepak.popularmoviestage1.bean.reviews.ReviewListResponse;
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

public class MovieReviewModel implements MovieReviewModelInteractor {

    private final MovieDetailPresenterInteractor mMovieDetailPresenterInteractor;
    private Disposable mDisposable;

    public MovieReviewModel(MovieDetailPresenterInteractor movieDetailInteractor) {
        this.mMovieDetailPresenterInteractor = movieDetailInteractor;
    }

    /**
     * @param movieID      the movie id
     * @param languageCode the language code for review web API
     * @param pageNumber   the page number for the web API
     * @author deepaks
     * @date 1 july 2018
     * @description method will call the review web API
     */
    @Override
    public void callReviewWebAPI(String movieID, String languageCode, int pageNumber) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Single<ReviewListResponse> reviewListResponseObservable =
                apiInterface.getReviewsResponse(movieID, GlobalConstant.API_KEY
                        , languageCode, pageNumber);

        reviewListResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getReviewListObserver());
    }

    /**
     * @author deepaks
     * @date 3 july 2018
     * @return the single observer for review list observer
     */
    private SingleObserver<ReviewListResponse> getReviewListObserver() {
        return new SingleObserver<ReviewListResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onSuccess(ReviewListResponse reviewListResponse) {
                List<Review> reviewList = reviewListResponse.getResults();
                mMovieDetailPresenterInteractor.getReviewList(reviewList);
                mDisposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                mDisposable.dispose();
            }
        };
    }


}
