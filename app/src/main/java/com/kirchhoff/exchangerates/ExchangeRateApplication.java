package com.kirchhoff.exchangerates;

import android.app.Application;

import com.kirchhoff.exchangerates.database.DatabaseManager;

/**
 * @author Kirchhoff-
 */
public class ExchangeRateApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        //Open connection to database
        DatabaseManager.setHelper(getApplicationContext());
    }
}
