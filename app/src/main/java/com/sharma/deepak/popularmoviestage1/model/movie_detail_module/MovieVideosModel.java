package com.sharma.deepak.popularmoviestage1.model.movie_detail_module;

import com.sharma.deepak.popularmoviestage1.bean.videos.Video;
import com.sharma.deepak.popularmoviestage1.bean.videos.VideoListResponse;
import com.sharma.deepak.popularmoviestage1.network.ApiClient;
import com.sharma.deepak.popularmoviestage1.network.ApiInterface;
import com.sharma.deepak.popularmoviestage1.presenter.movie_detail_module.MovieDetailPresenterInteractor;
import com.sharma.deepak.popularmoviestage1.utility.GlobalConstant;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieVideosModel implements MovieVideosModelInteractor {
    private final MovieDetailPresenterInteractor mMovieDetailPresenterInteractor;
    private Disposable mDisposable;

    public MovieVideosModel(MovieDetailPresenterInteractor movieDetailInteractor) {
        this.mMovieDetailPresenterInteractor = movieDetailInteractor;
    }

    /**
     * @author deepaks
     * @date 27 june 2018
     * @description call the videos web API
     * @param movieId the movie id
     * @param languageCode the language code
     */
    @Override
    public void callVideosWebAPi(String movieId, String languageCode) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Single<VideoListResponse> reviewListResponseObservable =
                apiInterface.getVideosResponse(movieId, GlobalConstant.API_KEY, languageCode);

        reviewListResponseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getVideoListObserver());
    }


    /**
     * @return the single observer for video list data
     * @author deepaks
     * @date 27 june 2018
     * @description method for getting the observer for video list
     */
    private SingleObserver<VideoListResponse> getVideoListObserver() {
        return new SingleObserver<VideoListResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onSuccess(VideoListResponse videoListResponse) {
                List<Video> videoList = videoListResponse.getResults();
                mMovieDetailPresenterInteractor.getVideoList(videoList);
                mDisposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                mDisposable.dispose();
            }
        };
    }
}
