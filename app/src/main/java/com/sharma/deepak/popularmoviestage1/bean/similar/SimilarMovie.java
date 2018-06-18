package com.sharma.deepak.popularmoviestage1.bean.similar;

/**
 * Created by deepak on 17-06-2018.
 */

public class SimilarMovie {
    private boolean adult, video;
    private String backdrop_path, id, original_language, original_title, overview, release_date, poster_path, title;
    private double popularity, vote_average;

    public boolean isAdult() {
        return adult;
    }

    public boolean isVideo() {
        return video;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getId() {
        return id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getTitle() {
        return title;
    }

    public double getPopularity() {
        return popularity;
    }

    public double getVote_average() {
        return vote_average;
    }
}

