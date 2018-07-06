package com.sharma.deepak.popularmoviestage1.model.movie_detail_module;

import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.database.AppDatabase;
import com.sharma.deepak.popularmoviestage1.presenter.movie_detail_module.MovieDetailPresenterInteractor;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailDbDbModel implements MovieDetailDbModelInteractor {
    private Disposable mInsertionDisposable;
    private Disposable mDeletionDisposable;

    public MovieDetailDbDbModel(MovieDetailPresenterInteractor presenterInteractor) {
    }

    /**
     * @param db    the database object
     * @param movie the movie object
     * @author deepaks
     * @date 3 july 2018
     * @description method to insert the movie object in database
     */
    @Override
    public void insertMovie(AppDatabase db, Movie movie) {
        getInsertionObservable(db, movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getInsertionObserver());
    }

    /**
     * @param db    the database object
     * @param movie the movie object
     * @author deepaks
     * @date 3 july 2018
     * @description method to delete the movie object in database
     */
    @Override
    public void deleteMovie(AppDatabase db, Movie movie) {
        getDeletionObservable(db, movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getDeletionObserver());
    }

    /**
     * @param db    the database object
     * @param movie the movie object
     * @return the single insertion observable object
     * @author deepaks
     * @date 3 july 2018
     */
    private Single<Void> getInsertionObservable(final AppDatabase db, final Movie movie) {
        return Single.create(new SingleOnSubscribe<Void>() {
            @Override
            public void subscribe(SingleEmitter<Void> emitter){
                db.movieDao().insertMovie(movie);
            }
        });
    }

    /**
     * @param db    the database object
     * @param movie the movie object
     * @return the single deletion observable object
     * @author deepaks
     * @date 3 july 2018
     */
    private Single<Void> getDeletionObservable(final AppDatabase db, final Movie movie) {
        return Single.create(new SingleOnSubscribe<Void>() {
            @Override
            public void subscribe(SingleEmitter<Void> emitter) {
                db.movieDao().deleteTask(movie);
            }
        });
    }


    /**
     * @return the single observer
     * @author deepaks
     * @date 3 july 2018
     */
    private SingleObserver<Void> getInsertionObserver() {
        return new SingleObserver<Void>() {
            @Override
            public void onSubscribe(Disposable d) {
                mInsertionDisposable = d;
            }

            @Override
            public void onSuccess(Void aVoid) {
                mInsertionDisposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                mInsertionDisposable.dispose();
            }
        };
    }

    /**
     * @return the single observer
     * @author deepaks
     * @date 3 july 2018
     */
    private SingleObserver<Void> getDeletionObserver() {
        return new SingleObserver<Void>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDeletionDisposable = d;
            }

            @Override
            public void onSuccess(Void aVoid) {
                mDeletionDisposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                mDeletionDisposable.dispose();
            }
        };
    }
}
