package com.sharma.deepak.popularmoviestage1.bean.movies;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by deepak on 18-04-2017.
 */

public class Movie implements Serializable {
    private String title, poster_path, overview, release_date, original_language, original_title, backdrop_path, id;
    private double vote_average, popularity;
    private int vote_count;
    private boolean video, adult;

    private static final String BASE_URL = "http://image.tmdb.org/t/p/";
    private static final transient String POSTER_URL = BASE_URL + "w300";
    private static final transient String BACKDROP_URL = BASE_URL + " w500";

    public String getTitle() {
        return title;
    }


    public String getPoster_path() {
        return moviePath(POSTER_URL, poster_path);
    }

    public String getOverview() {
        return overview;
    }

    public String getId() {
        return id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public double getVote_average() {
        return vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }


    private final String moviePath(String url, String addr) {

        Uri builtUri = Uri.parse(url).buildUpon()
                .appendEncodedPath(addr)
                .build();
        return builtUri.toString();
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getBackdrop_path() {
        return moviePath(BACKDROP_URL, backdrop_path);
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
