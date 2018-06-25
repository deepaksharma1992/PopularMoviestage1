package com.sharma.deepak.popularmoviestage1.view.movie_detail_module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.sharma.deepak.popularmoviestage1.R;
import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.view.movie_list_module.activity.MovieListActivity;

public class DetailActivity extends AppCompatActivity {
    private ImageView mMovieImage;
    private TextView mMovieNameTextView, mMovieLanguageTextView, mMovieReleaseDateTextView, mOverViewTextView, mUserRatingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_movie_detail);
      //  initView();

        Intent detailIntent = getIntent();
        Movie movie = (Movie) detailIntent.getSerializableExtra(MovieListActivity.MOVIE_DATA_PASSED_KEY);
       // assignUiToView(movie);

    }
/*
    //Method to assign values on UI
    private void assignUiToView(Movie movie) {
        String movieName = movie.getTitle();
        String language = movie.getOriginal_language();
        String overView = movie.getOverview();
        String releaseDate = movie.getRelease_date();
        String imagePath = movie.getPoster_path();
        double rating = movie.getVote_average();
        int voteCount = movie.getVote_count();

        Glide
                .with(this)
                .load(imagePath)
                .apply(new RequestOptions()
                        .centerCrop()
                        .error(R.drawable.movie_default)
                        .placeholder(R.drawable.movie_default))
                .into(mMovieImage);


        mOverViewTextView.setText(overView);
        mMovieLanguageTextView.setText(language);
        mMovieReleaseDateTextView.setText(releaseDate);
        mMovieNameTextView.setText(movieName);
        mUserRatingTextView.setText("(" + rating + ") " + voteCount + " votes");


    }

    // Method to initialise all UI views
    private void initView() {
        mMovieImage = (ImageView) findViewById(R.id.iv_movie_image);
        mMovieNameTextView = (TextView) findViewById(R.id.tv_movie_name);
        mMovieLanguageTextView = (TextView) findViewById(R.id.tv_language);
        mMovieReleaseDateTextView = (TextView) findViewById(R.id.tv_release_date);
        mUserRatingTextView = (TextView) findViewById(R.id.tv_rating_description);
        mOverViewTextView = (TextView) findViewById(R.id.tv_overview);
    }*/
}
