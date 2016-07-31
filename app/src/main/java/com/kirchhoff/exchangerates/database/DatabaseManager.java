package com.kirchhoff.exchangerates.database;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 *
 * Class that given access to database
 *
 * @author Kirchhoff-
 */
public class DatabaseManager {

    private static DatabaseHelper databaseHelper;

    public static synchronized DatabaseHelper getHelper(){
        return databaseHelper;
    }

    public static  void setHelper(Context context){
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }
    public static void releaseHelper(){
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }

}
