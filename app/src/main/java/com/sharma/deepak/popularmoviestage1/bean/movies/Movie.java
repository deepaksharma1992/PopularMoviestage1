package com.sharma.deepak.popularmoviestage1.bean.movies;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by deepak on 18-04-2017.
 */

@Entity(tableName = "favourite_movies")
public class Movie implements Parcelable {
    private final String title;
    private final String poster_path;
    private final String overview;
    private final String release_date;
    private final String original_language;
    private final String original_title;
    private final String backdrop_path;
    private final double vote_average;
    private final double popularity;
    private final int vote_count;

    private final boolean video;
    private final boolean adult;

    @NonNull
    @PrimaryKey
    private final String id;


    public Movie(String title, String poster_path, String overview, String release_date, String original_language, String original_title, String backdrop_path, @NonNull String id, double vote_average, double popularity, int vote_count, boolean video, boolean adult) {
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.original_language = original_language;
        this.original_title = original_title;
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.vote_average = vote_average;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.video = video;
        this.adult = adult;
    }

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
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    @NonNull
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

    public String getOriginal_title() {
        return original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
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
