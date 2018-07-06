package com.sharma.deepak.popularmoviestage1.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.sharma.deepak.popularmoviestage1.database.AppDatabase;


public class AddMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final String mMovieId;

    public AddMovieViewModelFactory(AppDatabase database, String movieId) {
        mDb = database;
        mMovieId = movieId;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddMovieViewModel(mDb, mMovieId);
    }
}
