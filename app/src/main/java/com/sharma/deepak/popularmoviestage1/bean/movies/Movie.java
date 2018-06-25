package com.sharma.deepak.popularmoviestage1.bean.movies;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by deepak on 18-04-2017.
 */

public class Movie implements Parcelable {
    private String title, poster_path, overview, release_date, original_language, original_title, backdrop_path, id;
    private double vote_average, popularity;
    private int vote_count;
    private boolean video, adult;

    private static final String BASE_URL = "http://image.tmdb.org/t/p/";
    private static final transient String POSTER_URL = BASE_URL + "w300";
    private static final transient String BACKDROP_URL = BASE_URL + "w500";

    protected Movie(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        backdrop_path = in.readString();
        id = in.readString();
        vote_average = in.readDouble();
        popularity = in.readDouble();
        vote_count = in.readInt();
        video = in.readByte() != 0;
        adult = in.readByte() != 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

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


    private final String moviePath(String url, String address) {

        Uri builtUri = Uri.parse(url).buildUpon()
                .appendEncodedPath(address)
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeString(original_language);
        parcel.writeString(original_title);
        parcel.writeString(backdrop_path);
        parcel.writeString(id);
        parcel.writeDouble(vote_average);
        parcel.writeDouble(popularity);
        parcel.writeInt(vote_count);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeByte((byte) (adult ? 1 : 0));
    }
}
