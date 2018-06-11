package com.tw.retail.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class for common methods and constants.
 */
public class Utils {

    private static String DATA = "data";
    private static String MYPREFERENCE = "mypreference";
    public static String SELECTED_PRODUCT = "selected_product";

    public static String getdataFromLocalJson(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    /**
     * SharedPreference to store the boolean value whether data loaded on initial launch of the application.
     * @param isDataLoaded
     * @param context
     */
    public static void setOnInitialDataLoad(boolean isDataLoaded, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MYPREFERENCE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(DATA,isDataLoaded);
        editor.apply();
    }

    /**
     * SharedPreference to get the initial load boolean value.
     * @param context
     * @return
     */
    public static boolean getOnInitialDataLoad(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MYPREFERENCE,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(DATA,false);
    }


}
