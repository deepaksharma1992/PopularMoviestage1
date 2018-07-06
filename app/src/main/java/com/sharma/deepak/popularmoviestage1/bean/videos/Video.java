package com.sharma.deepak.popularmoviestage1.bean.videos;

import android.net.Uri;

/**
 * Created by deepak on 17-06-2018.
 */

public class Video {
    private String id;
    private String iso_639_1;
    private String iso_3166_1;
    private String key;
    private String name;
    private String site;
    private String size;
    private String type;

    public String getId() {
        return id;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getThumbNailUrl() {
        @SuppressWarnings("SpellCheckingInspection") Uri builtUri = Uri.parse("http://img.youtube.com/vi/").buildUpon()
                .appendEncodedPath(getKey())
                .appendEncodedPath("mqdefault.jpg")
                .build();
        return builtUri.toString();
    }
}

