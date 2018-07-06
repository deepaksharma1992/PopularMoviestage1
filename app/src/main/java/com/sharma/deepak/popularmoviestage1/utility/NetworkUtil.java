package com.sharma.deepak.popularmoviestage1.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

/**
 * Created by deepak on 15-06-2018.
 */

public class NetworkUtil {

    /**
     * @param context the context of the caller
     * @return the network connection status
     * @author deepaks
     * @date 15 june 2018
     */
    public static boolean isConnectedToNetwork(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    /**
     * @param url     the url string
     * @param address the address string
     * @return the full movie web path
     * @author deepaks
     * @date 15 june 2018
     * @description method to provide the movie web path
     */
    public static String moviePath(String url, String address) {
        Uri builtUri = Uri.parse(url).buildUpon()
                .appendEncodedPath(address)
                .build();
        return builtUri.toString();
    }
}
