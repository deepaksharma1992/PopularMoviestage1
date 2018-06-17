package com.sharma.deepak.popularmoviestage1.bean;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by deepak on 18-04-2017.
 */

public class Movie implements Serializable {
    private String title, poster_path, overview, release_date, original_language, original_title, backdrop_path;
    private double voteAverage, id, popularity;
    private int vote_count;
    private boolean video, adult;

    private static final transient String BASE_URL = "http://image.tmdb.org/t/p/w185";

    public String getTitle() {
        return title;
    }


    public String getPoster_path() {
        return moviePath(poster_path);
    }

    public String getOverview() {
        return overview;
    }


    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getVote_count() {
        return vote_count;
    }


    private final String moviePath(String addr) {

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendEncodedPath(addr)
                .build();
        return builtUri.toString();
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getBackdrop_path() {
        return moviePath(backdrop_path);
    }

    public double getId() {
        return id;
    }

    public double getPopularity() {
        return popularity;
    }

    public boolean isVideo() {
        return video;
    }

    public boolean isAdult() {
        return adult;
    }

}
