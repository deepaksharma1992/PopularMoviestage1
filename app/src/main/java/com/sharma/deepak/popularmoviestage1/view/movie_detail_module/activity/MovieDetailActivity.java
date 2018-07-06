package com.sharma.deepak.popularmoviestage1.view.movie_detail_module.activity;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sharma.deepak.popularmoviestage1.R;
import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.bean.reviews.Review;
import com.sharma.deepak.popularmoviestage1.bean.videos.Video;
import com.sharma.deepak.popularmoviestage1.database.AppDatabase;
import com.sharma.deepak.popularmoviestage1.viewmodel.AddMovieViewModel;
import com.sharma.deepak.popularmoviestage1.presenter.movie_detail.MovieDetailPresenter;
import com.sharma.deepak.popularmoviestage1.presenter.movie_detail.MovieDetailPresenterInteractor;
import com.sharma.deepak.popularmoviestage1.viewmodel.AddMovieViewModelFactory;
import com.sharma.deepak.popularmoviestage1.utility.GlobalConstant;
import com.sharma.deepak.popularmoviestage1.utility.NetworkUtil;
import com.sharma.deepak.popularmoviestage1.view.BaseActivity;
import com.sharma.deepak.popularmoviestage1.view.movie_detail_module.adapter.MovieReviewAdapter;
import com.sharma.deepak.popularmoviestage1.view.movie_detail_module.adapter.SimilarMovieAdapter;
import com.sharma.deepak.popularmoviestage1.view.movie_detail_module.adapter.VideoListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressWarnings({"WeakerAccess", "UnnecessaryReturnStatement"})
public class MovieDetailActivity extends BaseActivity implements MovieDetailActivityInteractor
        , VideoListAdapter.VideoItemClickListener
        , SimilarMovieAdapter.SimilarMovieItemClickListener {

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
    @BindView(R.id.pb_similar_movies)
    ProgressBar mSimilarProgress;
    @BindView(R.id.pb_review_movies)
    ProgressBar mReviewProgress;
    @BindView(R.id.rv_videos)
    RecyclerView mRvVideos;
    @BindView(R.id.rv_similar_movies)
    RecyclerView mRvSimilarVideos;
    @BindView(R.id.rv_reviews)
    RecyclerView mRvReviews;
    @BindView(R.id.tv_video_error)
    TextView mVideoErrorText;
    @BindView(R.id.tv_similar_error)
    TextView mSimilarMovieErrorText;
    @BindView(R.id.tv_review_error)
    TextView mReviewErrorText;
    private Movie mReceivedMovie;
    private MovieDetailPresenterInteractor mPresenterInteractor;
    private List<Video> mMovieVideoList;
    private AppDatabase mDb;
    private String mMovieId;
    private AddMovieViewModel mViewModel;
    private static final int DEFAULT_STATE = 0;
    private static final int DATA_DOWNLOADED = 1;
    private static final String VIDEO_DOWNLOAD_STATE_KEY = "videoStateKey";
    private static final String REVIEW_DOWNLOAD_STATE_KEY = "reviewStateKey";
    private static final String SIMILAR_DOWNLOAD_STATE_KEY = "similarStateKey";
    private int mVideoDownloadedState;
    private int mReviewDownloadedState;
    private int mSimilarMovieDownloadedState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        setUpActivityComponents();
        setUpFavouriteButtonListener();

        if (NetworkUtil.isConnectedToNetwork(this)) {
            if (savedInstanceState != null) {
                int videoState = savedInstanceState.getInt(VIDEO_DOWNLOAD_STATE_KEY, DEFAULT_STATE);
                if (videoState == DATA_DOWNLOADED)
                    setVideoListAdapter(mViewModel.getVideoList());
                else
                    downloadVideoData();

                int similarMovieState = savedInstanceState.getInt(SIMILAR_DOWNLOAD_STATE_KEY, DEFAULT_STATE);
                if (similarMovieState == DATA_DOWNLOADED)
                    setSimilarVideoListAdapter(mViewModel.getSimilarMovieList());
                else
                    downloadSimilarMovieData();

                int reviewState = savedInstanceState.getInt(REVIEW_DOWNLOAD_STATE_KEY, DEFAULT_STATE);
                if (reviewState == DATA_DOWNLOADED)
                    setReviewAdapter(mViewModel.getReviewList());
                else
                    downloadReviewData();

            } else {
                downloadVideoData();
                downloadSimilarMovieData();
                downloadReviewData();
            }
        } else {
            showNoConnectionMessages();
        }
    }

    /**
     * @author deepaks
     * @date 3 july 2018
     * @description method to handel the favourite button click listener
     */
    private void setUpFavouriteButtonListener() {
        mFavouriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    mPresenterInteractor.insertMovie(mDb, mReceivedMovie);
                    Toast.makeText(MovieDetailActivity.this, getString(R.string.added_to_favourite)
                            , Toast.LENGTH_SHORT).show();
                } else {
                    mPresenterInteractor.deleteMovie(mDb, mReceivedMovie);
                    Toast.makeText(MovieDetailActivity.this, getString(R.string.removed_from_favourite)
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * @return the layout resource file
     * @author deepaks
     */
    @Override
    protected int getResourceLayout() {
        return R.layout.activity_movie_detail;
    }

    /**
     * @author deepaks
     * @description method to set up the activity components
     */
    @Override
    protected void setUpActivityComponents() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        mDb = AppDatabase.getInstance(getApplicationContext());

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent() != null && getIntent().hasExtra(GlobalConstant.MOVIE_DATA_PASSED_KEY)) {
            mPresenterInteractor = new MovieDetailPresenter(this);
            mReceivedMovie = getIntent().getParcelableExtra(GlobalConstant.MOVIE_DATA_PASSED_KEY);
            setDataToView(mReceivedMovie);

            mMovieId = mReceivedMovie.getId();

            AddMovieViewModelFactory movieViewModelFactory
                    = new AddMovieViewModelFactory(mDb, mMovieId);

            mViewModel
                    = ViewModelProviders.of(this, movieViewModelFactory)
                    .get(AddMovieViewModel.class);

            mViewModel.getMovie().observe(this, new Observer<Movie>() {
                @Override
                public void onChanged(@Nullable Movie movie) {
                    if (movie != null) {
                        mFavouriteButton.setChecked(true);
                    } else {
                        mFavouriteButton.setChecked(false);
                    }
                }
            });

        } else {
            Toast.makeText(this, getString(R.string.movie_details_not_present_label), Toast.LENGTH_SHORT).show();
            onBackPressed();
            return;
        }
    }

    /**
     * @param movie the movie object
     * @author deepaks
     * @date 25 june 2018
     * @description method for setting the data to views
     */
    @SuppressLint("SetTextI18n")
    private void setDataToView(Movie movie) {
        String movieName = movie.getTitle();
        String language = movie.getOriginal_language();
        String overView = movie.getOverview();
        String releaseDate = movie.getRelease_date();
        String posterPath = NetworkUtil.moviePath(GlobalConstant.POSTER_URL, movie.getPoster_path());
        double rating = movie.getVote_average();
        int voteCount = movie.getVote_count();
        String backdropPath = NetworkUtil.moviePath(GlobalConstant.BACKDROP_URL, movie.getBackdrop_path());
        String movieID = movie.getId();
        Glide
                .with(this)
                .load(posterPath)
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

    /**
     * @author deepaks
     * @description method for downloading the video list data
     */
    private void downloadVideoData() {
        mPresenterInteractor.callVideosWebAPI(mMovieId, GlobalConstant.US_ENG_CODE);
    }

    /**
     * @author deepaks
     * @description method for downloading the similar movie list data
     */
    private void downloadSimilarMovieData() {
        mPresenterInteractor.callSimilarMoviesWebAPI(mMovieId,
                GlobalConstant.US_ENG_CODE, GlobalConstant.PAGE_1);
    }

    /**
     * @author deepaks
     * @description method for downloading hte review list data
     */
    private void downloadReviewData() {
        mPresenterInteractor.callMovieReviewsWebAPI(mMovieId,
                GlobalConstant.US_ENG_CODE, GlobalConstant.PAGE_1);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(VIDEO_DOWNLOAD_STATE_KEY, mVideoDownloadedState);
        outState.putInt(SIMILAR_DOWNLOAD_STATE_KEY, mSimilarMovieDownloadedState);
        outState.putInt(REVIEW_DOWNLOAD_STATE_KEY, mReviewDownloadedState);
        super.onSaveInstanceState(outState);

    }

    /**
     * @author deepaks
     * @description method to show the no connection layout
     */
    private void showNoConnectionMessages() {
        mVideoErrorText.setText(getString(R.string.video_connection_label));
        mSimilarMovieErrorText.setText(getString(R.string.similar_connection_label));
        mReviewErrorText.setText(getString(R.string.review_connection_label));
        mReviewProgress.setVisibility(View.INVISIBLE);
        mSimilarProgress.setVisibility(View.INVISIBLE);
        mVideosProgress.setVisibility(View.INVISIBLE);
        showErrorMessage(mRvVideos, mVideoErrorText);
        showErrorMessage(mRvSimilarVideos, mSimilarMovieErrorText);
        showErrorMessage(mRvReviews, mReviewErrorText);
    }

    /**
     * @param videoList the video list
     * @author deepaks
     * @description method for setting up the video list adapter
     */
    @Override
    public void setVideoListAdapter(List<Video> videoList) {
        mViewModel.setVideoList(videoList);
        mVideoDownloadedState = DATA_DOWNLOADED;
        mVideosProgress.setVisibility(View.INVISIBLE);
        if (videoList != null && !videoList.isEmpty()) {
            showListData(mRvVideos, mVideoErrorText);
            mMovieVideoList = videoList;
            VideoListAdapter videoListAdapter
                    = new VideoListAdapter(mMovieVideoList, this, this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this
                    , LinearLayoutManager.HORIZONTAL
                    , false);
            mRvVideos.setHasFixedSize(true);
            mRvVideos.setLayoutManager(layoutManager);
            mRvVideos.setAdapter(videoListAdapter);
        } else {
            showErrorMessage(mRvVideos, mVideoErrorText);
            mVideoErrorText.setText(getString(R.string.no_video_description));
        }

    }

    /**
     * @param similarMovieList the similar movie list
     * @author deepaks
     * @description method for setting up the similar list adapter
     */
    @Override
    public void setSimilarVideoListAdapter(List<Movie> similarMovieList) {
        mViewModel.setSimilarMovieList(similarMovieList);
        mSimilarMovieDownloadedState = DATA_DOWNLOADED;
        mSimilarProgress.setVisibility(View.INVISIBLE);
        if (similarMovieList != null && !similarMovieList.isEmpty()) {
            showListData(mRvSimilarVideos, mSimilarMovieErrorText);
            SimilarMovieAdapter similarMovieAdapter = new SimilarMovieAdapter(similarMovieList
                    , this, this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this
                    , GlobalConstant.GRID_COUNT_THREE);
            mRvSimilarVideos.setHasFixedSize(true);
            mRvSimilarVideos.setNestedScrollingEnabled(false);
            mRvSimilarVideos.setLayoutManager(gridLayoutManager);
            mRvSimilarVideos.setAdapter(similarMovieAdapter);
        } else {
            showErrorMessage(mRvSimilarVideos, mSimilarMovieErrorText);
            mSimilarMovieErrorText.setText(getString(R.string.no_similar_movie_desc));
        }
    }

    /**
     * @param reviewList the review movie list
     * @author deepaks
     * @description method for setting up the review list adapter
     */
    @Override
    public void setReviewAdapter(List<Review> reviewList) {
        mViewModel.setReviewList(reviewList);
        mReviewDownloadedState = DATA_DOWNLOADED;
        mReviewProgress.setVisibility(View.INVISIBLE);
        if (reviewList != null && !reviewList.isEmpty()) {
            showListData(mRvReviews, mReviewErrorText);
            MovieReviewAdapter reviewListAdapter = new MovieReviewAdapter(reviewList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this
                    , LinearLayoutManager.VERTICAL
                    , false);
            mRvReviews.setHasFixedSize(true);
            mRvReviews.setNestedScrollingEnabled(false);
            mRvReviews.setLayoutManager(layoutManager);
            mRvReviews.setAdapter(reviewListAdapter);
        } else {
            showErrorMessage(mRvReviews, mReviewErrorText);
            mReviewErrorText.setText(getString(R.string.no_review_desc));
        }
    }

    @OnClick(R.id.btn_share)
    void shareMovieListener() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType(GlobalConstant.TEXT_PLAIN_MIME_TYPE);
        String shareBodyText = getString(R.string.watching_movie_info);
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                mReceivedMovie.getTitle());
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(sharingIntent
                , getString(R.string.share_with_friends_label)));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveBack(this);
        finish();
    }

    /**
     * @param recyclerView the recycler view list
     * @param messageText  the message text
     * @author deepaks
     * @description method for showing the error message layout
     */
    private void showErrorMessage(RecyclerView recyclerView, TextView messageText) {
        recyclerView.setVisibility(View.INVISIBLE);
        messageText.setVisibility(View.VISIBLE);
    }

    /**
     * @param recyclerView the recycler view list
     * @param messageText  the message text
     * @author deepaks
     * @description method to show the list data
     */
    private void showListData(RecyclerView recyclerView, TextView messageText) {
        recyclerView.setVisibility(View.VISIBLE);
        messageText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void movieClick(int position) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"
                + mMovieVideoList.get(position).getKey()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v="
                        + mMovieVideoList.get(position).getKey()));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            try {
                startActivity(webIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this
                        , getString(R.string.no_app_to_open)
                        , Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }

    @Override
    public void similarMovieClick(Movie movie) {
        Intent detailActivityIntent = new Intent(this, MovieDetailActivity.class);
        detailActivityIntent.putExtra(GlobalConstant.MOVIE_DATA_PASSED_KEY, movie);
        finish();
        startActivity(detailActivityIntent);
        moveHead(this);
    }
}
