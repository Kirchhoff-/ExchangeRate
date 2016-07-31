package com.kirchhoff.exchangerates.utils;

import android.text.format.DateFormat;

/**
 * @author Kirchhoff-
 */
public class Time {


    public static String getTime(long time){
        if (time == 0)
            return DateFormat.format("dd/MM/yyyy", System.currentTimeMillis()).toString();
        else
            return DateFormat.format("dd/MM/yyyy", time).toString();
    }


}
