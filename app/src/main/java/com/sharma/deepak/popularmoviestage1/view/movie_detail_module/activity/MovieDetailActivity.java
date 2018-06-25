package com.sharma.deepak.popularmoviestage1.view.movie_detail_module.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sharma.deepak.popularmoviestage1.R;
import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.utility.GlobalConstant;
import com.sharma.deepak.popularmoviestage1.view.BaseActivity;
import com.sharma.deepak.popularmoviestage1.view.movie_list_module.activity.MovieListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.tv_movie_name)
    TextView mMovieNameText;
    @BindView(R.id.tb_favourite)
    ToggleButton mFavouriteButton;
    @BindView(R.id.iv_movie_poster)
    ImageView mPosterImage;
    @BindView(R.id.iv_movie_image)
    ImageView mMovieImage;
    @BindView(R.id.tv_rating_description)
    TextView mRatingText;
    @BindView(R.id.tv_release_date)
    TextView mReleaseDateText;
    @BindView(R.id.tv_language)
    TextView mLanguageText;
    @BindView(R.id.tv_overview)
    TextView mOverviewText;
    @BindView(R.id.pb_load_videos)
    ProgressBar mVideosProgress;
    @BindView(R.id.rv_videos)
    RecyclerView mRvVideos;
    @BindView(R.id.rv_similar_movies)
    RecyclerView mRvSimilarVideos;
    @BindView(R.id.rv_reviews)
    RecyclerView mRvReviews;
    private Movie mReceivedMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        setUpActivityComponents();

    }

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void setUpActivityComponents() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);

        if (getIntent() != null) {
            if (getIntent().hasExtra(MovieListActivity.MOVIE_DATA_PASSED_KEY)) {
                mReceivedMovie = getIntent().getParcelableExtra(MovieListActivity.MOVIE_DATA_PASSED_KEY);
                setDataToView(mReceivedMovie);

            } else {
                Toast.makeText(this, getString(R.string.movie_details_not_present_label), Toast.LENGTH_SHORT).show();
                onBackPressed();
                return;
            }
        }


    }

    /**
     * @param movie the movie object
     * @author deepaks
     * @date 25 june 2018
     * @description method for setting the data to views
     */
    private void setDataToView(Movie movie) {

        String movieName = movie.getTitle();
        String language = movie.getOriginal_language();
        String overView = movie.getOverview();
        String releaseDate = movie.getRelease_date();
        String imagePath = movie.getPoster_path();
        double rating = movie.getVote_average();
        int voteCount = movie.getVote_count();
        String backdropPath = movie.getBackdrop_path();
        Glide
                .with(this)
                .load(imagePath)
                .apply(new RequestOptions()
                        .centerCrop()
                        .error(R.drawable.movie_default)
                        .placeholder(R.drawable.movie_default))
                .into(mMovieImage);

        Glide
                .with(this)
                .load(backdropPath)
                .apply(new RequestOptions()
                        .centerCrop()
                        .error(R.drawable.movie_default)
                        .placeholder(R.drawable.movie_default))
                .into(mPosterImage);

        mOverviewText.setText(overView);
        mLanguageText.setText(language);
        mReleaseDateText.setText(releaseDate);
        mMovieNameText.setText(movieName);
        mRatingText.setText("(" + rating + ") " + voteCount
                + GlobalConstant.SPACE_1
                + getString(R.string.votes_label));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveBack(this);
        finish();
    }
}
