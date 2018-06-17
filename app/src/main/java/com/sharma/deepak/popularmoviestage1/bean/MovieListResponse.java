package com.sharma.deepak.popularmoviestage1.bean;

import java.util.List;

/**
 * Created by deepak on 15-06-2018.
 */

public class MovieListResponse {
    private int page, total_results, total_pages;
    private List<Movie> results;

    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Movie> getResults() {
        return results;
    }
}

