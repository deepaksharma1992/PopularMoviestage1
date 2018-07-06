package com.sharma.deepak.popularmoviestage1.bean.reviews;

import java.util.List;

public class ReviewListResponse {
    private String id;
    private int page;
    private List<Review> results;

    public String getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public List<Review> getResults() {
        return results;
    }
}
