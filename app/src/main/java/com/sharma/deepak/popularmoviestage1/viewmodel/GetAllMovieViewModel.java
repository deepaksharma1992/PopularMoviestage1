package com.sharma.deepak.popularmoviestage1.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.database.AppDatabase;

import java.util.List;

public class GetAllMovieViewModel extends ViewModel {
    private final LiveData<List<Movie>> mMovieList;
    private List<Movie> mSavedMovies;
    private String mTitleName;

    public GetAllMovieViewModel(AppDatabase db) {
        mMovieList = db.movieDao().loadAllMovies();
    }

    public LiveData<List<Movie>> getAllMovieList() {
        return mMovieList;
    }

    public void setSavedMovies(List<Movie> savedMovies) {
        this.mSavedMovies = savedMovies;
    }

    public List<Movie> getSavedMovies() {
        return mSavedMovies;
    }

    public void setTitleName(String title) {
        this.mTitleName = title;
    }

    public String getTitle() {
        return mTitleName;
    }
}
