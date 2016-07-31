package com.kirchhoff.exchangerates;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.kirchhoff.exchangerates.database.DatabaseManager;

/**
 * @author Kirchhoff-
 */
public class ExchangeRateApplication extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        //Open connection to database
        DatabaseManager.setHelper(getApplicationContext());

        context = this;
    }


    public static boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
