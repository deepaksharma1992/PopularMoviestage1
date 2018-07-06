package com.sharma.deepak.popularmoviestage1.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM favourite_movies ORDER BY id")
    LiveData<List<Movie>> loadAllMovies();

    @Insert
    void insertMovie(Movie movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(Movie movie);

    @Delete
    void deleteTask(Movie movie);

    @Query("SELECT * FROM favourite_movies WHERE id = :id")
    LiveData<Movie> loadMovieById(String id);
}
