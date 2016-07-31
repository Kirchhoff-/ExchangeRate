package com.kirchhoff.exchangerates.utils;

import android.util.Log;

/**
 * @author Kirchhoff-
 */
public class LogUtils {

    private static boolean Debug = true;
    private static String LOG_TAG = "tag_log";

    public static void i(String msg) {
        if (Debug) {
            Log.i(LOG_TAG, msg);
        }
    }

    public static void d(String msg) {
        if (Debug) {
            Log.d(LOG_TAG, msg);
        }
    }

    public static void d(String msg, Throwable e) {
        if (Debug) {
            Log.d(LOG_TAG, msg, e);
        }
    }

    public static void e(String msg, Throwable e) {
        if (Debug)
            Log.e(LOG_TAG, msg, e);
    }

    public static void e(String msg) {
        if (Debug)
            Log.e(LOG_TAG, msg);
    }

    public static void e(Throwable e) {
        if (e != null && Debug) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }
    }

}
