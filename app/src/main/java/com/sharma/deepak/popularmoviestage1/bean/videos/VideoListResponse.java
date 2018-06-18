package com.sharma.deepak.popularmoviestage1.bean.videos;

import java.util.List;

/**
 * Created by deepak on 17-06-2018.
 */

public class VideoListResponse {
    private double id;
    private List<Video> results;

    public double getId() {
        return id;
    }

    public List<Video> getResults() {
        return results;
    }
}
