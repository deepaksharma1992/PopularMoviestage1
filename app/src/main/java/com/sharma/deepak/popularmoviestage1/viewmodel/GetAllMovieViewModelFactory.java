package com.sharma.deepak.popularmoviestage1.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.sharma.deepak.popularmoviestage1.database.AppDatabase;

public class GetAllMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;

    public GetAllMovieViewModelFactory(AppDatabase db) {
        this.mDb = db;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new GetAllMovieViewModel(mDb);
    }
}
