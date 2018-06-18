package com.sharma.deepak.popularmoviestage1.bean.similar;

import java.util.List;

/**
 * Created by deepak on 17-06-2018.
 */

public class SimilarListResponse {
    private int page, total_pages, total_results;
    private List<SimilarMovie> results;

    public int getPage() {
        return page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public List<SimilarMovie> getResults() {
        return results;
    }
}
