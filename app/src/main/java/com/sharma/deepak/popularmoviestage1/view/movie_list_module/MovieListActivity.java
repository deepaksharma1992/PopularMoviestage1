package com.sharma.deepak.popularmoviestage1.view.movie_list_module;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.sharma.deepak.popularmoviestage1.R;
import com.sharma.deepak.popularmoviestage1.bean.Movie;
import com.sharma.deepak.popularmoviestage1.presenter.movie_list_module.MovieListPresenter;
import com.sharma.deepak.popularmoviestage1.presenter.movie_list_module.MovieListPresenterInteractor;
import com.sharma.deepak.popularmoviestage1.view.BaseActivity;
import com.sharma.deepak.popularmoviestage1.view.movie_detail_module.DetailActivity;
import com.sharma.deepak.popularmoviestage1.view.movie_detail_module.MovieListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private GridLayoutManager mGridLayoutManager;
    private List<Movie> movieListData;
    public static final String MOVIE_DATA_PASSED_KEY = "moviePassedData";
    private MovieListPresenterInteractor mPresenterInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        setUpActivityComponents();
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
        mPresenterInteractor = new MovieListPresenter(this, this);
        makeConnection(POPULAR_PARAM);
    }

    /**
     * @param preference the preference string for making the connection
     * @author deepaks
     * @date 15 june 2018
     * @description method to make the network connection
     */
    private void makeConnection(String preference) {
        mPresenterInteractor.callMovieListAPI(preference);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_popular) {
            makeConnection(POPULAR_PARAM);
            setTitle(getString(R.string.popular_movies_label));
            return true;
        } else if (id == R.id.action_top_rated) {
            makeConnection(TOP_RATED_PARAM);
            setTitle(getString(R.string.top_rated_movies_label));
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        Intent detailActivityIntent = new Intent(this, DetailActivity.class);
        detailActivityIntent.putExtra(MOVIE_DATA_PASSED_KEY, movie);
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
        movieListData = moviesList;
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mMovieRecyclerView.setHasFixedSize(true);
        mMovieRecyclerView.setLayoutManager(mGridLayoutManager);
        MovieListAdapter rcAdapter = new MovieListAdapter(moviesList, this, this);
        mMovieRecyclerView.setAdapter(rcAdapter);
    }

}
