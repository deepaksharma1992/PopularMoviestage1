package com.sharma.deepak.popularmoviestage1.view.movie_list_module.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sharma.deepak.popularmoviestage1.R;
import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;
import com.sharma.deepak.popularmoviestage1.database.AppDatabase;
import com.sharma.deepak.popularmoviestage1.presenter.movie_list_module.MovieListPresenter;
import com.sharma.deepak.popularmoviestage1.presenter.movie_list_module.MovieListPresenterInteractor;
import com.sharma.deepak.popularmoviestage1.utility.GlobalConstant;
import com.sharma.deepak.popularmoviestage1.view.BaseActivity;
import com.sharma.deepak.popularmoviestage1.view.movie_detail_module.activity.MovieDetailActivity;
import com.sharma.deepak.popularmoviestage1.view.movie_list_module.MovieListActivityInteractor;
import com.sharma.deepak.popularmoviestage1.view.movie_list_module.adapter.MovieListAdapter;
import com.sharma.deepak.popularmoviestage1.viewmodel.GetAllMovieViewModel;
import com.sharma.deepak.popularmoviestage1.viewmodel.GetAllMovieViewModelFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class MovieListActivity extends BaseActivity implements MovieListAdapter.MovieItemClickInterface
        , MovieListActivityInteractor {
    @BindView(R.id.rv_movie_list)
    RecyclerView mMovieRecyclerView;
    @BindView(R.id.pb_load_back)
    ProgressBar mProgressBar;
    @BindView(R.id.ll_error)
    LinearLayout mErrorLinearLayout;

    private static final String POPULAR_PARAM = "popular";
    private static final String TOP_RATED_PARAM = "top_rated";
    private List<Movie> movieListData;
    private MovieListPresenterInteractor mPresenterInteractor;

    private static final String MOVIE_STATE_KEY = "movie_state";
    private static final String DATA_DOWNLOAD_KEY = "download_state";
    private GetAllMovieViewModel mViewModel;
    private int DATA_DOWNLOAD_STATE;
    private static final int DATA_DOWNLOADED_SUCCESSFULLY = 1;
    private static final int DEFAULT_STATE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        setUpActivityComponents();

        if (savedInstanceState != null) {
            String movieState = savedInstanceState.getString(MOVIE_STATE_KEY);
            int dataDownloadState = savedInstanceState.getInt(DATA_DOWNLOAD_KEY);
            setTitle(mViewModel.getTitle());
            if (movieState.equals(getString(R.string.popular_movies_label))) {
                if (dataDownloadState == DATA_DOWNLOADED_SUCCESSFULLY)
                    setMoviesAdapter(mViewModel.getSavedMovies());
                else
                    makeConnection(POPULAR_PARAM);
            } else if (movieState.equals(getString(R.string.top_rated_movies_label))) {
                if (dataDownloadState == DATA_DOWNLOADED_SUCCESSFULLY)
                    setMoviesAdapter(mViewModel.getSavedMovies());
                else
                    makeConnection(TOP_RATED_PARAM);
            } else {
                setMoviesAdapter(mViewModel.getSavedMovies());
            }

        } else {
            makeConnection(POPULAR_PARAM);
            mViewModel.setTitleName(getString(R.string.popular_movies_label));
        }
    }

    /**
     * @return the resource integer file id
     * @author deepaks
     * @date 15 june 2018
     * @description method to get the resource layout
     */
    @Override
    protected int getResourceLayout() {
        return R.layout.activity_main;
    }

    /**
     * @author deepaks
     * @date 15 june 2018
     * @description method to set the activity components and views
     */
    @Override
    protected void setUpActivityComponents() {
        ButterKnife.bind(this);
        setTitle(getString(R.string.popular_movies_label));
        mPresenterInteractor = new MovieListPresenter(this);
        AppDatabase mDb = AppDatabase.getInstance(getApplicationContext());

        GetAllMovieViewModelFactory factory = new GetAllMovieViewModelFactory(mDb);
        mViewModel = ViewModelProviders.of(this, factory).get(GetAllMovieViewModel.class);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(DATA_DOWNLOAD_KEY, DATA_DOWNLOAD_STATE);
        outState.putString(MOVIE_STATE_KEY, mViewModel.getTitle());
        super.onSaveInstanceState(outState);
    }

    /**
     * @param preference the preference string for making the connection
     * @author deepaks
     * @date 15 june 2018
     * @description method to make the network connection
     */
    private void makeConnection(String preference) {
        DATA_DOWNLOAD_STATE = DEFAULT_STATE;
        mMovieRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        mPresenterInteractor.callMovieListAPI(preference);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_popular:
                makeConnection(POPULAR_PARAM);
                setTitle(getString(R.string.popular_movies_label));
                mViewModel.setTitleName(getString(R.string.popular_movies_label));
                return true;
            case R.id.action_top_rated:
                makeConnection(TOP_RATED_PARAM);
                setTitle(getString(R.string.top_rated_movies_label));
                mViewModel.setTitleName(getString(R.string.top_rated_movies_label));
                return true;
            case R.id.action_favourite:
                showFavouriteMovies();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFavouriteMovies() {
        setTitle(getString(R.string.favourite_movies_label));
        mViewModel.setTitleName(getString(R.string.favourite_movies_label));
        mViewModel.getAllMovieList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                setMoviesAdapter(movies);
            }
        });
    }

    /**
     * @param position the position of the adapter
     * @author deepaks
     * @date 15 june 2018
     * @description method to get the position of item clicked from adapter and start the details activity
     */
    @Override
    public void movieClick(int position) {
        Movie movie = movieListData.get(position);
        Intent detailActivityIntent = new Intent(this, MovieDetailActivity.class);
        detailActivityIntent.putExtra(GlobalConstant.MOVIE_DATA_PASSED_KEY, movie);
        startActivity(detailActivityIntent);
        moveHead(this);
    }

    /**
     * @author deepaks
     * @date 15 june 2018
     * @description method to show the list data
     */
    @Override
    public void showDataView() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mErrorLinearLayout.setVisibility(View.GONE);
        mMovieRecyclerView.setVisibility(View.VISIBLE);
    }

    /**
     * @author deepaks
     * @date 15 june 2018
     * @description method to show the error data
     */
    @Override
    public void showErrorView() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mMovieRecyclerView.setVisibility(View.GONE);
        mErrorLinearLayout.setVisibility(View.VISIBLE);
    }

    /**
     * @param moviesList the movies list data
     * @author deepaks
     * @date 15 june 2018
     * @description method to set the movie list adapter
     */
    @Override
    public void setMoviesAdapter(List<Movie> moviesList) {
        mViewModel.setSavedMovies(moviesList);
        DATA_DOWNLOAD_STATE = DATA_DOWNLOADED_SUCCESSFULLY;
        if (moviesList.size() > 0) {
            movieListData = moviesList;
            GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
            mMovieRecyclerView.setHasFixedSize(true);
            mMovieRecyclerView.setLayoutManager(mGridLayoutManager);
            MovieListAdapter rcAdapter = new MovieListAdapter(moviesList, this, this);
            mMovieRecyclerView.setAdapter(rcAdapter);
        } else {
            showErrorView();
        }
    }

}
