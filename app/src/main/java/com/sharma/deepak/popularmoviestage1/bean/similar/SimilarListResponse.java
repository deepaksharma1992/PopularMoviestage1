package com.sharma.deepak.popularmoviestage1.bean.similar;

import com.sharma.deepak.popularmoviestage1.bean.movies.Movie;

import java.util.List;

/**
 * Created by deepak on 17-06-2018.
 */

public class SimilarListResponse {
    private int page, total_pages, total_results;
    private List<Movie> results;

    public int getPage() {
        return page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public List<Movie> getResults() {
        return results;
    }
}
