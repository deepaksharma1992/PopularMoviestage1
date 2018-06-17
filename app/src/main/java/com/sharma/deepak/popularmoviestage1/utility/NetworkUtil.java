package com.sharma.deepak.popularmoviestage1.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
